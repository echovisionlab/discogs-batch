package io.dsub.dumpdbmgmt.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MasterReleaseTest {
    MasterRelease masterRelease;

    @BeforeEach
    void setUp() {
        masterRelease = new MasterRelease();
    }

    @Test
    void initTest() {
        masterRelease = new MasterRelease(1L);
        assertEquals(1L, masterRelease.getId());
        assertEquals(0, masterRelease.getStyles().size());
        assertEquals(0, masterRelease.getGenres().size());
        assertEquals(0, masterRelease.getArtists().size());
        assertEquals(0, masterRelease.getReleases().size());
        assertNull(masterRelease.getTitle());
        assertNull(masterRelease.getReleaseYear());
    }

    @Test
    void withId() {
        masterRelease = masterRelease.withId(3L);
        assertEquals(3L, masterRelease.getId());
    }

    @Test
    void withTitle() {
        masterRelease = masterRelease.withTitle("title");
        assertEquals("title", masterRelease.getTitle());
    }

    @Test
    void withReleaseYear() {
        masterRelease = masterRelease.withReleaseYear((short) 1993);
        assertEquals((short) 1993, masterRelease.getReleaseYear());
    }

    @Test
    void withReleases() {
        Release release = new Release(3L);
        Set<Release> releases = Collections.synchronizedSet(new HashSet<>());
        releases.add(release);
        masterRelease = masterRelease.withReleases(releases);
        assertEquals(1, masterRelease.getReleases().size());
        assertEquals(3L, masterRelease.getReleases().iterator().next().getId());
    }

    @Test
    void withArtists() {
        Artist artist = new Artist(3L);
        Set<Artist> artists = Collections.synchronizedSet(new HashSet<>());
        artists.add(artist);
        masterRelease = masterRelease.withArtists(artists);
        assertEquals(3L, masterRelease.getArtists().iterator().next().getId());
        assertEquals(1, masterRelease.getArtists().size());
    }

    @Test
    void withGenres() {
        Set<String> genres = Collections.synchronizedSet(new HashSet<>());
        genres.add("Jazz");
        genres.add("Electronic");
        masterRelease = masterRelease.withGenres(genres);
        assertTrue(masterRelease.getGenres().contains("Jazz"));
        assertTrue(masterRelease.getGenres().contains("Electronic"));
    }

    @Test
    void withStyles() {
        Set<String> styles = Collections.synchronizedSet(new HashSet<>());
        styles.add("Alternative");
        styles.add("Experimental");
        masterRelease = masterRelease.withStyles(styles);
        assertTrue(masterRelease.getStyles().contains("Alternative"));
        assertTrue(masterRelease.getStyles().contains("Experimental"));
    }
}
