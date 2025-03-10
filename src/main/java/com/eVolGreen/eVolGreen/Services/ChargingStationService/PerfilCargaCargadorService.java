package com.eVolGreen.eVolGreen.Services.ChargingStationService;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.ReporteListDTO;
import com.eVolGreen.eVolGreen.Models.Account.Fee.EstadoPerfil;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.PerfilCargaCargador;

import java.util.List;
import java.util.Optional;

public interface PerfilCargaCargadorService {

    // Obtener perfil de carga por el ID del cargador
    Optional<PerfilCargaCargador> getProfileByChargerId(Long chargerId);

    // Guardar o actualizar un perfil de carga
    PerfilCargaCargador saveOrUpdateProfile(Long chargerId, PerfilCargaCargador perfilCargaCargador);

    void save(PerfilCargaCargador perfil);

    List<PerfilCargaCargador> findAll();

    PerfilCargaCargador findByChargerIdAndEstado(Long chargerId, EstadoPerfil estadoPerfil);

    List<PerfilCargaCargador> findAllByChargerIdAndEstado(Long chargerId, EstadoPerfil estadoPerfil);

    List<PerfilCargaCargador> findAllWithFeeAndDays();

    Optional<PerfilCargaCargador> findDefaultActiveProfileByChargerIdAndDay(Long chargerId);

    PerfilCargaCargador findByChargerIdAndStackLevel(Long chargerId, Integer stackLevel);

    Optional<PerfilCargaCargador> findTopByChargerIdAndStackLevel(Long chargerId, Integer stackLevel);
}