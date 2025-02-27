package com.eVolGreen.eVolGreen.Services.ChargingStationService;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.ReporteListDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ReporteDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ReporteResponseDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ReporteResumenDTO;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Reporte;

import java.util.List;

public interface ReporteService {
    List<ReporteDTO> getAllReportes();
    ReporteDTO getReporteById(Long id);
    void saveReporte(Reporte reporte);
    void deleteReporte(Long id);

    List<ReporteResponseDTO> getReportesByEmpresa(Long empresaId);
    List<ReporteResponseDTO> getReportesByEmpresaAndByRfid(Long empresaId);

    Long createReporte(ReporteDTO reporteDTO); // Crear reporte
    boolean updateReporte(Long id, ReporteDTO reporteDTO); // Actualizar reporte

    List<ReporteListDTO> getAllReportesGroupedByCharger ();
    ReporteResumenDTO obtenerReporteResumen(Long empresaId);

}
