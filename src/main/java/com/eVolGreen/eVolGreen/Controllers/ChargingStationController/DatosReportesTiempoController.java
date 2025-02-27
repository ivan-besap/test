package com.eVolGreen.eVolGreen.Controllers.ChargingStationController;

import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Repositories.AccountRepository;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.DatosReportesTiempoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DatosReportesTiempoController {

    @Autowired
    private DatosReportesTiempoService datosReportesTiempoService;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/generar-datosReportesTiempo")
    public ResponseEntity<String> generarDatosReportesTiempoPorEmpresa(Authentication authentication) {
        Optional<Account> cuentaOpt = accountRepository.findByEmail(authentication.getName());
        if (cuentaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        datosReportesTiempoService.generarDatosReportesTiempoPorEmpresa(cuentaOpt.get().getEmpresa().getId());
        return ResponseEntity.ok("Datos reportes de tiempo generados exitosamente para la empresa: " + cuentaOpt.get().getEmpresa().getNombre());
    }
}
