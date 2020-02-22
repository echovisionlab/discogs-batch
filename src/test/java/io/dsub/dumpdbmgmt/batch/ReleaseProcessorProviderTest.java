package io.dsub.dumpdbmgmt.batch;

import io.dsub.dumpdbmgmt.entity.Release;
import io.dsub.dumpdbmgmt.xmlobj.XmlRelease;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
class ReleaseProcessorProviderTest {

    ItemProcessor<XmlRelease, Release> processor;
    ItemReaderProvider itemReaderProvider;

    public ReleaseProcessorProviderTest(
            @Qualifier(value = "syncReleaseProcessor") ItemProcessor<XmlRelease, Release> processor,
            @Qualifier(value = "itemReaderProvider") ItemReaderProvider itemReaderProvider) {
        this.processor = processor;
        this.itemReaderProvider = itemReaderProvider;
    }

    @Test
    void releaseProcessor() {
        CustomStaxEventItemReader<XmlRelease> releaseReader = itemReaderProvider.reader(XmlRelease.class);
        Integer count = 0;

        boolean hasNext = true;

        releaseReader.open(new ExecutionContext());
        List<Release> releaseList = new ArrayList<>();


        while (hasNext) {
            try {
                XmlRelease release = releaseReader.read();
                if (release == null) {
                    hasNext = false;
                    releaseReader.close();
                    log.info("releaseReader -> Count completed with value {}", count);
                } else {
                    count++;
                    releaseList.add(processor.process(release));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        releaseList.forEach(entry -> log.info("Processed -> {}", entry.toString().trim()));
    }
}
