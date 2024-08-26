package com.eVolGreen.eVolGreen.Services.ChargingStationService;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ConnectorDTO;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.Connector;

import java.util.List;

public interface ConnectorService {

    List<ConnectorDTO> getConnectorsDTO();

    void saveConnector(Connector connector);

    Connector findById(Long connectorId);

    ConnectorDTO getConnectorDTO(Long id);


}
