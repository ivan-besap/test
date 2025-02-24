package com.eVolGreen.eVolGreen.Services.AccountService;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO.FlotaDTO;
import com.eVolGreen.eVolGreen.Models.Account.Car.Flota;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;

import java.util.List;

public interface FlotaService {

    List<FlotaDTO> getFlotasDTO();

    void saveFlota(Flota flota);

    Flota findById(Long id);

    void deleteFlota(Long id);

    List<FlotaDTO> getFlotasDTOByEmpresa(Empresa empresa);

    FlotaDTO getFlotaDTO(Long id);

    Flota findByNombreFlotaAndEmpresa(String nombreFlota, Empresa empresa);
}