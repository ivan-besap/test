package com.eVolGreen.eVolGreen.Repositories;


import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AccountEmployeeRepository extends JpaRepository<AccountEmployee, Long> {
}
