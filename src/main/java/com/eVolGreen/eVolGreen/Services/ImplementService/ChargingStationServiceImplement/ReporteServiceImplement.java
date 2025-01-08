package com.eVolGreen.eVolGreen.Services.ImplementService.ChargingStationServiceImplement;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ReporteDTO;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Reporte;
import com.eVolGreen.eVolGreen.Repositories.ReporteRepository;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteServiceImplement implements ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    @Override
    public List<ReporteDTO> getAllReportes() {
        return reporteRepository.findAll().stream()
                .map(reporte -> new ReporteDTO(
                        reporte.getId(),
                        reporte.getChargingStation().getId(),
                        reporte.getCharger().getId(),
                        reporte.getConnector().getId(),
                        reporte.getEmpresa().getId(),
                        reporte.getEnergia(),
                        reporte.getTiempo(),
                        reporte.getFechaCreacion(),
                        reporte.getInicioCarga(),
                        reporte.getFinCarga(),
                        reporte.getCosto(),
                        reporte.getDescripcion(),
                        reporte.getAccount().getId(),
                        reporte.getDeviceIdentifier().getId()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ReporteDTO getReporteById(Long id) {
        Reporte reporte = reporteRepository.findById(id).orElse(null);
        if (reporte != null) {
            return new ReporteDTO(
                    reporte.getId(),
                    reporte.getChargingStation().getId(),
                    reporte.getCharger().getId(),
                    reporte.getConnector().getId(),
                    reporte.getEmpresa().getId(),
                    reporte.getEnergia(),
                    reporte.getTiempo(),
                    reporte.getFechaCreacion(),
                    reporte.getInicioCarga(),
                    reporte.getFinCarga(),
                    reporte.getCosto(),
                    reporte.getDescripcion(),
                    reporte.getAccount().getId(),
                    reporte.getDeviceIdentifier().getId()
            );
        }
        return null;
    }

    @Override
    public void saveReporte(Reporte reporte) {
        reporteRepository.save(reporte);
    }

    @Override
    public void deleteReporte(Long id) {
        reporteRepository.deleteById(id);
    }
}

