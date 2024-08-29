package com.eVolGreen.eVolGreen.Controllers.ChargingStationController;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.ChargerManufacturerDTO;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargerManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ChargerManufacturerController {

    @Autowired
    private ChargerManufacturerService chargerManufacturerService;

    @GetMapping("/manufacturers")
    public ResponseEntity<List<ChargerManufacturerDTO>> getAllManufacturers() {
        List<ChargerManufacturerDTO> manufacturers = chargerManufacturerService.getAllChargerManufacturers();
        return ResponseEntity.ok(manufacturers);
    }
}
