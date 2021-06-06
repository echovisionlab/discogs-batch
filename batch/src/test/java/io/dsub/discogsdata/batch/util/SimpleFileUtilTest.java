package io.dsub.discogsdata.batch.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import ch.qos.logback.classic.Level;
import io.dsub.discogsdata.batch.testutil.LogSpy;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.RegisterExtension;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SimpleFileUtilTest {

  SimpleFileUtil fileUtil;

  @RegisterExtension
  LogSpy logSpy = new LogSpy();

  @BeforeEach
  void prepare() {
    fileUtil = SimpleFileUtil.builder()
        .appDirectory(RandomString.make())
        .build();
  }

  @AfterEach
  void cleanUp() {
    try {
      Path appDir = fileUtil.getAppDirectory(false);
      if (!Files.exists(appDir)) {
        return;
      }
      try (Stream<Path> files = Files.walk(appDir)) {
        List<Boolean> lists = files.sorted(Comparator.reverseOrder())
            .map(Path::toFile)
            .map(File::delete)
            .collect(Collectors.toList());
        assertThat(lists).doesNotContain(false);
      }
      this.fileUtil = null;
    } catch (IOException e) {
      fail(e);
    }
  }

  @Test
  void whenClearAll__ShouldClearEntirePath() throws InterruptedException {
    try {
      List<Path> filePaths = new ArrayList<>();
      Path appDir = fileUtil.getAppDirectory(true);
      filePaths.add(appDir);

      for (int i = 0; i < 10; i++) {
        Path p = fileUtil.getFilePath(RandomString.make(), true);
        filePaths.add(p);
      }

      fileUtil.clearAll();

      for (Path filePath : filePaths) {
        assertThat(filePath).doesNotExist();
      }

    } catch (IOException e) {
      fail(e);
    } finally {
      Thread.sleep(500);
    }
  }

  @Test
  void givenFileNameAndFalse__WhenGetFilePath__ShouldNotGenerateGivenFile() {
    try {
      Path p = fileUtil.getFilePath("test-name", false);
      assertThat(p)
          .doesNotExist()
          .hasFileName("test-name");
    } catch (IOException e) {
      fail(e);
    }
  }

  @Test
  void givenFileNameAndTrue__WhenGetFilePath__ShouldGenerateGivenFile() {
    try {
      Path p = fileUtil.getFilePath("test-name", true);
      assertThat(p)
          .exists()
          .hasFileName("test-name");
    } catch (IOException e) {
      fail(e);
    }
  }

  @Test
  void givenFalse__WhenGetAppDirectory__ShouldNotGenerate() {
    try {
      Path appDir = fileUtil.getAppDirectory(false);
      assertThat(appDir).doesNotExist();
    } catch (IOException e) {
      fail(e);
    }
  }

  @Test
  void givenTrue__WhenGetAppDirectory__ShouldGenerate() {
    try {
      Path appDir = fileUtil.getAppDirectory(true);
      assertThat(appDir).exists();
    } catch (IOException e) {
      fail(e);
    }
  }

  @Test
  void whenDefaultConstructorCalled__ShouldHaveProperDefaultValues() {
    // when
    this.fileUtil = SimpleFileUtil.builder().build();
    // then
    assertThat(fileUtil.getAppDirectory()).isEqualTo(FileUtil.DEFAULT_APP_DIR);
    assertThat(fileUtil.isTemporary()).isFalse();
  }

  @Test
  void whenBuilderCalled__ShouldBehaveAsExpected() {
    // when
    this.fileUtil = SimpleFileUtil.builder()
        .isTemporary(true)
        .appDirectory("hello")
        .build();
    // then
    assertThat(this.fileUtil.isTemporary()).isTrue();
    assertThat(this.fileUtil.getAppDirectory()).isEqualTo("hello");
  }
}