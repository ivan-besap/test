package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.TypeAccounts;
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

    List<Account> findByEmpresaAndActivo(Empresa empresa, boolean activo);

    List<Account> findByEmpresaAndTipoCuentaAndActivo(Empresa empresa, TypeAccounts tipoCuenta, Boolean activo);

    Account findByEmpresaAndTipoCuentaAndActivoAndId(Empresa empresa, TypeAccounts typeAccounts, boolean activo, Long id);

    List<Account> findByEmpresaIdAndAlarmaCorreoTrue(Long empresaId);

    List<Account> findByEmpresaIdAndAlarmaErrorTrue(Long empresaId);
}



