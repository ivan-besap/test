package com.eVolGreen.eVolGreen.Services.Implement;

import com.eVolGreen.eVolGreen.DTOS.ChargerDTO;
import com.eVolGreen.eVolGreen.Models.Charger;
import com.eVolGreen.eVolGreen.Repositories.ChargerRepository;
import com.eVolGreen.eVolGreen.Services.ChargerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChargerServiceImplement implements ChargerService {
    @Autowired
    private ChargerRepository chargerRepository;

    @Override
    public List<ChargerDTO> getChargersDTO() {
        return chargerRepository.findAll()
                .stream()
                .map(ChargerDTO::new)
                .collect(Collectors.toList());
    }
    @Override
    public void saveCharger(Charger charger) {
        chargerRepository.save(charger);
    }

    @Override
    public Charger findById(Long chargerId) {
        return chargerRepository.findById(chargerId).orElse(null);
    }

    @Override
    public ChargerDTO getChargerDTO(Long id) {
        return new ChargerDTO(this.findById(id));
    }
}
