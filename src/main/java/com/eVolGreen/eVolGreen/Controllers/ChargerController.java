package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.ChargerDTO;
import com.eVolGreen.eVolGreen.Repositories.ChargerRepository;
import com.eVolGreen.eVolGreen.Services.ChargerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ChargerController {
    @Autowired
    private ChargerService chargerService;
    @Autowired
    private ChargerRepository chargerRepository;

    @GetMapping("/chargers")
    public List<ChargerDTO> getChargers() {
        return chargerService.getChargersDTO();
    }
}
