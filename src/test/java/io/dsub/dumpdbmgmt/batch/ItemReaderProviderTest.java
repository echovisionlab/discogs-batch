package io.dsub.dumpdbmgmt.batch;

import io.dsub.dumpdbmgmt.xmlobj.XmlArtist;
import io.dsub.dumpdbmgmt.xmlobj.XmlLabel;
import io.dsub.dumpdbmgmt.xmlobj.XmlMaster;
import io.dsub.dumpdbmgmt.xmlobj.XmlRelease;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
class ItemReaderProviderTest {

    ItemReaderProvider itemReaderProvider;

    public ItemReaderProviderTest(
            @Qualifier(value = "itemReaderProvider") ItemReaderProvider itemReaderProvider) {
        this.itemReaderProvider = itemReaderProvider;
    }

    @Test
    void readerTest() throws Exception {
        CustomStaxEventItemReader<XmlRelease> releaseReader = itemReaderProvider.reader(XmlRelease.class);
        Integer count = 0;

        boolean hasNext = true;

        releaseReader.open(new ExecutionContext());

        while (hasNext) {
            XmlRelease release = releaseReader.read();
            if (release == null) {
                hasNext = false;
                releaseReader.close();
                log.info("releaseReader -> Count completed with value {}", count);
            } else {
                count++;
            }
        }
        assertEquals(18, count);

        CustomStaxEventItemReader<XmlLabel> labelReader = itemReaderProvider.reader(XmlLabel.class);
        count = 0;
        hasNext = true;

        labelReader.open(new ExecutionContext());
        while (hasNext) {
            XmlLabel label = labelReader.read();
            if (label == null) {
                hasNext = false;
                labelReader.close();
                log.info("labelReader -> Count completed with value {}", count);
            } else {
                count++;
            }
        }

        CustomStaxEventItemReader<XmlArtist> artistReader = itemReaderProvider.reader(XmlArtist.class);
        count = 0;
        hasNext = true;

        artistReader.open(new ExecutionContext());
        while (hasNext) {
            XmlArtist artist = artistReader.read();
            if (artist == null) {
                hasNext = false;
                artistReader.close();
                log.info("artistReader -> Count completed with value {}", count);
            } else {
                count++;
            }
        }

        CustomStaxEventItemReader<XmlMaster> masterReader = itemReaderProvider.reader(XmlMaster.class);
        count = 0;
        hasNext = true;

        masterReader.open(new ExecutionContext());
        while (hasNext) {
            XmlMaster master = masterReader.read();
            if (master == null) {
                hasNext = false;
                masterReader.close();
                log.info("masterReader -> Count completed with value {}", count);
            } else {
                count++;
            }
        }

    }
}
