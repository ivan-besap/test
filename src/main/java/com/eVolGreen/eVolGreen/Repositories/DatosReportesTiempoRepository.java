package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.ChargingStation.DatosReportesTiempo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DatosReportesTiempoRepository extends JpaRepository<DatosReportesTiempo, Long> {

    // Método existente
    Optional<DatosReportesTiempo> findByMesAndAnoAndEmpresaId(int mes, int ano, Long empresaId);

    // **Nuevo** Obtener tiempos mensuales para un año específico
    List<DatosReportesTiempo> findByEmpresaIdAndAnoAndMes(Long empresaId, int ano, int mes);

    // **Nuevo** Obtener tiempos anuales
    List<DatosReportesTiempo> findByEmpresaIdAndAno(Long empresaId, int ano);
}
