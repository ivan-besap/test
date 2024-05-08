package com.eVolGreen.eVolGreen.Services.Implement;

import com.eVolGreen.eVolGreen.DTOS.ConnectorDTO;
import com.eVolGreen.eVolGreen.Models.Connector;
import com.eVolGreen.eVolGreen.Repositories.ConnectorRepository;
import com.eVolGreen.eVolGreen.Services.ConnectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
