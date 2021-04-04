package com.kenuiworks.frameworkbox.service;

import com.kenuiworks.frameworkbox.dto.FrameworkDTO;
import com.kenuiworks.frameworkbox.exception.FrameworkNotFoundException;
import com.kenuiworks.frameworkbox.model.Framework;
import com.kenuiworks.frameworkbox.repository.FrameWorkRepository;
import io.micronaut.http.HttpResponse;

import java.util.List;

public interface FrameWorkService {

    public FrameworkDTO createFramework(FrameworkDTO frameworkDTO);

    List<Framework> findAll();

    Framework findByName(String name) throws FrameworkNotFoundException;

    void deleteById(Long id) throws FrameworkNotFoundException;

    FrameworkDTO incrementMonthOfExperience(Long id, Integer experience) throws FrameworkNotFoundException;
}
