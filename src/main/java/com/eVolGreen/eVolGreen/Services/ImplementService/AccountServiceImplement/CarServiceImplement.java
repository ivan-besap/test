package com.eVolGreen.eVolGreen.Services.ImplementService.AccountServiceImplement;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO.CarDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Car.Car;
import com.eVolGreen.eVolGreen.Repositories.CarRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImplement implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<CarDTO> getCarsDTO() {
        return carRepository.findAll()
                .stream()
                .map(CarDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void saveCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<CarDTO> getCarsDTOByAccount(Account account) {
        List<Car> cars = carRepository.findByAccountAndActivo(account, true);
        return cars.stream()
                .map(CarDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public CarDTO getCarDTO(Long id) {
        return carRepository.findById(id)
                .map(CarDTO::new)
                .orElse(null);
    }
}
