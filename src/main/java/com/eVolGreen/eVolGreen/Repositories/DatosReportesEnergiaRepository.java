package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.ChargingStation.DatosReportesEnergia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DatosReportesEnergiaRepository extends JpaRepository<DatosReportesEnergia, Long> {
    Optional<DatosReportesEnergia> findByMesAndAnoAndEmpresaId(int mes, int ano, Long empresaId);

    List<DatosReportesEnergia> findByEmpresaIdAndAno(Long empresaId, int ano);

    List<DatosReportesEnergia> findByEmpresaId(Long empresaId);
}
