package com.assecor.jobs.assessment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.assecor.jobs.assessment.repository.PersonRepository;
import com.assecor.jobs.assessment.repository.PersonRepositoryCsv;
import com.assecor.jobs.assessment.repository.PersonRepositorySql;

@SpringBootApplication
public class AssessmentApplication {
	@Value("${assessment.datasourceType}")
	private String datasourceType;

	public static void main(String[] args) {
		SpringApplication.run(AssessmentApplication.class, args);
	}

	//@Bean
	//public PersonRepository personRepository() {
//		PersonRepository personRepository;
		//System.out.println("We use now this datasourceType: " + datasourceType);
		//if (datasourceType.equalsIgnoreCase("csv")) {
	//		personRepository = new PersonRepositoryCsv();
//		} else {
//			personRepository = new IPersonRepositorySql();
//		}

//		return personRepository;
//	}

	//@Bean
//	public PersonRepositoryCsv personRepositoryCsv() {
//		return new PersonRepositoryCsv();
//	}
}
