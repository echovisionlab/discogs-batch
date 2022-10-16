package io.dsub.discogs.batch.job.processor;

import io.dsub.discogs.batch.xml.master.MasterXML;
import io.dsub.discogs.batch.util.ReflectionUtil;
import io.dsub.discogs.jooq.tables.records.MasterRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;

@RequiredArgsConstructor
public class MasterCoreProcessor implements ItemProcessor<MasterXML, MasterRecord> {

  @Override
  public MasterRecord process(MasterXML master) throws Exception {
    if (master.getId() == null || master.getId() < 1) {
      return null;
    }
    ReflectionUtil.normalizeStringFields(master);
    return master.buildRecord();
  }
}
