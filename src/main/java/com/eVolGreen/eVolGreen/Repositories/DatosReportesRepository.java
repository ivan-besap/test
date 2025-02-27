package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.DatosReportesDTO;
import com.eVolGreen.eVolGreen.Models.ChargingStation.DatosReportes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DatosReportesRepository extends JpaRepository<DatosReportes, Long> {
    // **Obtener datos de un mes específico para una empresa**
    List<DatosReportes> findByEmpresaIdAndAnoAndMes(Long empresaId, int ano, int mes);

    // **Obtener datos de un año específico para una empresa**
    List<DatosReportes> findByEmpresaIdAndAno(Long empresaId, int ano);

    Optional<DatosReportes> findByMesAndAnoAndEmpresaIdAndEstacionId(int mes, int ano, Long empresaId, Long estacionId);

    @Query("SELECT new com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.DatosReportesDTO(cs.nombreTerminal, dr.mes, dr.ano, dr.ingreso) " +
            "FROM DatosReportes dr " +
            "JOIN ChargingStation cs ON dr.estacionId = cs.id " +
            "WHERE dr.empresaId = :empresaId")
    List<DatosReportesDTO> findAllByEmpresaId(Long empresaId);
}
