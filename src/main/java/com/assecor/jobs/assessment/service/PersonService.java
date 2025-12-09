package com.assecor.jobs.assessment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assecor.jobs.assessment.model.dto.Person;
import com.assecor.jobs.assessment.model.entity.PersonEntity;
import com.assecor.jobs.assessment.repository.PersonRepository;
import com.assecor.jobs.assessment.util.Mapper;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPersons() {
        return this.personRepository.getAllPersons().stream().map(personEntity -> Mapper.mapPersonEntityToDto(personEntity)).toList();
    }

    public Person getPersonById(final int id) {
        PersonEntity foundPerson = this.personRepository.getPersonById(id);
        Person mappedPerson = foundPerson != null ? Mapper.mapPersonEntityToDto(foundPerson) : null;

        return mappedPerson;
    }

    public List<Person> getPersonsByColor(final String color) {
         return this.personRepository.getPersonsByColor(color).stream().map(personEntity -> Mapper.mapPersonEntityToDto(personEntity)).toList();
    }

    public Long createNewPerson(final Person person) {
        return this.personRepository.createNewPerson(Mapper.mapPersonDtoToEntity(person));
    }
}