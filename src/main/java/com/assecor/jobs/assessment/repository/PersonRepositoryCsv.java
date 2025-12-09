package com.assecor.jobs.assessment.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.assecor.jobs.assessment.model.Color;
import com.assecor.jobs.assessment.model.Person;

public class PersonRepositoryCsv implements PersonRepository {
    @Value("${assessment.csvPath}")
    private String csvFilePath;
    
    public List<Person> getAllPersons() {
        return this.readCsv();
    }

    public Person getPersonById(final int id) {
        List<Person> persons = this.readCsv();
        Person foundPerson = null;

        for (Person person: persons) {
            if (person.getId() == id) {
                foundPerson = person;
                break;
            }
        }

        return foundPerson;
    }

    public List<Person> getPersonsByColor(final String color) {
        List<Person> persons = this.readCsv();
        List<Person> foundPersons = new ArrayList<>();

        for (Person person: persons) {
            if (person.getFavoriteColor().getColorName().equals(color)) {
                foundPersons.add(person);
            }
        }

        return foundPersons;
    }

    private List<Person> readCsv() {
        List<Person> persons = new ArrayList<>();

        try (
            BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
        ) {
            String line = null;
            String tmp = "";
            String[] splittedLine = null;
            int personCount = 1;

            while ((line = br.readLine()) != null) {
                tmp += line;
                splittedLine = tmp.split(",");
                if (splittedLine.length < 4) {
                    continue;
                } else {
                    persons.add(new Person(personCount, splittedLine[0].trim(), splittedLine[1].trim(), splittedLine[2].trim(), Color.getByNumber(Integer.parseInt(splittedLine[3].strip()))));
                    personCount++;
                }

                tmp = "";
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e ) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return persons;
    }

    
}
