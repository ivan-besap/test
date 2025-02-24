package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.ChargingStation.DatosReportes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DatosReportesRepository extends JpaRepository<DatosReportes, Long> {
    Optional<DatosReportes> findByMesAndAno(int mes, int ano);
}
