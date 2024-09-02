package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Car.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c FROM Car c WHERE c.account = :account")
    List<Car> findByAccount(@Param("account") Account account);

    @Query("SELECT c FROM Car c WHERE c.account = :account AND c.activo = :activo")
    List<Car> findByAccountAndActivo(@Param("account") Account account, @Param("activo") Boolean activo);
}

