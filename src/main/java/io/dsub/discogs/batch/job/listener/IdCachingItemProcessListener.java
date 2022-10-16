package io.dsub.discogs.batch.job.listener;

import static io.dsub.discogs.batch.job.registry.EntityIdRegistry.Type.ARTIST;
import static io.dsub.discogs.batch.job.registry.EntityIdRegistry.Type.GENRE;
import static io.dsub.discogs.batch.job.registry.EntityIdRegistry.Type.LABEL;
import static io.dsub.discogs.batch.job.registry.EntityIdRegistry.Type.MASTER;
import static io.dsub.discogs.batch.job.registry.EntityIdRegistry.Type.RELEASE;
import static io.dsub.discogs.batch.job.registry.EntityIdRegistry.Type.STYLE;

import io.dsub.discogs.batch.xml.artist.ArtistXML;
import io.dsub.discogs.batch.xml.label.LabelXML;
import io.dsub.discogs.batch.xml.master.MasterXML;
import io.dsub.discogs.batch.xml.release.ReleaseItemXML;
import io.dsub.discogs.batch.job.registry.DefaultEntityIdRegistry;
import io.dsub.discogs.batch.job.registry.EntityIdRegistry;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ItemProcessListener;

@RequiredArgsConstructor
public class IdCachingItemProcessListener implements ItemProcessListener<Object, Object> {

  private final EntityIdRegistry idRegistry;

  /* No Op */
  @Override
  public void beforeProcess(Object item) {

  }

  @Override
  public void afterProcess(Object pulled, Object result) {
    if (result == null) {
      return;
    }
    if (pulled instanceof ArtistXML artist) {
      if (artist.getId() != null) {
        idRegistry.put(ARTIST, artist.getId());
      }
    } else if (pulled instanceof LabelXML label) {
      if (label.getId() != null) {
        idRegistry.put(LABEL, label.getId());
      }
    } else if (pulled instanceof MasterXML master) {
      if (master.getId() != null) {
        idRegistry.put(MASTER, master.getId());
        cacheStringTypedItems(STYLE, master.getStyles());
        cacheStringTypedItems(GENRE, master.getGenres());
      }
    } else if (pulled instanceof ReleaseItemXML releaseItem) {
      if (releaseItem.getId() != null) {
        idRegistry.put(RELEASE, releaseItem.getId());
        cacheStringTypedItems(GENRE, releaseItem.getGenres());
        cacheStringTypedItems(STYLE, releaseItem.getStyles());
      }
    }
  }

  private void cacheStringTypedItems(DefaultEntityIdRegistry.Type type, List<String> values) {
    if (values == null || values.isEmpty()) {
      return;
    }
    values.stream()
        .filter(Objects::nonNull)
        .map(String::trim)
        .filter(val -> !val.isBlank())
        .forEach(value -> idRegistry.put(type, value));
  }

  /* No Op */
  @Override
  public void onProcessError(Object item, Exception e) {

  }
}
