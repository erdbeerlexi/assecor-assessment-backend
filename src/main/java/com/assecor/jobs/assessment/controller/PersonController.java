package com.assecor.jobs.assessment.controller;

import java.net.URI;
import java.net.URISyntaxException;
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


/**
 * Copyright 2025 (C) Alexandra Fengler
 * 
 * Author: Alexandra Fengler
 */
@RestController
@RequestMapping("/persons")
public class PersonController {
    private PersonService personService;

    @Autowired
    public PersonController(final PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = this.personService.getAllPersons();
        ResponseEntity<List<Person>> response = null;

        if (persons.isEmpty()) {
            response = ResponseEntity.noContent().build();
        } else {
            response = ResponseEntity.ok(persons);
        }

        return response;
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Person> getById(@PathVariable("id") final int id) {
        Person person = this.personService.getPersonById(id);
        ResponseEntity<Person> response = null;

        if (person != null) {
            response = ResponseEntity.ok(person);
        } else {
            response = ResponseEntity.noContent().build();
        }

        return response;
    }

    @GetMapping(path = "/color/{color}", produces = "application/json")
    public ResponseEntity<List<Person>> getById(@PathVariable("color") final String color) {
        List<Person> persons = this.personService.getPersonsByColor(color);
        ResponseEntity<List<Person>> response = null;

        if (persons.isEmpty()) {
            response = ResponseEntity.noContent().build();
        } else {
            response = ResponseEntity.ok(persons);
        }

        return response;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> postPerson(@RequestBody final Person person) {
        ResponseEntity<?> response = null;

        if (this.personService.doesPersonExists(person)) {
            response = ResponseEntity.ok(null);
        } else {
            Long newId = this.personService.createNewPerson(person);
            
            try {
                response = ResponseEntity.created(new URI("/persons/" + newId)).build();
            } catch (URISyntaxException e) {
                response = ResponseEntity.internalServerError().build();
            }
        }

        return response;
    }
}