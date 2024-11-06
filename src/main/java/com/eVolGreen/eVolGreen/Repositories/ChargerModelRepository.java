package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.ChargerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChargerModelRepository extends JpaRepository<ChargerModel, Long> {
    ChargerModel findByName(String name);

    List<ChargerModel> findByEmpresaId(Long empresaId);
}
