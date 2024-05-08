package com.eVolGreen.eVolGreen.Services;

import com.eVolGreen.eVolGreen.DTOS.LocationDTO;
import com.eVolGreen.eVolGreen.Models.Location;

import java.util.List;

public interface LocationService {

    List<LocationDTO> getLocationsDTO();

    void saveLocation(Location location);
}
