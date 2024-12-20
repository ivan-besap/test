package com.eVolGreen.eVolGreen.Services.AccountService;


import com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO.DeviceIdentifierDTO;
import com.eVolGreen.eVolGreen.Models.Account.Car.DeviceIdentifier;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargerStatus;

import java.util.List;

public interface DeviceIdentifierService {

    List<DeviceIdentifierDTO> getDeviceIdentifiersDTO();

    void saveDeviceIdentifier(DeviceIdentifier deviceIdentifier);

    boolean updateRfidStatus(Long id, Boolean usable);

    void deleteDeviceIdentifier(long id);

    DeviceIdentifier findById(Long id);

    List<DeviceIdentifierDTO> getDeviceIdentifiersByEmpresa(Empresa empresa);


}
