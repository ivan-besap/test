package com.eVolGreen.eVolGreen.Services;

import com.eVolGreen.eVolGreen.DTOS.DeviceIdentifierDTO;
import com.eVolGreen.eVolGreen.Models.DeviceIdentifier;

import java.util.List;

public interface DeviceIdentifierService {
    List<DeviceIdentifierDTO> getDeviceIdentifiersDTO();

    void saveDeviceIdentifier(DeviceIdentifier deviceIdentifier);

    void deleteDeviceIdentifier(long id);

    DeviceIdentifier findById(Long id);
}
