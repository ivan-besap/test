package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Mantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Long> {
    List<Mantenimiento> findByCargadorId(Long cargadorId);

    List<Mantenimiento> findByEmpresaId(Long empresaId);
}