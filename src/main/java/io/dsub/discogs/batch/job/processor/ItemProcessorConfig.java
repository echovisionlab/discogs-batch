package io.dsub.discogs.batch.job.processor;

import io.dsub.discogs.batch.job.registry.DefaultEntityIdRegistry;
import io.dsub.discogs.batch.xml.artist.ArtistSubItemsXML;
import io.dsub.discogs.batch.xml.artist.ArtistXML;
import io.dsub.discogs.batch.xml.label.LabelSubItemsXML;
import io.dsub.discogs.batch.xml.label.LabelXML;
import io.dsub.discogs.batch.xml.master.MasterMainReleaseXML;
import io.dsub.discogs.batch.xml.master.MasterSubItemsXML;
import io.dsub.discogs.batch.xml.master.MasterXML;
import io.dsub.discogs.batch.xml.release.ReleaseItemSubItemsXML;
import io.dsub.discogs.batch.xml.release.ReleaseItemXML;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Configuration
@RequiredArgsConstructor
public class ItemProcessorConfig {

  private final DefaultEntityIdRegistry entityIdRegistry;

  @Bean
  @StepScope
  public ItemProcessor<ArtistXML, ArtistRecord> artistCoreProcessor() {
    return new ArtistCoreProcessor();
  }

  @Bean
  @StepScope
  public ItemProcessor<ArtistSubItemsXML, Collection<UpdatableRecord<?>>>
  artistSubItemsProcessor() {
    return new ArtistSubItemsProcessor(entityIdRegistry);
  }

  @Bean
  @StepScope
  public ItemProcessor<LabelXML, LabelRecord> labelCoreProcessor() {
    return new LabelCoreProcessor();
  }

  @Bean
  @StepScope
  public ItemProcessor<LabelSubItemsXML, Collection<UpdatableRecord<?>>> labelSubItemsProcessor() {
    return new LabelSubItemsProcessor(entityIdRegistry);
  }

  @Bean
  @StepScope
  public ItemProcessor<MasterXML, MasterRecord> masterCoreProcessor() {
    return new MasterCoreProcessor();
  }

  @Bean
  @StepScope
  public ItemProcessor<MasterSubItemsXML, Collection<UpdatableRecord<?>>>
  masterSubItemsProcessor() {
    return new MasterSubItemsProcessor(entityIdRegistry);
  }

  @Bean
  @StepScope
  public ItemProcessor<ReleaseItemXML, ReleaseItemRecord> releaseItemCoreProcessor() {
    return new ReleaseItemCoreProcessor(entityIdRegistry);
  }

  @Bean
  @StepScope
  public ItemProcessor<ReleaseItemSubItemsXML, Collection<UpdatableRecord<?>>>
  releaseItemSubItemsProcessor() {
    return new ReleaseItemSubItemsProcessor(entityIdRegistry);
  }

  @Bean
  @StepScope
  public ItemProcessor<MasterMainReleaseXML, MasterRecord> masterMainReleaseItemProcessor() {
    return new MasterMainReleaseItemProcessor(entityIdRegistry);
  }
}
