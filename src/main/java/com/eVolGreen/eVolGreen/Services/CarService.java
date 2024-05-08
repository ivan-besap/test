package com.eVolGreen.eVolGreen.Services;

import com.eVolGreen.eVolGreen.DTOS.CarDTO;
import com.eVolGreen.eVolGreen.Models.Car;

import java.util.List;

public interface CarService {

    List<CarDTO> getCarsDTO();

    void saveCar(Car car);
}
