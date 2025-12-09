package com.assecor.jobs.assessment.repository;

import java.util.List;
import java.util.Optional;

import com.assecor.jobs.assessment.model.entity.PersonEntity;

public interface PersonRepository {
    List<PersonEntity> findAll();
    Optional<PersonEntity> findById(final int id);
    List<PersonEntity> findByFavoriteColor(final String color);
    PersonEntity save(final PersonEntity person);
}
