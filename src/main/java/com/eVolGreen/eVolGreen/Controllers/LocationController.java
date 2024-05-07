package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.LocationDTO;
import com.eVolGreen.eVolGreen.Repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LocationController {
    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/locations")
    public List<LocationDTO> getLocations() {
        return locationRepository.findAll()
                .stream()
                .map(LocationDTO::new)
                .collect(Collectors.toList());
    }
}
