package io.dsub.dumpdbmgmt.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BaseEntityTest {

    BaseEntity baseEntity;

    @BeforeEach
    void setUp() {
        baseEntity = new Artist();
    }

    @Test
    void persist() {
        baseEntity.persist();
        assertNotNull(baseEntity.createdAt);
    }

    @Test
    void update() {
        baseEntity.persist();
        baseEntity.update();
        assertNotNull(baseEntity.updatedAt);
        assertNotEquals(baseEntity.createdAt, baseEntity.updatedAt);
    }

    @Test
    void getCreatedAt() {
        baseEntity.persist();
        assertNotNull(baseEntity.createdAt);
    }

    @Test
    void getUpdatedAt() {
        baseEntity.persist();
        baseEntity.update();
        assertNotNull(baseEntity.updatedAt);
        assertNotEquals(baseEntity.createdAt, baseEntity.updatedAt);
    }
}
