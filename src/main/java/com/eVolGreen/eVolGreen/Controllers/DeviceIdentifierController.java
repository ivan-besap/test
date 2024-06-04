package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.DeviceIdentifierDTO;
import com.eVolGreen.eVolGreen.Models.DeviceIdentifier;
import com.eVolGreen.eVolGreen.Repositories.DeviceIdentifierRepository;
import com.eVolGreen.eVolGreen.Services.DeviceIdentifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class DeviceIdentifierController {
    @Autowired
    private DeviceIdentifierService deviceIdentifierService;
    @Autowired
    private DeviceIdentifierRepository deviceIdentifierRepository;

    @GetMapping("/deviceIdentifiers")
    public List<DeviceIdentifierDTO> getDeviceIdentifiers() {
        return deviceIdentifierService.getDeviceIdentifiersDTO();
    }

    @GetMapping("/deviceIdentifiers/{id}")
    public DeviceIdentifierDTO getDeviceIdentifier(@PathVariable Long id) {
        return new DeviceIdentifierDTO(deviceIdentifierService.findById(id));
    }
}
