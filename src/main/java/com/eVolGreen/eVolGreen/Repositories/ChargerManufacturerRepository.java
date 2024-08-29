package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.ChargerManufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargerManufacturerRepository extends JpaRepository<ChargerManufacturer, Long> {
    ChargerManufacturer findByName(String name);
}
