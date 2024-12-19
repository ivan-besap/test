package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Car.Car;
import com.eVolGreen.eVolGreen.Models.Account.Car.Flota;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface CarRepository extends JpaRepository<Car, Long> {

    // Buscar autos por empresa
    @Query("SELECT c FROM Car c WHERE c.empresa = :empresa")
    List<Car> findByEmpresa(@Param("empresa") Empresa empresa);

    // Buscar autos por empresa y estado activo
    @Query("SELECT c FROM Car c WHERE c.empresa = :empresa AND c.activo = :activo")
    List<Car> findByEmpresaAndActivo(@Param("empresa") Empresa empresa, @Param("activo") Boolean activo);

    List<Car> findByFlota(Flota flota);
}

