package com.eVolGreen.eVolGreen.Services.AccountService;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.LocationDTO.LocationDTO;
import com.eVolGreen.eVolGreen.Models.Account.Location;

import java.util.List;

public interface LocationService {

    List<LocationDTO> getLocationsDTO();

    void saveLocation(Location location);

    Location findById(Long id);

    LocationDTO getLocationDTO(Long id);
}
