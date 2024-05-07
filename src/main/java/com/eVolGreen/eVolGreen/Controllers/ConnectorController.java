package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.ConnectorDTO;
import com.eVolGreen.eVolGreen.Repositories.ConnectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ConnectorController {
    @Autowired
    private ConnectorRepository connectorRepository;

    @GetMapping("/connectors")
    public List<ConnectorDTO> getConnectors() {
        return connectorRepository.findAll()
                .stream()
                .map(ConnectorDTO::new)
                .collect(Collectors.toList());
    }
}
