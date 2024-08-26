package com.eVolGreen.eVolGreen.Controllers.AccountController;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.FeeDTO.NewFeeConnectorDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.FeeDTO.NewFeeDTO;
import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;
import com.eVolGreen.eVolGreen.Models.Account.Fee.FeeConnector;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.Connector;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import com.eVolGreen.eVolGreen.Repositories.FeeConnectorRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.FeeConnectorService;
import com.eVolGreen.eVolGreen.Services.AccountService.FeeService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ConnectorService;
import com.eVolGreen.eVolGreen.Services.DUserService.CompanyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

public class FeeConnectorController {

    @Autowired
    private FeeService feeService;
    @Autowired
    private ConnectorService connectorService;
    @Autowired
    private CompanyUserService companyService;
    @Autowired
    private FeeConnectorService feeConnectorService;


    @PostMapping("/companies/current/fee-connector")
    public ResponseEntity<Object> createFeeConnector(Authentication authentication,
                                                     @RequestBody NewFeeConnectorDTO feeConnectorDTO) {
        // Obtener la compañía del usuario autenticado
        CompanyUser companyUser = companyService.findByEmailCompanyUser(authentication.getName());
        String mensaje = " ";

        if (companyUser == null) {
            mensaje = "La compañía no se encontró";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        // Validar si la tarifa existe
        Fee tarifa = feeService.findById(feeConnectorDTO.getTarifa());
        if (tarifa == null) {
            mensaje = "La tarifa no se encontró";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        // Validar si el conector existe
        Connector conector = connectorService.findById(feeConnectorDTO.getConector());
        if (conector == null) {
            mensaje = "El conector no se encontró";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        // Crear la asociación FeeConnector
        FeeConnector feeConnector = new FeeConnector(
                tarifa,
                conector,
                feeConnectorDTO.getFechaActivacion(),
                false
        );

        // Guardar la asociación en la base de datos
        feeConnectorService.save(feeConnector);

        mensaje = "La tarifa se ha asociado al conector correctamente.";
        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
    }

    @PutMapping("/companies/current/feeConnector/{id}")
    public ResponseEntity<Object> updateFee(Authentication authentication,
                                            @PathVariable Long id,
                                            @RequestBody NewFeeDTO feeDTO) {

        // Obtener la compañía del usuario autenticado
        CompanyUser company = companyService.findByEmailCompanyUser(authentication.getName());
        String mensaje = " ";

        if (company == null) {
            mensaje = "La compañía no se encontró";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        // Buscar la tarifa existente por su ID
        Fee existingFee = feeService.findById(id);
        if (existingFee == null) {
            mensaje = "La tarifa no se encontró";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        // Validar los campos requeridos en el DTO
        if (feeDTO.getNombreTarifa() == null) {
            mensaje = "El nombre es necesario";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (feeDTO.getFechaInicio() == null) {
            mensaje = "La Fecha de inicio es necesaria";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (feeDTO.getFechaFin() == null) {
            mensaje = "La Fecha Fin de la tarifa es necesaria";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (feeDTO.getHoraInicio() == null) {
            mensaje = "La Hora de inicio es necesaria";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (feeDTO.getHoraFin() == null) {
            mensaje = "La Hora Fin es necesaria";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (feeDTO.getDiasDeLaSemana() == null) {
            mensaje = "Es necesario marcar 1 día de la semana";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (feeDTO.getPrecioTarifa() == null) {
            mensaje = "El precio de la tarifa es necesario";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }

        // Actualizar los campos de la tarifa existente
        existingFee.setNombreTarifa(feeDTO.getNombreTarifa());
        existingFee.setFechaInicio(feeDTO.getFechaInicio());
        existingFee.setFechaFin(feeDTO.getFechaFin());
        existingFee.setHoraInicio(feeDTO.getHoraInicio());
        existingFee.setHoraFin(feeDTO.getHoraFin());
        existingFee.setDiasDeLaSemana(feeDTO.getDiasDeLaSemana());
        existingFee.setPrecioTarifa(feeDTO.getPrecioTarifa());

        // Guardar los cambios en la base de datos
        feeService.saveFee(existingFee);

        mensaje = "La tarifa fue actualizada exitosamente";
        return ResponseEntity.status(HttpStatus.OK).body(mensaje);
    }

    @PatchMapping("/fee-connectors/{id}/deactivate")
    public ResponseEntity<Object> deactivateFeeConnector(Authentication authentication,
                                                         @PathVariable Long id) {
        // Obtener el usuario autenticado (en este caso, de la compañía)
        CompanyUser companyUser = companyService.findByEmailCompanyUser(authentication.getName());
        String mensaje = " ";

        if (companyUser == null) {
            mensaje = "La compañía no se encontró";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }

        // Buscar el FeeConnector por su ID
        FeeConnector feeConnector = feeConnectorService.findById(id);
        if (feeConnector == null) {
            mensaje = "FeeConnector no encontrado";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }

        // Verificar si el FeeConnector ya está desactivado
        if (!feeConnector.getActivo()) {
            mensaje = "FeeConnector ya está desactivado";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
        }

        // Desactivar el FeeConnector
        feeConnector.setActivo(false);
        feeConnectorService.save(feeConnector);

        mensaje = "FeeConnector desactivado correctamente";
        return ResponseEntity.ok(mensaje);
    }


}
