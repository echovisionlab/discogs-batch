package io.dsub.dumpdbmgmt.service;

import io.dsub.dumpdbmgmt.entity.Release;
import io.dsub.dumpdbmgmt.repository.ReleaseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReleaseService {

    private ReleaseRepository releaseRepository;

    public ReleaseService(ReleaseRepository releaseRepository) {
        this.releaseRepository = releaseRepository;
    }

    public Release save(Release release) {
        return this.releaseRepository.save(release);
    }

    public Iterable<Release> saveAll(Iterable<Release> releases) {
        return this.releaseRepository.saveAll(releases);
    }

    public Release findById(Long id) {
        Optional<Release> release = this.releaseRepository.findById(id);
        return release.orElse(null);
    }
}
