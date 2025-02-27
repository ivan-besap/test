package com.eVolGreen.eVolGreen.Controllers.ChargingStationController;


import com.eVolGreen.eVolGreen.DTOS.AccountDTO.TransactionDTO.ChargePointsSummaryResponseDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Repositories.AccountRepository;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.DatosReportesEnergiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DatosReportesEnergiaController {

    @Autowired
    private DatosReportesEnergiaService datosReportesEnergiaService;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/generar-datosReportesEnergia")
    public ResponseEntity<String> generarDatosReportesEnergiaPorEmpresa(Authentication authentication) {

        Optional<Account> cuentaOpt = accountRepository.findByEmail(authentication.getName());
        if (cuentaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        datosReportesEnergiaService.generarDatosReportesEnergiaPorEmpresa(cuentaOpt.get().getEmpresa().getId());
        return ResponseEntity.ok("Datos reportes de energía generados exitosamente para la empresa: " + cuentaOpt.get().getEmpresa().getNombre());
    }

    @GetMapping("/reporte-energia")
    public ChargePointsSummaryResponseDTO getChargePointsAndTotalEnergy(Authentication authentication) {

        Optional<Account> cuentaOpt = accountRepository.findByEmail(authentication.getName());
        if (cuentaOpt.isEmpty()) {
            return null;
        }

        // Solo llama al servicio para obtener el resumen
        return datosReportesEnergiaService.getChargePointsSummary(cuentaOpt.get().getEmpresa().getId());
    }
}
