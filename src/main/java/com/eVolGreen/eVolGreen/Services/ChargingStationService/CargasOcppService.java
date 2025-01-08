package com.eVolGreen.eVolGreen.Services.ChargingStationService;

import com.eVolGreen.eVolGreen.DTOS.CargasOcppDTO;

import java.util.List;

public interface CargasOcppService {
    List<CargasOcppDTO> getAllCargas();
    CargasOcppDTO getCargaById(Long id);
    CargasOcppDTO createCarga(CargasOcppDTO cargaDTO);
    CargasOcppDTO updateCarga(Long id, CargasOcppDTO cargaDTO);
    void deleteCarga(Long id);
}