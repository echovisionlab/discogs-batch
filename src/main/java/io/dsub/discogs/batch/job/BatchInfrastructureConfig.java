package io.dsub.discogs.batch.job;

import io.dsub.discogs.batch.dump.DiscogsDump;
import io.dsub.discogs.batch.dump.EntityType;
import io.dsub.discogs.batch.job.decider.MasterMainReleaseStepJobExecutionDecider;
import io.dsub.discogs.batch.job.listener.BatchListenerConfig;
import io.dsub.discogs.batch.job.processor.ItemProcessorConfig;
import io.dsub.discogs.batch.job.reader.DiscogsDumpItemReaderBuilder;
import io.dsub.discogs.batch.job.reader.ItemReaderConfig;
import io.dsub.discogs.batch.job.registry.DefaultEntityIdRegistry;
import io.dsub.discogs.batch.job.step.GlobalStepConfig;
import io.dsub.discogs.batch.job.tasklet.GenreStyleInsertionTasklet;
import io.dsub.discogs.batch.job.writer.ItemWriterConfig;
import io.dsub.discogs.batch.util.FileUtil;
import io.dsub.discogs.batch.util.SimpleFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@Import(
    value = {
        GlobalStepConfig.class,
        ItemReaderConfig.class,
        ItemProcessorConfig.class,
        ItemWriterConfig.class,
        BatchListenerConfig.class,
        GenreStyleInsertionTasklet.class
    })
public class BatchInfrastructureConfig {

  private Environment env;
  @Autowired
  public void setArgs(Environment env) {
    this.env = env;
  }

  @Bean
  public Map<EntityType, DiscogsDump> dumpMap() {
    return new HashMap<>();
  }

  @Bean
  public MasterMainReleaseStepJobExecutionDecider masterMainReleaseStepJobExecutionDecider(
      DefaultEntityIdRegistry registry) {
    return new MasterMainReleaseStepJobExecutionDecider(registry);
  }

  @Bean
  public FileUtil fileUtil() {
    boolean keep = getKeepFileValue();
    FileUtil fileUtil = SimpleFileUtil.builder().isTemporary(!keep).build();
    if (keep) {
      log.info("found keep file environment value. keeping file...");
    } else {
      log.info("keep file environment value not set. all files will be removed after the job.");
    }
    return fileUtil;
  }

  @Bean
  public DiscogsDumpItemReaderBuilder discogsDumpItemReaderBuilder() {
    return new DiscogsDumpItemReaderBuilder(fileUtil());
  }

  private boolean getKeepFileValue() {
    return env.getProperty("DISCOGS_BATCH_KEEP_FILE", Boolean.class, false);
  }
}
