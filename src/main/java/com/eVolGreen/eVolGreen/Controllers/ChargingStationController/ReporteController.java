package com.eVolGreen.eVolGreen.Controllers.ChargingStationController;


import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.ReporteListDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ReporteDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ReporteResponseDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ReporteResumenDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/reporte")
    public ResponseEntity<Long> createReporte(@RequestBody ReporteDTO reporteDTO) {
        try {
            Long reporteId = reporteService.createReporte(reporteDTO); // Usamos el servicio inyectado
            return ResponseEntity.ok(reporteId); // Retorna el ID del registro creado
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PatchMapping("/reporte/{id}")
    public ResponseEntity<String> updateReporte(@PathVariable Long id, @RequestBody ReporteDTO reporteDTO) {
        try {
            boolean updated = reporteService.updateReporte(id, reporteDTO); // Usamos el servicio inyectado
            if (updated) {
                return ResponseEntity.ok("Reporte actualizado correctamente.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reporte no encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el reporte.");
        }
    }

    @GetMapping("/reportes-cargas")
    public ResponseEntity<List<ReporteResponseDTO>> getReportesCargas(Authentication authentication) {
        try {
            // Obtener la cuenta del usuario autenticado
            Optional<Account> accountOptional = accountService.findByEmail(authentication.getName());
            if (accountOptional.isEmpty()) {
                // Si la cuenta no existe, devolvemos un error
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }

            Account account = accountOptional.get();
            Empresa empresa = account.getEmpresa();

            if (empresa == null) {
                // Si la empresa asociada no existe, devolvemos un error
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            // Obtener los reportes filtrados por empresa
            List<ReporteResponseDTO> reportes = reporteService.getReportesByEmpresa(empresa.getId());
            return ResponseEntity.ok(reportes);

        } catch (Exception e) {
            // Manejo de errores generales
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/reportes-cargas-rfid")
    public ResponseEntity<List<ReporteResponseDTO>> getReportesByEmpresaAndByRfid(Authentication authentication) {
        try {
            // Obtener la cuenta del usuario autenticado
            Optional<Account> accountOptional = accountService.findByEmail(authentication.getName());
            if (accountOptional.isEmpty()) {
                // Si la cuenta no existe, devolvemos un error
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }

            Account account = accountOptional.get();
            Empresa empresa = account.getEmpresa();

            if (empresa == null) {
                // Si la empresa asociada no existe, devolvemos un error
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            // Obtener los reportes filtrados por empresa
            List<ReporteResponseDTO> reportes = reporteService.getReportesByEmpresaAndByRfid(empresa.getId());
            return ResponseEntity.ok(reportes);

        } catch (Exception e) {
            // Manejo de errores generales
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/cargaCargador")
    public List<ReporteListDTO> getReportesGroupedByCharger() {
        return reporteService.getAllReportesGroupedByCharger();
    }

    @GetMapping("/resumen-reportes")
    public ReporteResumenDTO obtenerReporteResumen(@RequestParam Long empresaId) {
        return reporteService.obtenerReporteResumen(empresaId);
    }

}