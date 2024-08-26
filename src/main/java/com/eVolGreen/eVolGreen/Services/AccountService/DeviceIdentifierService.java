package com.eVolGreen.eVolGreen.Services.AccountService;


import com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO.DeviceIdentifierDTO;
import com.eVolGreen.eVolGreen.Models.Account.Car.DeviceIdentifier;

import java.util.List;

public interface DeviceIdentifierService {

    List<DeviceIdentifierDTO> getDeviceIdentifiersDTO();

    void saveDeviceIdentifier(DeviceIdentifier deviceIdentifier);

    void deleteDeviceIdentifier(long id);

    DeviceIdentifier findById(Long id);
}
