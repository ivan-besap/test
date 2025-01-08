package com.eVolGreen.eVolGreen.Services.ChargingStationService;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ReporteDTO;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Reporte;

import java.util.List;

public interface ReporteService {
    List<ReporteDTO> getAllReportes();
    ReporteDTO getReporteById(Long id);
    void saveReporte(Reporte reporte);
    void deleteReporte(Long id);
}
