package com.eVolGreen.eVolGreen.Services.AccountService;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO.CarDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Car.Car;

import java.util.List;

public interface CarService {

    List<CarDTO> getCarsDTO();

    void saveCar(Car car);

    Car findById(Long id);

    void deleteCar(Long id);

    List<CarDTO> getCarsDTOByAccount(Account account);

    CarDTO getCarDTO(Long id);
}