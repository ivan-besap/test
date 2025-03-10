package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.Account.Fee.EstadoPerfil;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.PerfilCargaCargador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface PerfilCargaCargadorRepository extends JpaRepository<PerfilCargaCargador, Long> {

    @Query("SELECT cp FROM PerfilCargaCargador cp WHERE cp.chargerId = :chargerId")
    Optional<PerfilCargaCargador> findByChargerId(@Param("chargerId") Long chargerId);

    @Query("SELECT cp FROM PerfilCargaCargador cp " +
            "LEFT JOIN cp.tarifa t " +
            "WHERE cp.chargerId = :chargerId " +
            "AND cp.estado = :estado " +
            "AND (cp.stackLevel = 0 OR cp.stackLevel IS NULL) " +
            "AND SIZE(t.diasDeLaSemana) > 0 " +
            "AND :currentDay MEMBER OF t.diasDeLaSemana " +
            "ORDER BY cp.stackLevel ASC")
    List<PerfilCargaCargador> findDefaultActiveProfilesByChargerIdAndDay(
            @Param("chargerId") Long chargerId,
            @Param("estado") EstadoPerfil estado,
            @Param("currentDay") String currentDay
    );

    PerfilCargaCargador findByChargerIdAndEstado(Long chargerId, EstadoPerfil estadoPerfil);

    List<PerfilCargaCargador> findAllByChargerIdAndEstado(Long chargerId, EstadoPerfil estadoPerfil);

    @Query("SELECT p FROM PerfilCargaCargador p " +
            "LEFT JOIN FETCH p.tarifa t " +
            "LEFT JOIN FETCH t.diasDeLaSemana " +
            "WHERE p.id IS NOT NULL")
    List<PerfilCargaCargador> findAllWithFeeAndDays();

    PerfilCargaCargador findByChargerIdAndStackLevel(Long chargerId, Integer stackLevel);

    @Query("SELECT cp FROM PerfilCargaCargador cp WHERE cp.chargerId = :chargerId AND cp.stackLevel = :stackLevel ORDER BY cp.id DESC")
    Optional<PerfilCargaCargador> findTopByChargerIdAndStackLevel(@Param("chargerId") Long chargerId, @Param("stackLevel") Integer stackLevel);
}