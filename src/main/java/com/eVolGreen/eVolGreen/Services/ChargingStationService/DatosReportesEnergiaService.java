package com.eVolGreen.eVolGreen.Services.ChargingStationService;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.TransactionDTO.ChargePointsSummaryResponseDTO;
import com.eVolGreen.eVolGreen.Models.ChargingStation.DatosReportesEnergia;

import java.util.List;

public interface DatosReportesEnergiaService {
    void generarDatosReportesEnergiaPorEmpresa(Long empresaId);

    List<DatosReportesEnergia> obtenerDatosReportesEnergiaPorEmpresa(Long empresaId);

    ChargePointsSummaryResponseDTO getChargePointsSummary(Long empresaId);
}
