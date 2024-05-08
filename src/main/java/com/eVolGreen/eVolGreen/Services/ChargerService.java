package com.eVolGreen.eVolGreen.Services;

import com.eVolGreen.eVolGreen.DTOS.ChargerDTO;
import com.eVolGreen.eVolGreen.Models.Charger;

import java.util.List;

public interface ChargerService {

    List<ChargerDTO> getChargersDTO();
    void saveCharger(Charger charger);
}
