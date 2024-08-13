package com.eVolGreen.eVolGreen.Services.Implement;

import com.eVolGreen.eVolGreen.DTOS.LocationDTO;
import com.eVolGreen.eVolGreen.Models.Location;
import com.eVolGreen.eVolGreen.Repositories.LocationRepository;
import com.eVolGreen.eVolGreen.Services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationServiceImplement implements LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<LocationDTO> getLocationsDTO() {
        return locationRepository.findAll()
                .stream()
                .map(LocationDTO::new)
                .collect(Collectors.toList());
    }
    @Override
    public void saveLocation(Location location) {
        locationRepository.save(location);
    }

    @Override
    public Location findById(Long id) {
        return locationRepository.findById(id).orElse(null);
    }

    @Override
    public LocationDTO getLocationDTO(Long id) {
        return new LocationDTO(this.findById(id));
    }
}
