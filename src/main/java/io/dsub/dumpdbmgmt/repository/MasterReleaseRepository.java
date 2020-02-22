package io.dsub.dumpdbmgmt.repository;

import io.dsub.dumpdbmgmt.entity.MasterRelease;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MasterReleaseRepository extends MongoRepository<MasterRelease, Long> {
}
