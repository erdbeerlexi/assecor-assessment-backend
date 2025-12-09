package com.assecor.jobs.assessment.repository;

import java.util.List;

import com.assecor.jobs.assessment.model.entity.PersonEntity;

public interface PersonRepository {
    List<PersonEntity> getAllPersons();
    PersonEntity getPersonById(final int id);
    List<PersonEntity> getPersonsByColor(final String color);
    int createNewPerson(final PersonEntity person);
}
