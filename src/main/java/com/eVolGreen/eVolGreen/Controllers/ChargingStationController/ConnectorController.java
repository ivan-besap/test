package com.eVolGreen.eVolGreen.Controllers.ChargingStationController;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ConnectorDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.NewConnectorDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.Connector;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.ConnectorStatus;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.TypeConnector;
import com.eVolGreen.eVolGreen.Repositories.ConnectorRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargerService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargingStationsService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ConnectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ConnectorController {

    @Autowired
    private ConnectorService connectorService;

    @Autowired
    private ChargerService chargerService;

    @Autowired
    private ConnectorRepository connectorRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ChargingStationsService chargingStationsService;

    @GetMapping("/connectors")
    public List<ConnectorDTO> getConnectors() {
         return connectorService.getActiveConnectorsDTO();
    }

    @GetMapping("/connectors/{id}")
    public ConnectorDTO getConnector(@PathVariable Long id) {
        return connectorService.getConnectorDTO(id);
    }


    @PostMapping("/companies/current/connectors")
    public ResponseEntity<Object> createConnector(Authentication authentication,
                                                  @RequestBody NewConnectorDTO connectorDTO) {

        Optional<Account> account = accountService.findByEmail(authentication.getName());
        String mensaje = " ";

        if (account.isEmpty()) {
            mensaje = "No se encontró la cuenta";
            return ResponseEntity.status(400).body(mensaje);
        }

        if (connectorDTO.getAlias() == null) {
            mensaje = "El alias del cargador no puede estar vacío";
            return ResponseEntity.status(400).body(mensaje);
        }
        if (connectorDTO.getTipoConector() == null) {
            mensaje = "El tipo de conector no puede estar vacío";
            return ResponseEntity.status(400).body(mensaje);
        }
        if (connectorDTO.getnConector() == null) {
            mensaje = "El nConector no puede estar vacío";
            return ResponseEntity.status(400).body(mensaje);
        }
        if (connectorDTO.getVoltajeMaximo() == null) {
            mensaje = "El voltaje maximo no puede estar vacío";
            return ResponseEntity.status(400).body(mensaje);
        }
        if (connectorDTO.getPotenciaMaxima() == null) {
            mensaje = "La potencia maxima no puede estar vacío";
            return ResponseEntity.status(400).body(mensaje);
        }
        if (connectorDTO.getCorrienteMaxima() == null) {
            mensaje = "La corriente maxima no puede estar vacío";
            return ResponseEntity.status(400).body(mensaje);
        }
        if (connectorDTO.getCargador() == null) {
            mensaje = "El cargador no puede estar vacío";
            return ResponseEntity.status(400).body(mensaje);
        }
        if (connectorDTO.getTerminal() == null) {
            mensaje = "La terminal no puede estar vacío";
            return ResponseEntity.status(400).body(mensaje);
        }

        Charger cargador = chargerService.findById(connectorDTO.getCargador());
        if (cargador == null) {
            mensaje = "El cargador no existe";
            return ResponseEntity.status(400).body(mensaje);
        }

        ChargingStation terminal = chargingStationsService.findById(connectorDTO.getTerminal());
        if (terminal == null) {
            mensaje = "La terminal no existe";
            return ResponseEntity.status(400).body(mensaje);
        }

        Connector connector = new Connector(
                connectorDTO.getAlias(),
                connectorDTO.getTipoConector(),
                connectorDTO.getnConector(),
                connectorDTO.getVoltajeMaximo(),
                connectorDTO.getPotenciaMaxima(),
                connectorDTO.getCorrienteMaxima(),
                cargador,
                terminal,
                ConnectorStatus.DISCONNECTED,
                true
        );


        connectorService.saveConnector(connector);
        mensaje = " El conector fue creado exitosamente y asociado con el cargador";
        return ResponseEntity.status(201).body(mensaje);
    }

    @PutMapping("/companies/current/connectors/{id}")
    public ResponseEntity<Object> updateConnector(Authentication authentication,
                                                  @PathVariable Long id,
                                                  @RequestBody NewConnectorDTO connectorDTO) {

        Optional<Account> account = accountService.findByEmail(authentication.getName());
        String mensaje = " ";

        if (account.isEmpty()) {
            mensaje = "No se encontró la cuenta";
            return ResponseEntity.status(400).body(mensaje);
        }

        // Buscar el conector existente
        Connector existingConnector = connectorService.findById(id);
        if (existingConnector == null) {
            mensaje = "El conector no existe";
            return ResponseEntity.status(404).body(mensaje);
        }

        // Validar los campos requeridos en el DTO
        if (connectorDTO.getAlias() == null) {
            mensaje = "El alias del cargador no puede estar vacío";
            return ResponseEntity.status(400).body(mensaje);
        }
        if (connectorDTO.getTipoConector() == null) {
            mensaje = "El tipo de conector no puede estar vacío";
            return ResponseEntity.status(400).body(mensaje);
        }
        if (connectorDTO.getnConector() == null) {
            mensaje = "El nConector no puede estar vacío";
            return ResponseEntity.status(400).body(mensaje);
        }
        if (connectorDTO.getVoltajeMaximo() == null) {
            mensaje = "El voltaje máximo no puede estar vacío";
            return ResponseEntity.status(400).body(mensaje);
        }
        if (connectorDTO.getPotenciaMaxima() == null) {
            mensaje = "La potencia máxima no puede estar vacía";
            return ResponseEntity.status(400).body(mensaje);
        }
        if (connectorDTO.getCorrienteMaxima() == null) {
            mensaje = "La corriente máxima no puede estar vacía";
            return ResponseEntity.status(400).body(mensaje);
        }
        if (connectorDTO.getCargador() == null) {
            mensaje = "El cargador no puede estar vacío";
            return ResponseEntity.status(400).body(mensaje);
        }
        if (connectorDTO.getTerminal() == null) {
            mensaje = "La terminal no puede estar vacía";
            return ResponseEntity.status(400).body(mensaje);
        }

        // Buscar y validar el cargador asociado
        Charger cargador = chargerService.findById(connectorDTO.getCargador());
        if (cargador == null) {
            mensaje = "El cargador no existe";
            return ResponseEntity.status(400).body(mensaje);
        }

        // Buscar y validar la terminal asociada
        ChargingStation terminal = chargingStationsService.findById(connectorDTO.getTerminal());
        if (terminal == null) {
            mensaje = "La terminal no existe";
            return ResponseEntity.status(400).body(mensaje);
        }

        // Actualizar los campos del conector existente
        existingConnector.setAlias(connectorDTO.getAlias());
        existingConnector.setTipoConector(connectorDTO.getTipoConector());
        existingConnector.setNConector(connectorDTO.getnConector());
        existingConnector.setVoltajeMaximo(connectorDTO.getVoltajeMaximo());
        existingConnector.setPotenciaMaxima(connectorDTO.getPotenciaMaxima());
        existingConnector.setCorrienteMaxima(connectorDTO.getCorrienteMaxima());
        existingConnector.setCargador(cargador);
        existingConnector.setTerminal(terminal);
        existingConnector.setEstadoConector(ConnectorStatus.DISCONNECTED); // Puedes ajustar este estado según tu lógica

        // Guardar los cambios en la base de datos
        connectorService.saveConnector(existingConnector);

        mensaje = "El conector fue actualizado exitosamente";
        return ResponseEntity.status(200).body(mensaje);
    }

    @PatchMapping("/companies/current/connectors/{id}/delete")
    public ResponseEntity<Object> deleteConnector(Authentication authentication,
                                                  @PathVariable Long id) {
        Optional<Account> account = accountService.findByEmail(authentication.getName());
        String mensaje = " ";

        if (account.isEmpty()) {
            mensaje = "No se encontró la cuenta";
            return ResponseEntity.status(400).body(mensaje);
        }

        // Buscar el conector por su ID
        Connector connector = connectorService.findById(id);
        if (connector == null) {
            mensaje = "Conector no encontrado";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }

        // Verificar si el conector ya está desactivado
        if (!connector.getActivo()) {
            mensaje = "El conector ya está desactivado";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
        }

        // Desactivar el conector
        connector.setActivo(false);
        connectorService.saveConnector(connector);

        mensaje = "Conector desactivado correctamente";
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/connector-types")
    public TypeConnector[] getAllTypeConnectors() {
        return connectorService.getAllTypeConnectors();
    }

    @PatchMapping("/connectorStatus/change-active-status")
    public ResponseEntity<String> changeChargingStationStatus(@RequestParam Long id, @RequestParam ConnectorStatus activeStatus) {
        boolean result = connectorService.updateConnectorStatus(id, activeStatus);
        if (result) {
            return ResponseEntity.ok("Estado de la estación actualizado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estación no encontrada.");
        }
    }
}
