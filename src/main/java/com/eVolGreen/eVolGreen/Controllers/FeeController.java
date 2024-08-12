package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.FeeDTO;
import com.eVolGreen.eVolGreen.Models.Company;
import com.eVolGreen.eVolGreen.Models.Fee;
import com.eVolGreen.eVolGreen.Services.CompanyService;
import com.eVolGreen.eVolGreen.Services.FeeService;
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
    private CompanyService companyService;

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
            return buildErrorResponse(message, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(feeDTO, HttpStatus.OK);
    }

    @PostMapping("/fees")
    public ResponseEntity<Object> createFee(Authentication authentication,
                                            @RequestBody FeeDTO feeDTO) {

        Company company = companyService.findByEmailCompany(authentication.getName());
        String message = " ";

        if (company == null) {
            message = "Fees/create: No se encontró la empresa.";
            return buildErrorResponse(message, HttpStatus.NOT_FOUND);
        }

        String validationError = feeService.validateFeeDTO(feeDTO);
        if (validationError != null) {
            message = "Fees/create: " + validationError;
            return buildErrorResponse(message, HttpStatus.FORBIDDEN);
        }

        Fee fee = feeService.convertToEntity(feeDTO);
        fee.setCompany(company);

        feeService.createFee(fee);
        message = "Fees/create: Tarifa creada correctamente.";
        return new ResponseEntity<>( message,HttpStatus.CREATED);
    }

    @PutMapping("/fees/update/{id}")
    public ResponseEntity<Object> updateFee(Authentication authentication,
                                            @PathVariable Long id,
                                            @Valid @RequestBody FeeDTO feeDTO) {

        Company company = companyService.findByEmailCompany(authentication.getName());
        String message = " ";

        if (company == null) {
            message = "Update: No se encontró la empresa.";
            return buildErrorResponse(message, HttpStatus.NOT_FOUND);
        }

        String validationError = feeService.validateFeeDTO(feeDTO);
        if (validationError != null) {
            message = "Update/{id}: " + validationError;
            return buildErrorResponse(message, HttpStatus.FORBIDDEN);
        }

        Fee updatedFee = feeService.updateFee(id, feeService.convertToEntity(feeDTO));
        if (updatedFee == null) {
            message = "Update/{id}: No se encontró la tarifa.";
            return buildErrorResponse(message, HttpStatus.NOT_FOUND);
        }

        message = "Update/{id}: Tarifa actualizada correctamente.";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/fees/delete/{id}")
    public ResponseEntity<Object> deleteFee(Authentication authentication,
                                            @PathVariable Long id) {

        Company company = companyService.findByEmailCompany(authentication.getName());
        String message = " ";
        Optional<Fee> feeOptional = feeService.findFeeById(id);


        if (company == null) {
            message = "Delete/{id}: No se encontró la empresa.";
            return buildErrorResponse(message, HttpStatus.NOT_FOUND);
        }
        if (!feeOptional.isPresent()) {
            message= "Delete/{id}: No se encontró la tarifa.";
            return buildErrorResponse(message, HttpStatus.NOT_FOUND);
        }

        message = "Delete/{id}: Tarifa eliminada correctamente.";
        feeService.deleteFee(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    private ResponseEntity<Object> buildErrorResponse(String message, HttpStatus status) {
        return new ResponseEntity<>(message, status);
    }
}
