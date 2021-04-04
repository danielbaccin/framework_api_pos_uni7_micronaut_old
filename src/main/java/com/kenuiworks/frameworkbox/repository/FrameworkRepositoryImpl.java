package com.kenuiworks.frameworkbox.repository;

import com.kenuiworks.frameworkbox.domain.Genre;
import com.kenuiworks.frameworkbox.exception.FrameworkNotFoundException;
import com.kenuiworks.frameworkbox.model.Framework;
import io.micronaut.transaction.annotation.ReadOnly;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class FrameworkRepositoryImpl implements FrameWorkRepository{

    private final EntityManager entityManager;

    public FrameworkRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @ReadOnly
    public List<Framework> findAll() {
        String query = "SELECT f FROM Framework as f";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    @ReadOnly
    public Framework findByName(String name) throws FrameworkNotFoundException {
        String query = "SELECT f FROM Framework as f WHERE f.name = ':name'";
        Query queryCreated = entityManager.createQuery(query.replace(":name", name));
        List resultList = queryCreated.getResultList();
        if(resultList.isEmpty())
            throw new FrameworkNotFoundException(name);
        return (Framework) resultList.get(0);
    }

    @Override
    public Framework save(Framework framework) {
        entityManager.persist(framework);
        return framework;
    }

    @Override
    public Optional<Framework> findById(Long id) {
        return Optional.of(entityManager.find(Framework.class, id));
    }

    @Override
    public void delete(Framework framework) {
        entityManager.remove(framework);
    }
}
