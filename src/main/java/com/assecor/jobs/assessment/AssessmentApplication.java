package com.assecor.jobs.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.assecor.jobs.assessment.repository.PersonRepository;
import com.assecor.jobs.assessment.repository.PersonRepositoryCsv;

@SpringBootApplication
public class AssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssessmentApplication.class, args);
	}

	@Bean
	public PersonRepository personRepository() {
		return new PersonRepositoryCsv();
	}
}
