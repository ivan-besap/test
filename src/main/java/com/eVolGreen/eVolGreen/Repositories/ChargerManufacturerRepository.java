package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.ChargerManufacturer;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.ChargerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChargerManufacturerRepository extends JpaRepository<ChargerManufacturer, Long> {
    ChargerManufacturer findByName(String name);

    List<ChargerManufacturer> findByEmpresaId(Long empresaId);
}
