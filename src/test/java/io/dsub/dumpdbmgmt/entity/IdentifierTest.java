package io.dsub.dumpdbmgmt.entity;

import io.dsub.dumpdbmgmt.entity.nested.Identifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentifierTest {

    Identifier identifier;

    @BeforeEach
    void setUp() {
        identifier = new Identifier("description", "type", "value");
    }

    @Test
    void testToString() {
        String target = identifier.toString();
        assertTrue(target.contains("description"));
        assertTrue(target.contains("type"));
        assertTrue(target.contains("value"));
    }

    @Test
    void testEquals() {
        assertEquals(identifier, identifier);
        assertEquals(identifier, identifier.withType("type"));
        assertNotEquals(identifier, identifier.withValue("type"));
    }

    @Test
    void testHashCode() {
        Integer target = identifier.hashCode();
        assertNotEquals(null, target);
    }

    @Test
    void withDescription() {
        identifier = identifier.withDescription("desc");
        assertEquals("desc", identifier.getDescription());
    }

    @Test
    void withType() {
        identifier = identifier.withType("newType");
        assertEquals("newType", identifier.getType());
    }

    @Test
    void withValue() {
        identifier = identifier.withValue("val");
        assertEquals("val", identifier.getValue());
    }

    @Test
    void getDescription() {
        assertEquals("description", identifier.getDescription());
    }

    @Test
    void getType() {
        assertEquals("type", identifier.getType());
    }

    @Test
    void getValue() {
        assertEquals("value", identifier.getValue());
    }
}
