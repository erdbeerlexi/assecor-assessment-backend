package com.assecor.jobs.assessment.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Copyright 2025 (C) Alexandra Fengler
 * 
 * Author: Alexandra Fengler
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("testsql")
public class PersonControllerSqlTest {
    @Autowired MockMvc mockMvc;

    @Test
    public void testGetAllPersons() throws Exception {
        String expectedJson = """
                [{"id": 1, "city": "Lauterecken", "favoriteColor": "blau", "name": "Hans", "lastname": "Müller", "zipCode": "67742"},
                {"id": 3, "city": "made up", "favoriteColor": "violett", "name": "Johnny", "lastname": "Johnson", "zipCode": "88888"},
                {"id": 2, "city": "Stralsund", "favoriteColor": "grün", "name": "Peter", "lastname": "Petersen", "zipCode": "18439"},
                {"id": 5, "city": "Hansstadt", "favoriteColor": "gelb", "name": "Jonas", "lastname": "Müller", "zipCode": "32323"},
                {"id": 4, "city": "made up too", "favoriteColor": "rot", "name": "Milly", "lastname": "Millenium", "zipCode": "77777"},
                {"id": 6, "city": "Japan", "favoriteColor": "türkis", "name": "Tastatur", "lastname": "Fujitsu", "zipCode": "42342"},
                {"id": 7, "city": "Schweden - ☀", "name": "Anders", "lastname": "Andersson", "favoriteColor": "grün", "zipCode": "32132"},
                {"id": 9, "city": "Woanders", "favoriteColor": "violett", "name": "Gerda", "lastname": "Gerber", "zipCode": "76535"},
                {"id": 8, "city": "Wasweißich", "favoriteColor": "blau", "name": "Bertram", "lastname": "Bart", "zipCode": "12313"},
                {"id": 10, "city": "Hierach", "favoriteColor": "grün", "name": "Klaus", "lastname": "Klaussen", "zipCode": "43246"}]
                """;

        mockMvc.perform(MockMvcRequestBuilders.get("/persons")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedJson))
        ;
    }

    @Test
    public void testGetById() throws Exception {
        String expectedJson = """
                {"id": 1, "city": "Lauterecken", "favoriteColor": "blau", "name": "Hans", "lastname": "Müller", "zipCode": "67742"}
                """;

        mockMvc.perform(MockMvcRequestBuilders.get("/persons/1").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedJson));

        mockMvc.perform(MockMvcRequestBuilders.get("/persons/21").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
    }

    @Test
    public void testGetByColor() throws Exception {
        String expectedJson = """
                [{"id": 1, "city": "Lauterecken", "favoriteColor": "blau", "name": "Hans", "lastname": "Müller", "zipCode": "67742"},
                {"id": 8, "city": "Wasweißich", "favoriteColor": "blau", "name": "Bertram", "lastname": "Bart", "zipCode": "12313"}]
                """;

        mockMvc.perform(MockMvcRequestBuilders.get("/persons/color/blau").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedJson));

        mockMvc.perform(MockMvcRequestBuilders.get("/persons/color/schwarz").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
    }

    @Test
    public void testPostPerson() throws Exception {
        String expectedJson = """
                [{"id": 1, "city": "Lauterecken", "favoriteColor": "blau", "name": "Hans", "lastname": "Müller", "zipCode": "67742"},
                {"id": 8, "city": "Wasweißich", "favoriteColor": "blau", "name": "Bertram", "lastname": "Bart", "zipCode": "12313"},
                {"id": 11, "city": "Testhausen", "favoriteColor": "blau", "name": "Test", "lastname": "Test", "zipCode": "10365"}]
                """;
        String postContent = """
                {"city": "Testhausen", "favoriteColor": "blau", "name": "Test", "lastname": "Test", "zipCode": "10365"}
                """;
        String expectedJsonId = """
                {"id": 11, "city": "Testhausen", "favoriteColor": "blau", "name": "Test", "lastname": "Test", "zipCode": "10365"}
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/persons").contentType("application/json").content(postContent))
            .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.get("/persons/color/blau").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedJson));

        mockMvc.perform(MockMvcRequestBuilders.post("/persons").contentType("application/json").content(postContent))
            .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/persons/color/blau").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedJson));

        mockMvc.perform(MockMvcRequestBuilders.get("/persons/11").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedJsonId));
    }
}
