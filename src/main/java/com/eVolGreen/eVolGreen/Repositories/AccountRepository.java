package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository <Account, Long> {

    List<Account> findByCuentaCompañia(CompanyUser client);

    Account findByNumeroDeCuenta(String numeroDeCuenta);

    boolean existsByNumeroDeCuenta(String numeroDeCuenta);

}



