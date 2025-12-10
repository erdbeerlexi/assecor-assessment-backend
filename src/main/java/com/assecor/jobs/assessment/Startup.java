package com.assecor.jobs.assessment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.assecor.jobs.assessment.model.dto.Person;
import com.assecor.jobs.assessment.model.enums.Color;
import com.assecor.jobs.assessment.service.PersonService;

/**
 * Copyright 2025 (C) Alexandra Fengler
 * 
 * Author: Alexandra Fengler
 */
@Component
public class Startup {
	private PersonService personService;
    @Value("${assessment.datasourceType}")
    private String datasourceType;
    @Value("${assessment.csvPath}")
    private String csvFilePath;
    @Value("${assessment.originalCsvPath}")
    private String originalCsvFilePath;
    @Value("${assessment.copyOriginalFile}")
    private boolean copyOriginalCsvFile;
    private Logger log = LoggerFactory.getLogger(Startup.class);

    @Autowired
    public Startup(final PersonService personService) {
        this.personService = personService;
    }
    
    @EventListener(ApplicationReadyEvent.class)
    public void initDatabase() {
        if (datasourceType.equalsIgnoreCase("db")) {
            this.log.info("On Startup - will create now the initial data for the application");
            this.createPersonEntity("Müller", "Hans", "67742", "Lauterecken", Color.BLUE);
            this.createPersonEntity("Petersen", "Peter", "18439", "Stralsund", Color.GREEN);
            this.createPersonEntity("Johnson", "Johnny", "88888", "made up", Color.VIOLETT);
            this.createPersonEntity("Millenium", "Milly", "77777", "made up too", Color.RED);
            this.createPersonEntity("Müller", "Jonas", "32323", "Hansstadt", Color.YELLOW);
            this.createPersonEntity("Fujitsu", "Tastatur", "42342", "Japan", Color.TURQUOISE);
            this.createPersonEntity("Andersson", "Anders", "32132", "Schweden - ☀", Color.GREEN);
            this.createPersonEntity("Bart", "Bertram", "12313", "Wasweißich", Color.BLUE);
            this.createPersonEntity("Gerber", "Gerda", "76535", "Woanders", Color.VIOLETT);
            this.createPersonEntity("Klaussen", "Klaus", "43246", "Hierach", Color.GREEN);
        } else { //this needs to be an if-else if there will be a third datasourceType
            if (copyOriginalCsvFile) {
                this.log.info("On Startup - we use csv and want to copy the original file to the used csv");
                Path copyPath = Paths.get(csvFilePath);
                Path originalPath = Paths.get(originalCsvFilePath);
                try {
                    Files.copy(originalPath, copyPath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    this.log.error("Could not copy the original file because: " + e.getMessage());
                }
            }
        }
    }

    private void createPersonEntity(final String lastname, final String firstName, final String zipCode, final String city, final Color color) {
        Person person = new Person(null, lastname, firstName, zipCode, city, color);
        if (!this.personService.doesPersonExists(person)) {
            this.personService.createNewPerson(person);
        }
    }
}
