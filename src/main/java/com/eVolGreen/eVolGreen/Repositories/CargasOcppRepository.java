package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.Ocpp.CargasOcpp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CargasOcppRepository extends JpaRepository<CargasOcpp, Long> {

    List<CargasOcpp> findByOcppIdAndNumeroConector(String ocppId, Integer numeroConector);
    CargasOcpp findByOcppIdAndNumeroConectorAndActivo(String ocppId, Integer numeroConector, Boolean activo);
}