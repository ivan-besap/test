package com.eVolGreen.eVolGreen.Services.Implement;

import com.eVolGreen.eVolGreen.DTOS.DeviceIdentifierDTO;
import com.eVolGreen.eVolGreen.Models.DeviceIdentifier;
import com.eVolGreen.eVolGreen.Repositories.DeviceIdentifierRepository;
import com.eVolGreen.eVolGreen.Services.DeviceIdentifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceIdentifierServiceImplement implements DeviceIdentifierService {
    @Autowired
    private DeviceIdentifierRepository deviceIdentifierRepository;

    @Override
    public List<DeviceIdentifierDTO> getDeviceIdentifiersDTO() {
        return deviceIdentifierRepository.findAll()
                .stream()
                .map(DeviceIdentifierDTO::new)
                .collect(Collectors.toList());
    }
    @Override
    public void saveDeviceIdentifier(DeviceIdentifier deviceIdentifier) {
        deviceIdentifierRepository.save(deviceIdentifier);
    }
}
