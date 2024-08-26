package com.eVolGreen.eVolGreen.Services.ImplementService.AccountServiceImplement;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.LocationDTO.LocationAccountCompanyDTO;
import com.eVolGreen.eVolGreen.Models.Account.Location;
import com.eVolGreen.eVolGreen.Repositories.LocationRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationServiceImplement implements LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<LocationAccountCompanyDTO> getLocationsCompanyDTO() {
        return locationRepository.findAll()
                .stream()
                .map(LocationAccountCompanyDTO::new)
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
    public LocationAccountCompanyDTO getLocationCompanyDTO(Long id) {
        return new LocationAccountCompanyDTO(this.findById(id));
    }

    @Override
    public void saveLocationCompany(Location location) {
        locationRepository.save(location);
    }
}
