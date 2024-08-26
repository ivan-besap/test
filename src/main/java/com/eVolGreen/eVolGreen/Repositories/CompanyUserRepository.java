package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface CompanyUserRepository extends JpaRepository<CompanyUser, Long> {

    CompanyUser findByEmail(String email);


    boolean existsByEmail(String email);
}
