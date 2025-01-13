package com.eVolGreen.eVolGreen.Services.ImplementService.AccountServiceImplement;

import com.eVolGreen.eVolGreen.DTOS.CargasOcppDTO;
import com.eVolGreen.eVolGreen.Models.Ocpp.CargasOcpp;
import com.eVolGreen.eVolGreen.Repositories.CargasOcppRepository;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.CargasOcppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CargasOcppServiceImplement implements CargasOcppService {

    @Autowired
    private CargasOcppRepository repository;

    @Override
    public List<CargasOcppDTO> getAllCargas() {
        return repository.findAll().stream()
                .map(carga -> new CargasOcppDTO(
                        carga.getOcppId(),
                        carga.getNumeroConector(),
                        carga.getTransaccionId(),
                        carga.getFechaCreacion(),
                        carga.getActivo()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public CargasOcppDTO getCargaById(Long id) {
        CargasOcpp carga = repository.findById(id).orElseThrow(() -> new RuntimeException("Carga no encontrada"));
        return new CargasOcppDTO(
                carga.getOcppId(),
                carga.getNumeroConector(),
                carga.getTransaccionId(),
                carga.getFechaCreacion(),
                carga.getActivo()
        );
    }

    @Override
    public CargasOcppDTO createCarga(CargasOcppDTO cargaDTO) {
        CargasOcpp carga = new CargasOcpp();
        carga.setOcppId(cargaDTO.getOcppId());
        carga.setNumeroConector(cargaDTO.getNumeroConector());
        carga.setTransaccionId(cargaDTO.getTransaccionId());
        carga.setFechaCreacion(ZonedDateTime.now()); // Fecha de creación establecida automáticamente
        carga.setActivo(cargaDTO.getActivo());

        CargasOcpp savedCarga = repository.save(carga);
        return new CargasOcppDTO(
                savedCarga.getOcppId(),
                savedCarga.getNumeroConector(),
                savedCarga.getTransaccionId(),
                savedCarga.getFechaCreacion(),
                savedCarga.getActivo()
        );
    }

    @Override
    public CargasOcppDTO updateCarga(Long id, CargasOcppDTO cargaDTO) {
        CargasOcpp carga = repository.findById(id).orElseThrow(() -> new RuntimeException("Carga no encontrada"));
        carga.setOcppId(cargaDTO.getOcppId());
        carga.setNumeroConector(cargaDTO.getNumeroConector());
        carga.setTransaccionId(cargaDTO.getTransaccionId());
        // La fecha de creación no se actualiza, ya que representa el momento en que fue creada.
        carga.setActivo(cargaDTO.getActivo());

        CargasOcpp updatedCarga = repository.save(carga);
        return new CargasOcppDTO(
                updatedCarga.getOcppId(),
                updatedCarga.getNumeroConector(),
                updatedCarga.getTransaccionId(),
                updatedCarga.getFechaCreacion(),
                updatedCarga.getActivo()
        );
    }

    @Override
    public void deleteCarga(Long id) {
        repository.deleteById(id);
    }
}
