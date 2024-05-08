package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.CarDTO;
import com.eVolGreen.eVolGreen.Repositories.CarRepository;
import com.eVolGreen.eVolGreen.Services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CarController {
    @Autowired
    private CarService carService;
    @Autowired
    private CarRepository carRepository;

    @GetMapping("/cars")
    public List<CarDTO> getCars() {
        return carService.getCarsDTO();
    }
}
