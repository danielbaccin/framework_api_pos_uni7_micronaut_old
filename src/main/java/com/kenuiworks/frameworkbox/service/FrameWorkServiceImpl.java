package com.kenuiworks.frameworkbox.service;

import com.kenuiworks.frameworkbox.dto.FrameworkDTO;
import com.kenuiworks.frameworkbox.exception.FrameworkNotFoundException;
import com.kenuiworks.frameworkbox.model.Framework;
import com.kenuiworks.frameworkbox.repository.FrameWorkRepository;
import io.micronaut.http.HttpResponse;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;

@Singleton
public class FrameWorkServiceImpl implements FrameWorkService{

    protected FrameWorkRepository repository;

    public FrameWorkServiceImpl(FrameWorkRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public FrameworkDTO createFramework(FrameworkDTO frameworkDTO) {
        Framework frameworkSaved = repository.save(frameworkDTO.toModel());
        return frameworkSaved.toDto();
    }

    @Override
    public List<Framework> findAll() {
        return repository.findAll();
    }

    @Override
    public Framework findByName(String name) throws FrameworkNotFoundException {
        return repository.findByName(name);
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws FrameworkNotFoundException {
        Framework framework = verifyIfIsAlreadyRegisteredWithId(id);
        repository.delete(framework);

    }

    @Override
    @Transactional
    public FrameworkDTO incrementMonthOfExperience(Long id, Integer experience) throws FrameworkNotFoundException {
        Framework framework = verifyIfIsAlreadyRegisteredWithId(id);
        framework.setMonthsOfExperience(framework.getMonthsOfExperience() + experience);
        repository.save(framework);
        return framework.toDto();

    }

    private Framework verifyIfIsAlreadyRegisteredWithId(Long id) throws FrameworkNotFoundException {
        return repository.findById(id).orElseThrow(() -> new FrameworkNotFoundException(id));
    }
}
