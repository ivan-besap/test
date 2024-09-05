package com.eVolGreen.eVolGreen.Services.ImplementService.AccountServiceImplement;


import com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO.DeviceIdentifierDTO;
import com.eVolGreen.eVolGreen.Models.Account.Car.DeviceIdentifier;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Repositories.DeviceIdentifierRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.DeviceIdentifierService;
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

    @Override
    public void deleteDeviceIdentifier(long id) {
        deviceIdentifierRepository.deleteById(id);
    }

    @Override
    public DeviceIdentifier findById(Long id) {
        return deviceIdentifierRepository.findById(id).orElse(null);
    }

    @Override
    public List<DeviceIdentifierDTO> getDeviceIdentifiersByEmpresa(Empresa empresa) {
        return deviceIdentifierRepository.findUnassignedDeviceIdentifiersByEmpresa(empresa)
                .stream()
                .map(DeviceIdentifierDTO::new)
                .collect(Collectors.toList());
    }
}
