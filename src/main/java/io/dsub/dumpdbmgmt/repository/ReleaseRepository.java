package io.dsub.dumpdbmgmt.repository;

import io.dsub.dumpdbmgmt.entity.Release;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReleaseRepository extends MongoRepository<Release, Long> {

}
