package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.ChargingStation.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface ReporteRepository extends JpaRepository<Reporte, Long> {
    List<Reporte> findByEmpresaId(Long empresaId);

    @Query("SELECT r FROM Reporte r WHERE r.transactionId = :transactionId AND r.activo = true")
    Optional<Reporte> findByTransactionIdAndActive(@Param("transactionId")Integer transactionId);

    @Query("SELECT r FROM Reporte r ORDER BY r.charger.id ASC, r.fechaCreacion DESC")
    List<Reporte> findAllGroupedByChargerAndSorted();

    List<Reporte> findByEmpresaIdAndDeviceIdentifierIsNull(Long empresaId);

    List<Reporte> findByEmpresaIdAndDeviceIdentifierIsNotNull(Long empresaId);


    @Query("SELECT YEAR(r.inicioCarga) AS ano, MONTH(r.inicioCarga) AS mes, SUM(r.costo) AS ingreso, r.empresa.id AS empresaId, r.chargingStation.id AS estacionId " +
            "FROM Reporte r " +
            "WHERE r.costo > 0 AND r.empresa.id = :empresaId " +
            "GROUP BY ano, mes, empresaId, estacionId")
    List<Object[]> findIngresosAgrupadosPorMesYAno(@Param("empresaId") Long empresaId);

    @Query("SELECT YEAR(r.inicioCarga), MONTH(r.inicioCarga), SUM(r.energiaEntregada), r.empresa.id " +
            "FROM Reporte r " +
            "WHERE r.empresa.id = :empresaId " +
            "GROUP BY YEAR(r.inicioCarga), MONTH(r.inicioCarga), r.empresa.id")
    List<Object[]> findEnergiaAgrupadaPorMesYAno(@Param("empresaId") Long empresaId);

    List<Reporte> findByEmpresaIdAndInicioCargaBetween(Long empresaId, ZonedDateTime inicio, ZonedDateTime fin);

    @Query("SELECT YEAR(r.inicioCarga) AS ano, MONTH(r.inicioCarga) AS mes, " +
            "r.tiempo, r.empresa.id AS empresaId " +
            "FROM Reporte r " +
            "WHERE r.empresa.id = :empresaId " +
            "GROUP BY ano, mes, r.tiempo, empresaId")
    List<Object[]> findTiemposAgrupadosPorMesYAno(Long empresaId);



}
