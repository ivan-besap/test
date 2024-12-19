package com.eVolGreen.eVolGreen.Services.ImplementService.AccountServiceImplement;


import com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO.FlotaDTO;
import com.eVolGreen.eVolGreen.Models.Account.Car.Flota;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Repositories.FlotaRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.FlotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FlotaServiceImplement implements FlotaService {

    @Autowired
    private FlotaRepository flotaRepository;

    @Override
    public List<FlotaDTO> getFlotasDTO() {
        return flotaRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void saveFlota(Flota flota) {
        flotaRepository.save(flota);
    }

    @Override
    public Flota findById(Long id) {
        return flotaRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteFlota(Long id) {
        flotaRepository.deleteById(id);
    }

    @Override
    public List<FlotaDTO> getFlotasDTOByEmpresa(Empresa empresa) {
        List<Flota> flotas = flotaRepository.findByEmpresaAndActivoTrue(empresa);
        return flotas.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FlotaDTO getFlotaDTO(Long id) {
        return flotaRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    private FlotaDTO mapToDTO(Flota flota) {
        return new FlotaDTO(flota);
    }
}