package io.dsub.dumpdbmgmt.entity;

import io.dsub.dumpdbmgmt.entity.nested.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrackTest {

    Track track;

    @BeforeEach
    void setUp() {
        track = new Track("title", "duration", "position");
    }

    @Test
    void testToString() {
        String target = track.toString();
        assertTrue(target.contains("title"));
        assertTrue(target.contains("duration"));
        assertTrue(target.contains("position"));

        track = new Track();
        assertNull(track.getTitle());
        assertNull(track.getPosition());
        assertNull(track.getDuration());
    }

    @Test
    void testEquals() {
        Track target = track.withTitle("Hello");
        assertNotEquals(target, track);
        assertNotNull(track.getTitle());
        assertEquals(target, target);
    }

    @Test
    void testHashCode() {
        Integer target = track.hashCode();
        assertNotNull(target);
    }

    @Test
    void withTitle() {
        track = track.withTitle("newTitle");
        assertEquals("newTitle", track.getTitle());
    }

    @Test
    void withDuration() {
        track = track.withDuration("newDuration");
        assertEquals("newDuration", track.getDuration());
    }

    @Test
    void withPosition() {
        track = track.withPosition("newPosition");
        assertEquals("newPosition", track.getPosition());
    }

}
