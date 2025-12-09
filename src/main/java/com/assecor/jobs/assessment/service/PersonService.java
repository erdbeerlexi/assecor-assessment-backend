package com.assecor.jobs.assessment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.assecor.jobs.assessment.model.dto.Person;
import com.assecor.jobs.assessment.model.entity.PersonEntity;
import com.assecor.jobs.assessment.repository.PersonRepository;
import com.assecor.jobs.assessment.repository.PersonRepositoryCsv;
import com.assecor.jobs.assessment.repository.PersonRepositorySql;
import com.assecor.jobs.assessment.util.Mapper;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPersons() {
        return this.personRepository.findAll().stream().map(personEntity -> Mapper.mapPersonEntityToDto(personEntity)).toList();
    }

    public Person getPersonById(final int id) {
        Optional<PersonEntity> foundPerson = this.personRepository.findById(id);
        Person mappedPerson = foundPerson.isPresent() ? Mapper.mapPersonEntityToDto(foundPerson.get()) : null;

        return mappedPerson;
    }

    public List<Person> getPersonsByColor(final String color) {
         return this.personRepository.findByFavoriteColor(color).stream().map(personEntity -> Mapper.mapPersonEntityToDto(personEntity)).toList();
    }

    public Long createNewPerson(final Person person) {
        PersonEntity newPersonEntity = this.personRepository.save(Mapper.mapPersonDtoToEntity(person));

        return newPersonEntity.getId();
    }
}