package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.Connector;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.ConnectorStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Enums.ChargePointStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ConnectorRepository extends JpaRepository<Connector, Long> {
    List<Connector> findByTerminal_Empresa(Empresa empresa);
    Connector findByCargador_oCPPidAndNConector(String ocppId, Integer nConector);

    @Transactional
    @Modifying
    @Query("UPDATE Connector c SET c.EstadoConector = :status WHERE c.cargador.oCPPid = :ocppId AND c.NConector = :connectorId")
    int updateConnectorStatus(String ocppId, int connectorId, ConnectorStatus status);

}