package io.dsub.dumpdbmgmt.repository;

import io.dsub.dumpdbmgmt.entity.Label;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LabelRepository extends MongoRepository<Label, Long> {
}
