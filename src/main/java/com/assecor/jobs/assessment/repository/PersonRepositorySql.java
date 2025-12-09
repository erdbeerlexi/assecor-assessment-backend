package com.assecor.jobs.assessment.repository;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

import com.assecor.jobs.assessment.model.entity.PersonEntity;

@Component
@ConditionalOnProperty(prefix = "assessment", name="datasourceType", havingValue="db")
public interface PersonRepositorySql extends Repository<PersonEntity, Long>, PersonRepository {

}
