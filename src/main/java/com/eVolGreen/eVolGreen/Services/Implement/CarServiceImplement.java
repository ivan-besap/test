package com.eVolGreen.eVolGreen.Services.Implement;

import com.eVolGreen.eVolGreen.DTOS.CarDTO;
import com.eVolGreen.eVolGreen.Models.Car;
import com.eVolGreen.eVolGreen.Repositories.CarRepository;
import com.eVolGreen.eVolGreen.Services.CarService;
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
}
