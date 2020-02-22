package io.dsub.dumpdbmgmt.service;

import io.dsub.dumpdbmgmt.entity.intermed.LabelRelease;
import io.dsub.dumpdbmgmt.repository.LabelReleaseRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class LabelReleaseService {

    private LabelReleaseRepository repository;

    public LabelReleaseService(LabelReleaseRepository repository) {
        this.repository = repository;
    }

    public LabelRelease save(LabelRelease labelRelease) {
        return this.repository.save(labelRelease);
    }

    public Iterable<LabelRelease> saveAll(LabelRelease... labelReleases) {
        return this.repository.saveAll(Arrays.asList(labelReleases));
    }

    public void delete(LabelRelease labelRelease) {
        this.repository.delete(labelRelease);
    }

    public void deleteAll(LabelRelease... labelReleases) {
        this.repository.deleteAll(Arrays.asList(labelReleases));
    }
}
