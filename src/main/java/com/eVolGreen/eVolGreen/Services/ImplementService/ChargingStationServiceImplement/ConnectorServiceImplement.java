package com.eVolGreen.eVolGreen.Services.ImplementService.ChargingStationServiceImplement;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.ChargerDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ConnectorDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.NewTypeConnectorDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.TypeConnectorDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargerStatus;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.Connector;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.ConnectorStatus;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.TypeConnector;
import com.eVolGreen.eVolGreen.Repositories.ConnectorRepository;
import com.eVolGreen.eVolGreen.Repositories.TypeConnectorRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ConnectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConnectorServiceImplement implements ConnectorService {
    @Autowired
    private ConnectorRepository connectorRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TypeConnectorRepository typeConnectorRepository;

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

    @Override
    public List<TypeConnectorDTO> getAllTypeConnectors() {
        List<TypeConnector> typeConnectors = typeConnectorRepository.findAll();
        return typeConnectors.stream()
                .map(TypeConnectorDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateConnectorStatus(Long id, ConnectorStatus activeStatus) {
        Optional<Connector> optionalStation = connectorRepository.findById(id);
        if (optionalStation.isPresent()) {
            Connector connector = optionalStation.get();
            connector.setEstadoConector(activeStatus);
            connectorRepository.save(connector);
            return true;
        }
        return false;
    }

    @Override
    public List<ConnectorDTO> getActiveConnectorsDTO() {
        return connectorRepository.findAll()
                .stream()
                .filter(Connector::getActivo) // Filtra las estaciones activas
                .map(ConnectorDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConnectorDTO> getConnectorsForCurrentUser(String email) {
        // Buscar la cuenta por email
        Optional<Account> account = accountService.findByEmail(email);
        if (account.isPresent()) {
            // Obtener la empresa asociada a la cuenta
            Empresa empresa = account.get().getEmpresa();

            if (empresa != null) {
                // Filtrar los conectores por las estaciones de carga de la empresa
                return connectorRepository.findByTerminal_Empresa(empresa)
                        .stream()
                        .filter(Connector::getActivo)
                        .map(ConnectorDTO::new)
                        .collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }

    @Override
    public void saveTypeConnector(NewTypeConnectorDTO newTypeConnectorDTO) {
        TypeConnector typeConnector = new TypeConnector();
        typeConnector.setNombre(newTypeConnectorDTO.getNombre());
        typeConnectorRepository.save(typeConnector);
    }

}
