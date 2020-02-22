package io.dsub.dumpdbmgmt.entity;

import io.dsub.dumpdbmgmt.entity.nested.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class VideoTest {

    Video video;

    @BeforeEach
    void setUp() {
        video = new Video();
    }

    @Test
    void initTest() {
        assertNull(video.getDescription());
        assertNull(video.getUrl());
        assertNull(video.getTitle());
        assertNull(video.getCreatedAt());
        assertNull(video.getUpdatedAt());
    }

    @Test
    void withTitle() {
        video = video.withTitle("title");
        assertEquals("title", video.getTitle());
    }

    @Test
    void withDescription() {
        video = video.withDescription("desc");
        assertEquals("desc", video.getDescription());
    }

    @Test
    void withUrl() {
        video = video.withUrl("url");
        assertEquals("url", video.getUrl());
    }

}
