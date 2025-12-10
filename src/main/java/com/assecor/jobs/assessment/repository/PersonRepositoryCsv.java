package com.assecor.jobs.assessment.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.assecor.jobs.assessment.model.enums.Color;
import com.assecor.jobs.assessment.model.entity.PersonEntity;

/**
 * Copyright 2025 (C) Alexandra Fengler
 * 
 * Author: Alexandra Fengler
 */
@Component
@ConditionalOnProperty(prefix = "assessment", name="datasourceType", havingValue="csv")
public class PersonRepositoryCsv implements PersonRepository {
    @Value("${assessment.csvPath}")
    private String csvFilePath;
    private Logger log = LoggerFactory.getLogger(PersonRepositoryCsv.class);
    
    @Override
    public List<PersonEntity> findAll() {
        return this.readCsv();
    }

    @Override
    public Optional<PersonEntity> findById(final int id) {
        List<PersonEntity> persons = this.readCsv();
        PersonEntity foundPerson = null;

        for (PersonEntity person: persons) {
            if (person.getId() == id) {
                foundPerson = person;
                break;
            }
        }

        return foundPerson != null ? Optional.of(foundPerson) : Optional.empty();
    }

    @Override
    public List<PersonEntity> findByFavoriteColor(final Color color) {
        List<PersonEntity> persons = this.readCsv();
        List<PersonEntity> foundPersons = new ArrayList<>();

        for (PersonEntity person: persons) {
            if (person.getFavoriteColor() == color) {
                foundPersons.add(person);
            }
        }

        return foundPersons;
    }

    @Override
    public PersonEntity save(final PersonEntity person) {
        List<PersonEntity> persons = this.readCsv();

        persons.add(person);
        person.setId((long)persons.size());
        persons.sort((person1, person2) -> { return person1.compareTo(person2);});

        this.writeCsv(persons);

        return person;
    }

    private List<PersonEntity> readCsv() {
        List<PersonEntity> persons = new ArrayList<>();

        try (
            BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
        ) {
            String line = null;
            String tmp = "";
            String[] splittedLine = null;
            long personCount = 1;

            while ((line = br.readLine()) != null) {
                tmp += line;
                splittedLine = tmp.split(",");
                if (splittedLine.length < 4) {
                    continue;
                } else {
                    persons.add(new PersonEntity(personCount, splittedLine[0].trim(), splittedLine[1].trim(), splittedLine[2].trim(),
                        Color.getByNumber(Integer.parseInt(splittedLine[3].strip()))));
                    personCount++;
                }

                tmp = "";
            }

        } catch (IOException e) {
            log.error("An IOException occurred while reading CSV file: " + this.csvFilePath + " Errormessage: " + e.getMessage());
        } catch (NumberFormatException e) {
            log.error("A NumberFormatException occurred. Message: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e ) {
            log.error("An ArrayIndexOutOfBoundsExcepion occurred. Message: " + e.getMessage());
        } catch (Exception e) {
            log.error("An unexpected Exception occurred. Message: " + e.getMessage());
        }

        return persons;
    }

    private void writeCsv(final List<PersonEntity> persons) {
        try (
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.csvFilePath)); 
        ) {
            for (int i = 0; i < persons.size(); i++) {
                bw.write(persons.get(i).toCsvLine());

                if (i < persons.size() - 1) {
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            log.error("An IOException occurred while writing CSV file: " + this.csvFilePath + " Errormessage: " + e.getMessage());
        }
    }
}
