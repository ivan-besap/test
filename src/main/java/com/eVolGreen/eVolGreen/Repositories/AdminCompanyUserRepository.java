package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.User.subclassUser.AdminCompanyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AdminCompanyUserRepository extends JpaRepository<AdminCompanyUser, Long> {
    AdminCompanyUser findByEmail(String name);
}
