package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.Account.RelationTypeCompany.CompanyEmployeeRelation;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CompanyEmployeeRelationRepository extends JpaRepository<CompanyEmployeeRelation, Long> {
    List<CompanyEmployeeRelation> findAllByCompany(CompanyUser company);
}
