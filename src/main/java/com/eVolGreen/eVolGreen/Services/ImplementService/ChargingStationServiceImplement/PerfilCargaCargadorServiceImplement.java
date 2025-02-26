package com.eVolGreen.eVolGreen.Services.ImplementService.ChargingStationServiceImplement;

import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.PerfilCargaCargador;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Utils.ChargingProfile;
import com.eVolGreen.eVolGreen.Repositories.ChargerRepository;
import com.eVolGreen.eVolGreen.Repositories.PerfilCargaCargadorRepository;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.PerfilCargaCargadorService;
import org.springframework.beans.factory.annotation.Autowired;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class PerfilCargaCargadorServiceImplement implements PerfilCargaCargadorService {

    @Autowired
    private PerfilCargaCargadorRepository perfilCargaCargadorRepository;

    @Autowired
    private ChargerRepository chargerRepository;

    // Obtener un perfil de carga por el ID del cargador
    @Override
    public Optional<PerfilCargaCargador> getProfileByChargerId(Long chargerId) {
        return perfilCargaCargadorRepository.findByChargerId(chargerId);
    }

    // Crear o actualizar el perfil de carga
    @Override
    public PerfilCargaCargador saveOrUpdateProfile(Long chargerId, PerfilCargaCargador newProfile) {
        var charger = chargerRepository.findById(chargerId)
                .orElseThrow(() -> new IllegalArgumentException("Cargador no encontrado"));

        var existingProfileOpt = perfilCargaCargadorRepository.findByChargerId(chargerId);

        var profileToSave = existingProfileOpt.orElse(new PerfilCargaCargador());
        profileToSave.setConnectorId(newProfile.getConnectorId());
        profileToSave.setChargingProfileId(newProfile.getChargingProfileId());
        profileToSave.setStackLevel(newProfile.getStackLevel());
        profileToSave.setChargingProfilePurpose(newProfile.getChargingProfilePurpose());
        profileToSave.setChargingProfileKind(newProfile.getChargingProfileKind());
        profileToSave.setRecurrencyKind(newProfile.getRecurrencyKind() != null && !newProfile.getRecurrencyKind().isEmpty() ? newProfile.getRecurrencyKind() : null);
        profileToSave.setValidFrom(newProfile.getValidFrom());
        profileToSave.setValidTo(newProfile.getValidTo());
        profileToSave.setDuration(newProfile.getDuration());
        profileToSave.setStartSchedule(newProfile.getStartSchedule());
        profileToSave.setChargingRateUnit(newProfile.getChargingRateUnit());
        profileToSave.setMinChargingRate(newProfile.getMinChargingRate());
        profileToSave.setStartPeriod(newProfile.getStartPeriod());
        profileToSave.setLimite(newProfile.getLimite());
        profileToSave.setNumberPhases(newProfile.getNumberPhases());
        profileToSave.setChargerId(charger.getId());

        return perfilCargaCargadorRepository.save(profileToSave);
    }
}