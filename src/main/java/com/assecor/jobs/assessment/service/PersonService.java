package com.assecor.jobs.assessment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assecor.jobs.assessment.model.dto.Person;
import com.assecor.jobs.assessment.model.entity.PersonEntity;
import com.assecor.jobs.assessment.model.enums.Color;
import com.assecor.jobs.assessment.repository.PersonRepository;
import com.assecor.jobs.assessment.util.Mapper;

/**
 * Copyright 2025 (C) Alexandra Fengler
 * 
 * Author: Alexandra Fengler
 */
@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * This method loads all persons which are saved in the system.
     * @return A list with all persons.
     */
    public List<Person> getAllPersons() {
        return this.personRepository.findAll()
            .stream()
            .map(personEntity -> Mapper.mapPersonEntityToDto(personEntity)).toList();
    }

    /**
     * This method returns the person found for the given id or null if no person was found.
     * @param id The id of the person searched for
     * @return The found person or null
     */
    public Person getPersonById(final int id) {
        Optional<PersonEntity> foundPerson = this.personRepository.findById(id);
        Person mappedPerson = foundPerson.isPresent() ? Mapper.mapPersonEntityToDto(foundPerson.get()) : null;

        return mappedPerson;
    }

    /**
     * This method returns a list of persons who have the given color as favorite color.
     * @param color The favorite color of the persons we want to find
     * @return A list of persons which were found. Can be empty.
     */
    public List<Person> getPersonsByColor(final String color) {
        Color searchColor = Color.getByColorName(color);

        return this.personRepository.findByFavoriteColor(searchColor)
            .stream()
            .map(personEntity -> Mapper.mapPersonEntityToDto(personEntity)).toList();
    }

    /**
     * This method creates a new person if it not already exists.
     * @param person The person which will be created
     * @return The id of the new created person.
     */
    public Long createNewPerson(final Person person) {
        PersonEntity personEntity = this.findPersonEntity(person);

        if (personEntity == null) {
            personEntity = this.personRepository.save(Mapper.mapPersonDtoToEntity(person));
        }

        return personEntity.getId();
    }

    /**
     * This method checks if the person already exists
     * @param person
     * @return
     */
    public boolean doesPersonExists(final Person person) {
        PersonEntity personEntity = Mapper.mapPersonDtoToEntity(person);

        List<PersonEntity> personEntities = this.personRepository.findAll();

        return personEntities.contains(personEntity);
    }

    /**
     * This method finds a person entity by the person dto.
     * @param person The person we want to find the entity for
     * @return The found entity or null
     */
    private PersonEntity findPersonEntity(final Person person) {
        PersonEntity personEntity = Mapper.mapPersonDtoToEntity(person);
        PersonEntity foundPersonEntity = null;
        List<PersonEntity> personEntities = this.personRepository.findAll();

        for (PersonEntity personEntity2: personEntities) {
            if (personEntity2.equals(personEntity)) {
                foundPersonEntity = personEntity2;
                break;
            }
        }

        return foundPersonEntity;
    }
}