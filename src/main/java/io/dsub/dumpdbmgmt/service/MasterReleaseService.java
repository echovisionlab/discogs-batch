package io.dsub.dumpdbmgmt.service;

import io.dsub.dumpdbmgmt.entity.MasterRelease;
import io.dsub.dumpdbmgmt.repository.MasterReleaseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MasterReleaseService {

    MasterReleaseRepository repository;

    public MasterReleaseService(MasterReleaseRepository repository) {
        this.repository = repository;
    }

    public MasterRelease save(MasterRelease masterRelease) {
        return this.repository.save(masterRelease);
    }

    public Iterable<MasterRelease> saveAll(Iterable<MasterRelease> masterReleases) {
        return this.repository.saveAll(masterReleases);
    }

    public MasterRelease findById(Long id) {
        Optional<MasterRelease> masterRelease = this.repository.findById(id);
        return masterRelease.orElse(null);
    }
}
