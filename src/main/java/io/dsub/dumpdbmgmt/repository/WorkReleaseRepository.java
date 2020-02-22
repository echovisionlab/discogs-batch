package io.dsub.dumpdbmgmt.repository;

import io.dsub.dumpdbmgmt.entity.intermed.WorkRelease;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkReleaseRepository extends MongoRepository<WorkRelease, Long> {
}
