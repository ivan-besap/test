package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ChargerRepository extends JpaRepository<Charger, Long> {
    List<Charger> findByTerminal_Empresa(Empresa empresa);

    @Query("SELECT c FROM Charger c WHERE c.oCPPid = :oCPPid")
    Charger findByOCPPid(@Param("oCPPid") String oCPPid);
}
