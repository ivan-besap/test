package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.Account.RelationTypeCompany.CompanyEmployeeRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CompanyEmployeeRelationRepository extends JpaRepository<CompanyEmployeeRelation, Long> {
}
