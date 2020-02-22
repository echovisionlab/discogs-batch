package io.dsub.dumpdbmgmt.repository;

import io.dsub.dumpdbmgmt.entity.intermed.ArtistCredit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArtistCreditRepository extends MongoRepository<ArtistCredit, Long> {
}
