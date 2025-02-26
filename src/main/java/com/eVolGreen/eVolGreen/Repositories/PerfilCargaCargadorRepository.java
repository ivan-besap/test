package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.PerfilCargaCargador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PerfilCargaCargadorRepository extends JpaRepository<PerfilCargaCargador, Long> {

    @Query("SELECT cp FROM PerfilCargaCargador cp WHERE cp.chargerId = :chargerId")
    Optional<PerfilCargaCargador> findByChargerId(@Param("chargerId") Long chargerId);
}