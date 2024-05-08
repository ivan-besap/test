package com.eVolGreen.eVolGreen.Services;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationStatusDTO;
import com.eVolGreen.eVolGreen.Models.ChargingStationStatus;

import java.util.List;

public interface ChargingStationsStatusService {

    List<ChargingStationStatusDTO> getChargingStationsStatusDTO();

    void saveChargingStationsStatus(ChargingStationStatus chargingStationStatus);
}
