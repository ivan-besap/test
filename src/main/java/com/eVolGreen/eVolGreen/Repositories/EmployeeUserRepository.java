package com.eVolGreen.eVolGreen.Repositories;


import com.eVolGreen.eVolGreen.Models.User.subclassUser.EmployeeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EmployeeUserRepository extends JpaRepository<EmployeeUser, Long> {


    EmployeeUser findByEmail(String username);

}
