package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.Account.Car.Car;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c FROM Car c WHERE c.CuentaCompañia = :CuentaCompañia")
    List<Car> findByCuentaCompañia(@Param("CuentaCompañia") AccountCompany CuentaCompañia);

    @Query("SELECT c FROM Car c WHERE c.CuentaCompañia = :CuentaCompañia AND c.activo = :activo")
    List<Car> findByCuentaCompañiaAndActivo(@Param("CuentaCompañia") AccountCompany CuentaCompañia, @Param("activo") Boolean activo);

}

