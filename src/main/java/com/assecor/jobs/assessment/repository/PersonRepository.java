package com.assecor.jobs.assessment.repository;

import java.util.List;
import java.util.Optional;

import com.assecor.jobs.assessment.model.entity.PersonEntity;
import com.assecor.jobs.assessment.model.enums.Color;

/**
 * Copyright 2025 (C) Alexandra Fengler
 * 
 * Author: Alexandra Fengler
 */
public interface PersonRepository {
    /**
     * This method finds all person entities in the system
     * @return A list with all person entities
     */
    List<PersonEntity> findAll();

    /**
     * This method finds a person entity by id
     * @param id the id of the person enitity
     * @return An optional with the found person entity or an empty optional.
     */
    Optional<PersonEntity> findById(final int id);

    /**
     * This method finds all person entities who have the given color as favorite color.
     * @param color The searched for favorite color
     * @return A list of all found person entities. Can be empty
     */
    List<PersonEntity> findByFavoriteColor(final Color color);

    /**
     * Saves the given person entity into the system.
     * @param person The to be saved person
     * @return The person entity after saving it.
     */
    PersonEntity save(final PersonEntity person);
}
