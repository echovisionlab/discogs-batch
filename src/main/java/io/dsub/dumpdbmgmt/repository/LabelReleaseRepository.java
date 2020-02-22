package io.dsub.dumpdbmgmt.repository;

import io.dsub.dumpdbmgmt.entity.intermed.LabelRelease;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LabelReleaseRepository extends MongoRepository<LabelRelease, Long> {
}
