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

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("testsql")
public class PersonControllerSqlTest {
    @Autowired MockMvc mockMvc;

    @Test
    public void testGetAllPersons() throws Exception {
        String expectedJson = """
    [{"id": 1, "city": "Lauterecken", "favoriteColor": "blau", "firstname": "Hans", "lastname": "Müller", "zipCode": "67742"},
    {"id": 3, "city": "made up", "favoriteColor": "violett", "firstname": "Johnny", "lastname": "Johnson", "zipCode": "88888"},
    {"id": 2, "city": "Stralsund", "favoriteColor": "grün", "firstname": "Peter", "lastname": "Petersen", "zipCode": "18439"},
    {"id": 5, "city": "Hansstadt", "favoriteColor": "gelb", "firstname": "Jonas", "lastname": "Müller", "zipCode": "32323"},
    {"id": 4, "city": "made up too", "favoriteColor": "rot", "firstname": "Milly", "lastname": "Millenium", "zipCode": "77777"},
    {"id": 6, "city": "Japan", "favoriteColor": "türkis", "firstname": "Tastatur", "lastname": "Fujitsu", "zipCode": "42342"},
    {"id": 7, "city": "Schweden - ☀", "firstname": "Anders", "lastname": "Andersson", "favoriteColor": "grün", "zipCode": "32132"},
    {"id": 9, "city": "Woanders", "favoriteColor": "violett", "firstname": "Gerda", "lastname": "Gerber", "zipCode": "76535"},
    {"id": 8, "city": "Wasweißich", "favoriteColor": "blau", "firstname": "Bertram", "lastname": "Bart", "zipCode": "12313"},
    {"id": 10, "city": "Hierach", "favoriteColor": "grün", "firstname": "Klaus", "lastname": "Klaussen", "zipCode": "43246"}]
                """;;
        mockMvc.perform(MockMvcRequestBuilders.get("/persons")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedJson))
        ;
    }

    @Test
    public void testGetById() throws Exception {
        String expectedJson = """
                {"id": 1, "city": "Lauterecken", "favoriteColor": "blau", "firstname": "Hans", "lastname": "Müller", "zipCode": "67742"}
                """;
        mockMvc.perform(MockMvcRequestBuilders.get("/persons/1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedJson));

        mockMvc.perform(MockMvcRequestBuilders.get("/persons/11")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
    }

        @Test
    public void testGetByColor() throws Exception {
        String expectedJson = """
                [{"id": 1, "city": "Lauterecken", "favoriteColor": "blau", "firstname": "Hans", "lastname": "Müller", "zipCode": "67742"},
                {"id": 8, "city": "Wasweißich", "favoriteColor": "blau", "firstname": "Bertram", "lastname": "Bart", "zipCode": "12313"}]
                """;
        mockMvc.perform(MockMvcRequestBuilders.get("/persons/color/blau")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedJson));

        mockMvc.perform(MockMvcRequestBuilders.get("/persons/color/schwarz")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
    }
}
