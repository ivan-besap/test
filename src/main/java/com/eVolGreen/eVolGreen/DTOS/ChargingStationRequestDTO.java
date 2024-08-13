package com.eVolGreen.eVolGreen.DTOS;

public class ChargingStationRequestDTO {

    private ChargingStationsDTO chargingStationsDTO;
    private LocationDTO locationDTO;

    public ChargingStationRequestDTO() {
    }

    public ChargingStationRequestDTO(ChargingStationsDTO chargingStationsDTO, LocationDTO locationDTO) {
        this.chargingStationsDTO = chargingStationsDTO;
        this.locationDTO = locationDTO;
    }

    public ChargingStationsDTO getChargingStationsDTO() {
        return chargingStationsDTO;
    }

    public void setChargingStationsDTO(ChargingStationsDTO chargingStationsDTO) {
        this.chargingStationsDTO = chargingStationsDTO;
    }

    public LocationDTO getLocationDTO() {
        return locationDTO;
    }

    public void setLocationDTO(LocationDTO locationDTO) {
        this.locationDTO = locationDTO;
    }
}
