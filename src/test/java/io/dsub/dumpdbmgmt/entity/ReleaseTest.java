package io.dsub.dumpdbmgmt.entity;

import io.dsub.dumpdbmgmt.entity.intermed.ArtistCredit;
import io.dsub.dumpdbmgmt.entity.intermed.LabelRelease;
import io.dsub.dumpdbmgmt.entity.nested.Format;
import io.dsub.dumpdbmgmt.entity.nested.Identifier;
import io.dsub.dumpdbmgmt.entity.nested.Track;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ReleaseTest {

    Release release;

    @BeforeEach
    void setUp() {
        release = new Release(10L);
    }

    @Test
    void testToString() {
        String target = release.toString();
        assertNotNull(target);
        release = release.withId(3L);
        assertNotEquals(release.toString(), target);
    }

    @Test
    void testEquals() {
        Release target = release.withId(10L);
        assertSame(target, release);
        assertEquals(target, release);
        target = release.withId(9L);
        release = release.withId(9L);
        assertNotSame(target, release);
        assertEquals(target, release);

    }

    @Test
    void testHashCode() {
        Integer target = release.hashCode();
        assertNotNull(target);
    }

    @Test
    void withId() {
        Release target = release.withId(10L);
        assertEquals(target, release);
        target = release.withId(9L);
        release = release.withId(9L);
        assertNotSame(target, release);
    }

    @Test
    void withStatus() {
        release = release.withStatus("Needs vote");
        assertNotNull(release.getStatus());
        assertEquals("Needs vote", release.getStatus());
    }

    @Test
    void withTitle() {
        release = release.withTitle("Hello World");
        assertNotNull(release.getTitle());
        assertEquals("Hello World", release.getTitle());
    }

    @Test
    void withCountry() {
        release = release.withCountry("South Korea");
        assertNotNull(release.getCountry());
        assertEquals("South Korea", release.getCountry());
    }

    @Test
    void withNotes() {
        release = release.withNotes("My Note");
        assertNotNull(release.getNotes());
        assertEquals("My Note", release.getNotes());
    }

    @Test
    void withIsMain() {
        release = release.withIsMain(true);
        assertNotNull(release.getIsMain());
        assertTrue(release.getIsMain());
    }

    @Test
    void withMasterRelease() {
        MasterRelease masterRelease = new MasterRelease();
        masterRelease = masterRelease.withId(33L);
        release = release.withMasterRelease(masterRelease);
        assertNotNull(release.getMasterRelease());
        assertEquals(masterRelease, release.getMasterRelease());
        assertSame(masterRelease, release.getMasterRelease());
        assertEquals(33L, release.getMasterRelease().getId());
    }

    @Test
    void withArtists() {
        Artist artist = new Artist();
        artist = artist.withId(3245L);
        Set<Artist> target = Collections.synchronizedSet(new HashSet<>());
        target.add(artist);
        release = release.withArtists(target);
        assertNotNull(release.getArtists());
        assertEquals(1, release.getArtists().size());
        assertEquals(3245L, release.getArtists().iterator().next().getId());
    }

    @Test
    void withCreditedArtists() {
        ArtistCredit artistCredit = new ArtistCredit();
        Artist artist = new Artist();
        artist = artist.withId(3L);
        artistCredit = artistCredit.withArtist(artist);
        artistCredit = artistCredit.withCredit("Did Something!");
        artistCredit = artistCredit.withRelease(release);
        Set<ArtistCredit> target = Collections.synchronizedSet(new HashSet<>());
        target.add(artistCredit);
        release = release.withCreditedArtists(target);

        assertNotNull(release.getCreditedArtists());
        assertEquals(3L, release.getCreditedArtists().iterator().next().getArtist().getId());
        assertEquals("Did Something!", release.getCreditedArtists().iterator().next().getCredit());
    }

    @Test
    void withTracks() {
        Track track_1 = new Track("Hello World", "1:23", "A1");
        Track track_2 = new Track("Hello That World", "2:34", "A2");
        Track track_3 = new Track("Hello This World", "3:45", "B1");
        Set<Track> tracks = Collections.synchronizedSet(new HashSet<>());
        tracks.add(track_1);
        tracks.add(track_2);
        tracks.add(track_3);

        Release target;
        target = release.withTracks(tracks);

        assertEquals(3, target.getTracks().size());
        assertEquals(0, release.getTracks().size());
        assertTrue(target.getTracks().containsAll(tracks));
        assertNotSame(target, release);
    }

    @Test
    void withIdentifiers() {
        Identifier identifier = new Identifier("desc", "type", "value");
        Set<Identifier> target = Collections.synchronizedSet(new HashSet<>());
        target.add(identifier);
        release = release.withIdentifiers(target);

        assertNotNull(release.getIdentifiers());
        assertEquals(1, release.getIdentifiers().size());
        assertEquals("desc", release.getIdentifiers().iterator().next().getDescription());
    }

    @Test
    void withFormats() {
        Format format = new Format();
        Set<Format> formats = Collections.synchronizedSet(new HashSet<>());
        formats.add(format);
        release = release.withFormats(formats);

        assertNotNull(release.getFormats());
        assertEquals(1, release.getFormats().size());
    }

    @Test
    void withLabelReleases() {
        LabelRelease labelRelease = new LabelRelease();
        Label label = new Label();
        label = label.withId(43L);

        labelRelease = labelRelease.withLabel(label);
        labelRelease = labelRelease.withCatNo("CATEGORY");
        labelRelease = labelRelease.withRelease(release);

        Set<LabelRelease> target = Collections.synchronizedSet(new HashSet<>());
        target.add(labelRelease);

        release = release.withLabelReleases(target);

        release.getLabelReleases().forEach(item -> log.info(item.toString()));

        assertEquals(43L, release.getLabelReleases().iterator().next().getLabel().getId());
        assertEquals(release.getId(), release.getLabelReleases().iterator().next().getRelease().getId());
    }

    @Test
    void withAddArtistTest() {
        Artist artist = new Artist();
        artist = artist.withId(33L);
        release = release.withAddArtists(artist);
        assertNotNull(release.getArtists());
        assertTrue(release.getArtists().contains(artist));
        assertEquals(33L, release.getArtists().iterator().next().getId());
    }

    @Test
    void withRemoveArtistTest() {
        Release release = new Release();

        Artist artist = new Artist();
        artist = artist.withId(33L);
        release = release.withAddArtists(artist);
        assertNotNull(release.getArtists());
        assertTrue(release.getArtists().contains(artist));
        assertEquals(33L, release.getArtists().iterator().next().getId());

        release = release.withRemoveArtist(artist);

        assertEquals(0, release.getArtists().size());
    }

    @Test
    void withAddTracks() {
        Track track_1 = new Track("Title", "Duration", "Position");
        Track track_2 = new Track("Title", "Duration", "Position");
        release = release.withAddTracks(track_1, track_2);
        assertEquals("Title", release.getTracks().iterator().next().getTitle());
        assertEquals(1, release.getTracks().size());

        Track track_3 = new Track("New", "Duration", "Position");
        release = release.withAddTracks(track_3);
        assertEquals(2, release.getTracks().size());
    }

    @Test
    void withRemoveTrack() {
        Track track = new Track("Title", "Duration", "Position");
        release = release.withAddTracks(track);

        Track second = new Track();
        second = second.withTitle("Title");
        release = release.withRemoveTrack(second);

        assertEquals(0, release.getTracks().size());
    }

    @Test
    void withAddIdentifiers() {
        Identifier identifier = new Identifier();
        identifier = identifier.withValue("Value");
        identifier = identifier.withType("Type");
        identifier = identifier.withDescription("Description");

        release = release.withAddIdentifiers(identifier);

        assertEquals(1, release.getIdentifiers().size());
        assertEquals("Value", release.getIdentifiers().iterator().next().getValue());
    }

    @Test
    void withRemoveIdentifier() {
        Identifier identifier = new Identifier();
        identifier = identifier.withValue("Value");
        identifier = identifier.withType("Type");
        identifier = identifier.withDescription("Description");

        release = release.withAddIdentifiers(identifier);

        release = release.withRemoveIdentifier(identifier);

        assertEquals(0, release.getIdentifiers().size());
    }

    @Test
    void withAddFormats() {
        Format format = new Format();
        format = format.withName("Name");
        release = release.withAddFormats(format);
        assertEquals(1, release.getFormats().size());
        assertEquals("Name", release.getFormats().iterator().next().getName());
    }

    @Test
    void withRemoveFormat() {
        Format format = new Format();
        format = format.withName("Name");
        release.withAddFormats(format);

        release = release.withRemoveFormat(format);

        assertEquals(0, release.getFormats().size());
    }
}
