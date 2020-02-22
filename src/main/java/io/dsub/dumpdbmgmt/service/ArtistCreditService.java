package io.dsub.dumpdbmgmt.service;

import io.dsub.dumpdbmgmt.entity.intermed.ArtistCredit;
import io.dsub.dumpdbmgmt.repository.ArtistCreditRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ArtistCreditService {
    private ArtistCreditRepository artistCreditRepository;

    public ArtistCreditService(ArtistCreditRepository artistCreditRepository) {
        this.artistCreditRepository = artistCreditRepository;
    }

    public ArtistCredit save(ArtistCredit artistCredit) {
        return this.artistCreditRepository.save(artistCredit);
    }

    public Iterable<ArtistCredit> saveAll(ArtistCredit... artistCredits) {
        return this.artistCreditRepository.saveAll(Arrays.asList(artistCredits));
    }

    public void delete(ArtistCredit artistCredit) {
        this.artistCreditRepository.delete(artistCredit);
    }

    public void deleteAll(ArtistCredit... artistCredits) {
        this.artistCreditRepository.deleteAll(Arrays.asList(artistCredits));
    }
}
