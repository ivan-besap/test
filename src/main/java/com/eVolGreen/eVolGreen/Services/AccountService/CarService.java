package com.eVolGreen.eVolGreen.Services.AccountService;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO.CarDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Car.Car;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;

import java.util.List;

public interface CarService {

    List<CarDTO> getCarsDTO();

    void saveCar(Car car);

    Car findById(Long id);

    void deleteCar(Long id);

    List<CarDTO> getCarsDTOByEmpresa(Empresa empresa);

    CarDTO getCarDTO(Long id);
}