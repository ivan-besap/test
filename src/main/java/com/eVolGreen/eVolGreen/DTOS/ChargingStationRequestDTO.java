package com.eVolGreen.eVolGreen.DTOS;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.LocationDTO.LocationChargingStationDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargingStationsDTO;

public class ChargingStationRequestDTO {

    private ChargingStationsDTO chargingStationsDTO;

    private LocationChargingStationDTO locationDTO;


    public ChargingStationRequestDTO(ChargingStationsDTO chargingStationsDTO, LocationChargingStationDTO locationDTO) {
        this.chargingStationsDTO = chargingStationsDTO;
        this.locationDTO = locationDTO;
    }

    public ChargingStationsDTO getChargingStationsDTO() {
        return chargingStationsDTO;
    }

    public void setChargingStationsDTO(ChargingStationsDTO chargingStationsDTO) {
        this.chargingStationsDTO = chargingStationsDTO;
    }

    public LocationChargingStationDTO getLocationDTO() {
        return locationDTO;
    }

    public void setLocationDTO(LocationChargingStationDTO locationDTO) {
        this.locationDTO = locationDTO;
    }

    @Override
    public String toString() {
        return "ChargingStationRequestDTO{" +
                "chargingStationsDTO=" + chargingStationsDTO +
                ", locationDTO=" + locationDTO +
                '}';
    }
}
