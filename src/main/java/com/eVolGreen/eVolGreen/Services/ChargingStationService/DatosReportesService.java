package com.eVolGreen.eVolGreen.Services.ChargingStationService;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.DatosReportesDTO;

import java.util.List;

public interface DatosReportesService {
    List<DatosReportesDTO> getDatosReportesByEmpresaId(Long empresaId);

    void generarDatosReportesPorEmpresa(Long empresaId);
}
