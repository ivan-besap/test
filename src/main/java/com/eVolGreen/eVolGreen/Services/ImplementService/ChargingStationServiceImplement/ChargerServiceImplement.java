package com.eVolGreen.eVolGreen.Services.ImplementService.ChargingStationServiceImplement;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.ChargerDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargingStationsDTO;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargerStatus;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStationStatus;
import com.eVolGreen.eVolGreen.Repositories.ChargerRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChargerServiceImplement implements ChargerService {

    @Autowired
    private ChargerRepository chargerRepository;

    @Autowired
    private AccountService accountService;


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

    @Override
    public boolean updateChargerStatus(Long id, ChargerStatus activeStatus) {
        Optional<Charger> optionalStation = chargerRepository.findById(id);
        if (optionalStation.isPresent()) {
            Charger charger = optionalStation.get();
            charger.setEstadoCargador(activeStatus);
            chargerRepository.save(charger);
            return true;
        }
        return false;
    }

    @Override
    public List<ChargerDTO> getActiveChargersDTO() {
        return chargerRepository.findAll()
                .stream()
                .filter(Charger::getActivo) // Filtra las estaciones activas
                .map(ChargerDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChargerDTO> getChargersForCurrentUser(String email) {
        Optional<Account> account = accountService.findByEmail(email);
        if (account.isPresent()) {
            Empresa empresa = account.get().getEmpresa();
            if (empresa != null) {
                return chargerRepository.findByTerminal_Empresa(empresa)
                        .stream()
                        .filter(Charger::getActivo)
                        .map(ChargerDTO::new)
                        .collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }
}
