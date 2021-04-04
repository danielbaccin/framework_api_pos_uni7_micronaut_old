package com.kenuiworks.frameworkbox.repository;

import com.kenuiworks.frameworkbox.exception.FrameworkNotFoundException;
import com.kenuiworks.frameworkbox.model.Framework;

import java.util.List;
import java.util.Optional;

public interface FrameWorkRepository {
    List<Framework> findAll();

    Framework findByName(String name) throws FrameworkNotFoundException;

    Framework save(Framework toModel);

    Optional<Framework> findById(Long id);

    void delete(Framework framework);
}
