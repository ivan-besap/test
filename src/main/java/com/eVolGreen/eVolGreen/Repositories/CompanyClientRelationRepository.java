package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.Account.RelationTypeCompany.CompanyClientRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CompanyClientRelationRepository extends JpaRepository<CompanyClientRelation, Long> {
}
