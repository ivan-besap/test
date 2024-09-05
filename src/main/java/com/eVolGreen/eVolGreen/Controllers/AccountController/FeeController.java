package com.eVolGreen.eVolGreen.Controllers.AccountController;


import com.eVolGreen.eVolGreen.DTOS.AccountDTO.FeeDTO.FeeDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.FeeDTO.NewFeeDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.ChargerDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountService;
import com.eVolGreen.eVolGreen.Services.AccountService.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FeeController {

    @Autowired
    private FeeService feeService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/fees")
    public List<FeeDTO> getFees(Authentication authentication) {
        String email = authentication.getName();
        return feeService.getFeesDTO(email);
    }

    @GetMapping("/fees/{id}")
    public ResponseEntity<Object> getFeeDTOById(@PathVariable Long id) {

        String message;
        FeeDTO feeDTO = feeService.getFeeDTOById(id);

        if (feeDTO == null) {
            message = "No se encontró la tarifa con el id proporcionado.";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(feeDTO, HttpStatus.OK);
    }

    @PostMapping("/fees")
    public ResponseEntity<Object> createFee(Authentication authentication,
                                            @RequestBody NewFeeDTO feeDTO) {


        Optional<Account> optionalAccount = accountService.findByEmail(authentication.getName());
        String mensaje;

        if (optionalAccount.isEmpty()) {
            mensaje = "La cuenta no se encontró";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        Account account = optionalAccount.get();


        Empresa empresa = account.getEmpresa();
        if (empresa == null) {
            mensaje = "No se encontró una empresa asociada a la cuenta";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }


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
            mensaje = "Es necesario marcar al menos un día de la semana";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (feeDTO.getPrecioTarifa() == null) {
            mensaje = "El precio de la tarifa es necesario";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }


        Fee nuevaTarifa = new Fee(
                feeDTO.getNombreTarifa(),
                feeDTO.getFechaInicio(),
                feeDTO.getFechaFin(),
                feeDTO.getHoraInicio(),
                feeDTO.getHoraFin(),
                feeDTO.getDiasDeLaSemana(),
                feeDTO.getPrecioTarifa(),
                true,
                empresa
        );

        feeService.saveFee(nuevaTarifa);

        mensaje = "Se ha creado una nueva tarifa";
        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
    }

    @PutMapping("/fees/{id}")
    public ResponseEntity<Object> updateFee(@PathVariable Long id, @RequestBody NewFeeDTO feeDTO) {

        String mensaje;

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
            mensaje = "Es necesario marcar al menos un día de la semana";
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

    @PatchMapping("/fees/{id}/delete")
    public ResponseEntity<Object> deleteFee(@PathVariable Long id) {

        String mensaje;

        // Buscar la tarifa existente por su ID
        Fee existingFee = feeService.findById(id);
        if (existingFee == null) {
            mensaje = "La tarifa no se encontró";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        // Verificar si la tarifa ya está desactivada
        if (!existingFee.getActivo()) {
            mensaje = "La tarifa ya está desactivada";
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }

        // Desactivar la tarifa
        existingFee.setActivo(false);
        feeService.saveFee(existingFee);

        mensaje = "La tarifa fue desactivada exitosamente";
        return ResponseEntity.ok(mensaje);
    }

}