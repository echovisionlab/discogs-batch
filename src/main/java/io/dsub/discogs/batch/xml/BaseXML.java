package io.dsub.discogs.batch.xml;

import org.jooq.UpdatableRecord;

public interface BaseXML<T extends UpdatableRecord<T>> {

  T buildRecord();
}
