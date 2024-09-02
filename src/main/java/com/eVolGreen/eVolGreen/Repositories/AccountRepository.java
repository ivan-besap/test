package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.Account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository <Account, Long> {


    Account findByNumeroDeCuenta(String numeroDeCuenta);

    boolean existsByNumeroDeCuenta(String numeroDeCuenta);

    Optional<Account> findByEmail(String email);

    @Query("SELECT a FROM Account a JOIN FETCH a.role r LEFT JOIN FETCH r.permisos WHERE a.email = :email")
    Optional<Account> findByEmailWithRoleAndPermissions(@Param("email") String email);

}



