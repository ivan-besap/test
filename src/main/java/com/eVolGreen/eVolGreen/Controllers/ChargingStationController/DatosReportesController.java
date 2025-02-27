package com.eVolGreen.eVolGreen.Controllers.ChargingStationController;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.DatosReportesDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Repositories.AccountRepository;
import com.eVolGreen.eVolGreen.Repositories.DatosReportesRepository;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.DatosReportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DatosReportesController {

    @Autowired
    private DatosReportesService datosReportesService;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/datos-reportes")
    public ResponseEntity<List<DatosReportesDTO>> getDatosReportesByEmpresaId(Authentication authentication) {

        Optional<Account> cuentaOpt = accountRepository.findByEmail(authentication.getName());
        if (cuentaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Long empresaId = cuentaOpt.get().getEmpresa().getId();

        List<DatosReportesDTO> datosReportes = datosReportesService.getDatosReportesByEmpresaId(empresaId);
        return ResponseEntity.ok(datosReportes);
    }

    @PostMapping("/generar-datosReportes")
    public ResponseEntity<String> generarDatosReportesPorEmpresa(Authentication authentication) {

        Optional<Account> cuentaOpt = accountRepository.findByEmail(authentication.getName());
        if (cuentaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        datosReportesService.generarDatosReportesPorEmpresa(cuentaOpt.get().getEmpresa().getId());
        return ResponseEntity.ok("Datos reportes generados exitosamente para la empresa: " + cuentaOpt.get().getEmpresa().getNombre());
    }
}
