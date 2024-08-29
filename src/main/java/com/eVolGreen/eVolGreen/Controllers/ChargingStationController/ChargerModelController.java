package com.eVolGreen.eVolGreen.Controllers.ChargingStationController;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.ChargerModelDTO;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargerModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ChargerModelController {

    @Autowired
    private ChargerModelService chargerModelService;

    @GetMapping("/models")
    public ResponseEntity<List<ChargerModelDTO>> getAllModels() {
        List<ChargerModelDTO> models = chargerModelService.getAllChargerModels();
        return ResponseEntity.ok(models);
    }
}
