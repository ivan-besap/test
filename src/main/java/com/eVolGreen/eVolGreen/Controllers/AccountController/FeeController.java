package com.eVolGreen.eVolGreen.Controllers.AccountController;


import com.eVolGreen.eVolGreen.DTOS.AccountDTO.FeeDTO.FeeDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.FeeDTO.NewFeeDTO;
import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import com.eVolGreen.eVolGreen.Services.DUserService.CompanyUserService;
import com.eVolGreen.eVolGreen.Services.AccountService.FeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FeeController {

    @Autowired
    private FeeService feeService;

    @Autowired
    private CompanyUserService companyUserService;

    @GetMapping("/fees")
    public List<FeeDTO> getFees() {
        return feeService.getFeesDTO();
    }

    @GetMapping("/fees/{id}")
    public ResponseEntity<Object> getFeeDTOById(@PathVariable Long id) {

        String message = " ";
        FeeDTO feeDTO = feeService.getFeeDTOById(id);

        if (feeDTO == null) {
            message = "Fees/{id}: No se encontró la tarifa con el id proporcionado.";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(feeDTO, HttpStatus.OK);
    }


    @PostMapping("/companies/current/fee")
    public ResponseEntity<Object> createFee (Authentication authentication,
                                             @RequestBody NewFeeDTO fee){

        CompanyUser company = companyUserService.findByEmailCompanyUser(authentication.getName());
        String mensaje = " ";

        if (company == null) {
            mensaje = "La compañia no se encontro";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
        if (fee.getNombreTarifa() == null){
            mensaje = "El nombre es necesario";
            return new ResponseEntity<>(mensaje,HttpStatus.FORBIDDEN);
        }
        if (fee.getFechaInicio() == null){
            mensaje = "La Fecha de inicio es necesaria ";
            return new ResponseEntity<>(mensaje,HttpStatus.FORBIDDEN);
        }
        if (fee.getFechaFin() == null){
            mensaje = "La Fecha Fin de la tarifa es necesario";
            return new ResponseEntity<>(mensaje,HttpStatus.FORBIDDEN);
        }
        if (fee.getHoraInicio() == null){
            mensaje = "La Hora de inicio es necesaria";
            return new ResponseEntity<>(mensaje,HttpStatus.FORBIDDEN);
        }
        if (fee.getHoraFin() == null){
            mensaje = "La Hora Fines necesaria";
            return new ResponseEntity<>(mensaje,HttpStatus.FORBIDDEN);
        }
        if (fee.getDiasDeLaSemana() == null){
            mensaje = "Es necesario marcar 1 dia de la semana";
            return new ResponseEntity<>(mensaje,HttpStatus.FORBIDDEN);
        }
        if (fee.getPrecioTarifa() == null){
            mensaje = "El precio de la tarifa es necesario";
            return new ResponseEntity<>(mensaje,HttpStatus.FORBIDDEN);
        }

        Optional<AccountCompany> optionalAccountCompany = company.getCuentaCompañia().stream().findFirst();
        if (optionalAccountCompany.isEmpty()) {
            return new ResponseEntity<>("No se encontró la cuenta de la compañía", HttpStatus.NOT_FOUND);
        }

        AccountCompany accountCompany = optionalAccountCompany.get();

        Fee nuevaTarifa = new Fee(
                fee.getNombreTarifa(),
                fee.getFechaInicio(),
                fee.getFechaFin(),
                fee.getHoraInicio(),
                fee.getHoraFin(),
                fee.getDiasDeLaSemana(),
                fee.getPrecioTarifa(),
                false
        );
        nuevaTarifa.setCompañia(company);
        nuevaTarifa.setCuentaCompañia(accountCompany);
        feeService.saveFee(nuevaTarifa);

        mensaje = "Se ha creado una nueva tarifa";
        return new ResponseEntity<>(mensaje,HttpStatus.CREATED);
    }

    @PutMapping("/companies/current/fee/{id}")
    public ResponseEntity<Object> updateFee(Authentication authentication,
                                            @PathVariable Long id,
                                            @RequestBody NewFeeDTO feeDTO) {

        CompanyUser company = companyUserService.findByEmailCompanyUser(authentication.getName());
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

    @PatchMapping("/companies/current/fee/{id}/delete")
    public ResponseEntity<Object> deleteFee(Authentication authentication, @PathVariable Long id) {
        CompanyUser company = companyUserService.findByEmailCompanyUser(authentication.getName());
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
