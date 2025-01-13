package com.eVolGreen.eVolGreen.Controllers.ChargingStationController;


import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ReporteDTO;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ReporteController {

    @Autowired
    private ReporteService reporteService; // Inyectamos el servicio correctamente

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
}