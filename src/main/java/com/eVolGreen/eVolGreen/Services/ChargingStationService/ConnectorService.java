package com.eVolGreen.eVolGreen.Services.ChargingStationService;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.ChargerDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ConnectorDTO;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStationStatus;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.Connector;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.ConnectorStatus;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.TypeConnector;

import java.util.List;

public interface ConnectorService {

    List<ConnectorDTO> getConnectorsDTO();

    void saveConnector(Connector connector);

    Connector findById(Long connectorId);

    ConnectorDTO getConnectorDTO(Long id);

    TypeConnector[] getAllTypeConnectors();

    boolean updateConnectorStatus(Long id, ConnectorStatus activeStatus);

    List<ConnectorDTO> getActiveConnectorsDTO();


}
