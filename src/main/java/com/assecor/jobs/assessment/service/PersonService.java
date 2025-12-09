package com.assecor.jobs.assessment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assecor.jobs.assessment.model.Person;
import com.assecor.jobs.assessment.repository.PersonRepository;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPersons() {
        return this.personRepository.getAllPersons();
    }

    public Person getPersonById(final int id) {
        return this.personRepository.getPersonById(id);
    }

    public List<Person> getPersonsByColor(final String color) {
         return this.personRepository.getPersonsByColor(color);
    }

    public int createNewPerson(final Person person) {

    }
}