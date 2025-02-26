package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.ChargingStation.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReporteRepository extends JpaRepository<Reporte, Long> {
    List<Reporte> findByEmpresaId(Long empresaId);

    @Query("SELECT r FROM Reporte r WHERE r.transactionId = :transactionId AND r.activo = true")
    Optional<Reporte> findByTransactionIdAndActive(@Param("transactionId")Integer transactionId);

    @Query("SELECT r FROM Reporte r ORDER BY r.charger.id ASC, r.fechaCreacion DESC")
    List<Reporte> findAllGroupedByChargerAndSorted();

}
