package io.dsub.discogs.batch.job.processor;

import static io.dsub.discogs.batch.job.registry.EntityIdRegistry.Type.ARTIST;
import static io.dsub.discogs.batch.job.registry.EntityIdRegistry.Type.GENRE;
import static io.dsub.discogs.batch.job.registry.EntityIdRegistry.Type.STYLE;

import io.dsub.discogs.batch.xml.master.MasterSubItemsXML;
import io.dsub.discogs.batch.job.registry.EntityIdRegistry;
import io.dsub.discogs.batch.util.ReflectionUtil;
import io.dsub.discogs.jooq.tables.records.MasterGenreRecord;
import io.dsub.discogs.jooq.tables.records.MasterStyleRecord;
import io.dsub.discogs.jooq.tables.records.MasterVideoRecord;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.jooq.UpdatableRecord;
import org.springframework.batch.item.ItemProcessor;

@RequiredArgsConstructor
public class MasterSubItemsProcessor
    implements ItemProcessor<MasterSubItemsXML, Collection<UpdatableRecord<?>>> {

  private final EntityIdRegistry idRegistry;

  @Override
  public Collection<UpdatableRecord<?>> process(MasterSubItemsXML master) {

    if (master.getId() == null || master.getId() < 1) {
      return null;
    }

    ReflectionUtil.normalizeStringFields(master);

    List<UpdatableRecord<?>> items = new ArrayList<>();
    Integer masterId = master.getId();

    if (master.getMasterArtists() != null) {
      master.getMasterArtists().stream()
          .filter(Objects::nonNull)
          .distinct()
          .filter(masterArtist -> isExistingArtist(masterArtist.getArtistId()))
          .map(xml -> xml.getRecord(masterId))
          .forEach(items::add);
    }

    if (master.getMasterVideos() != null) {
      master.getMasterVideos().stream()
          .filter(Objects::nonNull)
          .distinct()
          .filter(video -> video.getUrl() != null && !video.getUrl().isBlank())
          .map(video -> getMasterVideoRecord(masterId, video))
          .forEach(items::add);
    }

    if (master.getGenres() != null) {
      master.getGenres().stream()
          .filter(Objects::nonNull)
          .map(String::trim)
          .distinct()
          .filter(this::isExistingGenre)
          .map(genre -> getMasterGenreRecord(masterId, genre))
          .forEach(items::add);
    }

    if (master.getStyles() != null) {
      master.getStyles().stream()
          .filter(Objects::nonNull)
          .map(String::trim)
          .distinct()
          .filter(this::isExistingStyle)
          .map(style -> getMasterStyleRecord(masterId, style))
          .forEach(items::add);
    }

    return items.stream().filter(Objects::nonNull).collect(Collectors.toList());
  }

  private boolean isExistingArtist(Integer id) {
    return idRegistry.exists(ARTIST, id);
  }

  private boolean isExistingStyle(String name) {
    return idRegistry.exists(STYLE, name);
  }

  private boolean isExistingGenre(String name) {
    return idRegistry.exists(GENRE, name);
  }

  private MasterGenreRecord getMasterGenreRecord(Integer masterId, String genre) {
    return new MasterGenreRecord()
        .setMasterId(masterId)
        .setGenre(genre)
        .setLastModifiedAt(LocalDateTime.now(Clock.systemUTC()))
        .setCreatedAt(LocalDateTime.now(Clock.systemUTC()));
  }

  private MasterStyleRecord getMasterStyleRecord(Integer masterId, String style) {
    return new MasterStyleRecord()
        .setMasterId(masterId)
        .setStyle(style)
        .setLastModifiedAt(LocalDateTime.now(Clock.systemUTC()))
        .setCreatedAt(LocalDateTime.now(Clock.systemUTC()));
  }

  private MasterVideoRecord getMasterVideoRecord(
      Integer masterId, MasterSubItemsXML.MasterVideoXML video) {
    String hashSrc =
        (video.getTitle() == null ? "" : video.getTitle())
            + (video.getDescription() == null ? "" : video.getDescription())
            + (video.getUrl() == null ? "" : video.getUrl());
    return new MasterVideoRecord()
        .setMasterId(masterId)
        .setTitle(video.getTitle())
        .setDescription(video.getDescription())
        .setUrl(video.getUrl())
        .setHash(hashSrc.hashCode())
        .setLastModifiedAt(LocalDateTime.now(Clock.systemUTC()))
        .setCreatedAt(LocalDateTime.now(Clock.systemUTC()));
  }
}
