package com.assecor.jobs.assessment.repository;

import java.util.List;


import com.assecor.jobs.assessment.model.Person;

public interface PersonRepository {
    List<Person> getAllPersons();
    Person getPersonById(final int id);
    List<Person> getPersonsByColor(final String color);
    int createNewPerson(final Person person);
}
