package com.assecor.jobs.assessment.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assecor.jobs.assessment.model.Person;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @RequestMapping(value = "", method = RequestMethod.GET, produces = { "application/json" })
    public List<Person> getPersons() {
        List<Person> persons = new ArrayList<>();

        persons.add(new Person("Müller", "Hans", "10365", "Berlin", 1));

        return persons;
    }
}