package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Mantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Long> {
    List<Mantenimiento> findByCargadorId(Long cargadorId);

    List<Mantenimiento> findByEmpresaId(Long empresaId);

    @Query("SELECT m FROM Mantenimiento m JOIN FETCH m.cargador c LEFT JOIN FETCH c.Conectores WHERE m.cargador IS NOT NULL")
    List<Mantenimiento> findWithCargadorAndConectores();
}