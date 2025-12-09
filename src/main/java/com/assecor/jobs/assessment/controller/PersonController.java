package com.assecor.jobs.assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assecor.jobs.assessment.model.dto.Person;
import com.assecor.jobs.assessment.service.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private PersonService personService;

    @Autowired
    public PersonController(final PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Person>> getPersons() {
        List<Person> persons = this.personService.getAllPersons();
        ResponseEntity<List<Person>> response;

        if (persons.isEmpty()) {
            response = ResponseEntity.noContent().build();
        } else {
            response = ResponseEntity.ok(persons);
        }

        return response;
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Person> getById(@PathVariable("id") final int id) {
        System.out.println("id for searching: " + id);
        Person person = this.personService.getPersonById(id);
        ResponseEntity<Person> response;

        if (person != null) {
            System.out.println("found person with id" + person.getId());
            response = ResponseEntity.ok(person);
        } else {
            System.out.println("found no person");
            response = ResponseEntity.noContent().build();
        }

        return response;
    }

    @GetMapping(path = "/color/{color}", produces = "application/json")
    public ResponseEntity<List<Person>> getById(@PathVariable("color") final String color) {
        List<Person> persons = this.personService.getPersonsByColor(color);
        ResponseEntity<List<Person>> response;

        if (persons.isEmpty()) {
            response = ResponseEntity.noContent().build();
        } else {
            response = ResponseEntity.ok(persons);
        }

        return response;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Long> postPerson(@RequestBody final Person person) {
        System.out.println("new person: " + person.getFirstname() + " " + person.getLastname());
        Long newId = this.personService.createNewPerson(person);
        return ResponseEntity.ok(newId);
    }
}