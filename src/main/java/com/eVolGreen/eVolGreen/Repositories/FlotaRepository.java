package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.Account.Car.Flota;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlotaRepository extends JpaRepository<Flota, Long> {
    List<Flota> findByEmpresaAndActivoTrue(Empresa empresa);
}
