package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.ConnectorDTO;
import com.eVolGreen.eVolGreen.Models.Charger;
import com.eVolGreen.eVolGreen.Models.Connector;
import com.eVolGreen.eVolGreen.Models.ConnectorStatus;
import com.eVolGreen.eVolGreen.Repositories.ConnectorRepository;
import com.eVolGreen.eVolGreen.Services.ChargerService;
import com.eVolGreen.eVolGreen.Services.ConnectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ConnectorController {
    @Autowired
    private ConnectorService connectorService;
    @Autowired
    private ChargerService chargerService;
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

    @PostMapping("/connectors")
    public ResponseEntity<Object> createConnector(Authentication authentication,
                                                  @RequestBody ConnectorDTO connectorDTO) {
        Charger charger = chargerService.findById(connectorDTO.getChargerId());
        if (charger == null) {
            return ResponseEntity.status(404).body("Charger not found.");
        }

        Connector connector = new Connector();
        connector.setName(connectorDTO.getName());
        connector.setPower(connectorDTO.getPower() != null ? connectorDTO.getPower() : BigDecimal.valueOf(0.0));
        connector.setConnectorStatus(connectorDTO.getConnectorStatus() != null ? connectorDTO.getConnectorStatus() : ConnectorStatus.DISCONNECTED);
        connector.setCharge(connectorDTO.getCharge() != null ? connectorDTO.getCharge() : BigDecimal.valueOf(0.0));
        connector.setCharger(charger);

        connectorService.saveConnector(connector);

        return ResponseEntity.status(201).body("Connector created successfully and associated with the charger.");
    }

}
