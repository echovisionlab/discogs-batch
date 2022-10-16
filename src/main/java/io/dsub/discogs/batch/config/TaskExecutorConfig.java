package io.dsub.discogs.batch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import oshi.SystemInfo;

@Slf4j
@Configuration
public class TaskExecutorConfig {
  private static final SystemInfo SYSTEM_INFO = new SystemInfo();

  /**
   * Primary bean for batch processing. The core and max pool size is fit into same value, just as
   * the same as the core size of host <b>logical</b> processor count.
   *
   * <p>{@link ThreadPoolTaskExecutor#setWaitForTasksToCompleteOnShutdown(boolean)}} is set to
   * true, in case of additional tasks required after the actual job is doe.
   *
   * @return instance of {@link ThreadPoolTaskExecutor}.
   */
  @Bean
  public ThreadPoolTaskExecutor batchTaskExecutor() {
    int coreCount = getCoreCount();
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    taskExecutor.setCorePoolSize(coreCount);
    taskExecutor.setMaxPoolSize(coreCount);
    taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
    taskExecutor.afterPropertiesSet();
    taskExecutor.initialize();
    return taskExecutor;
  }

  private int getCoreCount() {
    return SYSTEM_INFO.getHardware().getProcessor().getLogicalProcessorCount();
  }
}
