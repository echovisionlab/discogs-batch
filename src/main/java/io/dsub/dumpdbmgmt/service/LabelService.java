package io.dsub.dumpdbmgmt.service;

import io.dsub.dumpdbmgmt.entity.Label;
import io.dsub.dumpdbmgmt.repository.LabelRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LabelService {

    LabelRepository labelRepository;

    public LabelService(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    public Label save(Label label) {
        return this.labelRepository.save(label);
    }

    public Iterable<Label> saveAll(Iterable<Label> labels) {
        return this.labelRepository.saveAll(labels);
    }


    public Label findById(Long id) {
        Optional<Label> label = this.labelRepository.findById(id);
        return label.orElse(null);
    }
}
