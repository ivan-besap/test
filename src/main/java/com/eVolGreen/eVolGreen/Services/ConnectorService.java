package com.eVolGreen.eVolGreen.Services;

import com.eVolGreen.eVolGreen.DTOS.ConnectorDTO;
import com.eVolGreen.eVolGreen.Models.Connector;

import java.util.List;

public interface ConnectorService {

    List<ConnectorDTO> getConnectorsDTO();

    void saveConnector(Connector connector);

    Connector findById(Long connectorId);

    ConnectorDTO getConnectorDTO(Long id);
}
