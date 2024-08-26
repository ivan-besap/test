package com.eVolGreen.eVolGreen.Services.ImplementService.ChargingStationServiceImplement;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ConnectorDTO;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.Connector;
import com.eVolGreen.eVolGreen.Repositories.ConnectorRepository;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ConnectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConnectorServiceImplement implements ConnectorService {
    @Autowired
    private ConnectorRepository connectorRepository;

    @Override
    public List<ConnectorDTO> getConnectorsDTO() {
        return connectorRepository.findAll()
                .stream()
                .map(ConnectorDTO::new)
                .collect(Collectors.toList());
    }
    @Override
    public void saveConnector(Connector connector) {
        connectorRepository.save(connector);
    }

    @Override
    public Connector findById(Long connectorId) {
        return connectorRepository.findById(connectorId).orElse(null);
    }

    @Override
    public ConnectorDTO getConnectorDTO(Long id) {
            return new ConnectorDTO(this.findById(id));
    }

}
