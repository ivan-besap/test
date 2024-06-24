package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.ConnectorDTO;
import com.eVolGreen.eVolGreen.Repositories.ConnectorRepository;
import com.eVolGreen.eVolGreen.Services.ConnectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ConnectorController {
    @Autowired
    private ConnectorService connectorService;
    @Autowired
    private ConnectorRepository connectorRepository;

    @GetMapping("/connectors")
    public List<ConnectorDTO> getConnectors() {
        return connectorService.getConnectorsDTO();
    }

    @GetMapping ("/connectors/{id}")
    public ConnectorDTO getConnector(@PathVariable Long id) {
        return connectorService.getConnectorDTO(id);
    }
}
