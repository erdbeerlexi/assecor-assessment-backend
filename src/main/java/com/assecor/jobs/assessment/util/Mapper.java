package com.assecor.jobs.assessment.util;

import com.assecor.jobs.assessment.model.dto.Person;
import com.assecor.jobs.assessment.model.entity.PersonEntity;

/**
 * Copyright 2025 (C) Alexandra Fengler
 * 
 * Author: Alexandra Fengler
 */
public class Mapper {
    public static Person mapPersonEntityToDto(final PersonEntity personEntity) {
        return new Person(personEntity.getId(), personEntity.getLastname(), personEntity.getFirstname(),
            personEntity.getZipCode(), personEntity.getCity(), personEntity.getFavoriteColor());
    }

    public static PersonEntity mapPersonDtoToEntity(final Person person) {
        return new PersonEntity(person.getId(), person.getLastname(), person.getName(), person.getZipCode(),
            person.getCity(), person.getFavoriteColor());
    }
}
