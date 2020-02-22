package io.dsub.dumpdbmgmt.service;

import io.dsub.dumpdbmgmt.entity.intermed.WorkRelease;
import io.dsub.dumpdbmgmt.repository.WorkReleaseRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class WorkReleaseService {
    private WorkReleaseRepository repository;

    public WorkReleaseService(WorkReleaseRepository repository) {
        this.repository = repository;
    }

    public WorkRelease save(WorkRelease workRelease) {
        return this.repository.save(workRelease);
    }

    public Iterable<WorkRelease> saveAll(WorkRelease... workReleases) {
        return this.repository.saveAll(Arrays.asList(workReleases));
    }


    public void delete(WorkRelease workRelease) {
        this.repository.delete(workRelease);
    }

    public void deleteAll(WorkRelease... workReleases) {
        this.repository.deleteAll(Arrays.asList(workReleases));
    }
}
