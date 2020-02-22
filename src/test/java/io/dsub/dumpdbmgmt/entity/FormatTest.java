package io.dsub.dumpdbmgmt.entity;

import io.dsub.dumpdbmgmt.entity.nested.Format;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FormatTest {

    Format format;

    @BeforeEach
    void setUp() {
        format = new Format();
    }

    @Test
    void withName() {
        format = format.withName("Vinyl");
        assertEquals("Vinyl", format.getName());
    }

    @Test
    void withRelease() {
        Set<Release> releaseSet = Collections.synchronizedSet(new HashSet<>());
        Release release = new Release(3L);
        releaseSet.add(release);
    }

    @Test
    void withDescriptions() {
        Set<String> descriptions = Collections.synchronizedSet(new HashSet<>());
        descriptions.add("Hello");
        descriptions.add("World");
        format = format.withDescriptions(descriptions);
        assertTrue(format.getDescriptions().contains("Hello"));
        assertTrue(format.getDescriptions().contains("World"));
    }

    @Test
    void getName() {
        format = format.withName("Vinyl");
        assertEquals("Vinyl", format.getName());
    }

    @Test
    void getRelease() {
        Set<Release> releaseSet = Collections.synchronizedSet(new HashSet<>());
        Release release = new Release(3L);
        releaseSet.add(release);
    }

    @Test
    void getDescriptions() {
        Set<String> descriptions = Collections.synchronizedSet(new HashSet<>());
        descriptions.add("Hello");
        descriptions.add("World");
        format = format.withDescriptions(descriptions);

        assertTrue(format.getDescriptions().contains("Hello"));
        assertTrue(format.getDescriptions().contains("World"));
    }
}
