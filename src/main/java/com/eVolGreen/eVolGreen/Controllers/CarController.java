package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.CarDTO;
import com.eVolGreen.eVolGreen.Models.*;
import com.eVolGreen.eVolGreen.Repositories.CarRepository;
import com.eVolGreen.eVolGreen.Services.*;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CarController {
    @Autowired
    private CarService carService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private DeviceIdentifierService deviceIdentifierService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CarRepository carRepository;

    @GetMapping("/cars")
    public List<CarDTO> getCars() {
        return carService.getCarsDTO();
    }
    @GetMapping("/cars/{id}")
    public CarDTO getCardDTO(@PathVariable Long id) {
        return carService.getCardDTO(id);
    }

    @PostMapping("/clients/current/car")
    public ResponseEntity<Object> createCar(Authentication authentication,
                                            @RequestBody Car newCar,
                                            @RequestParam String numberAccount) {

        Client clientCurrent = clientService.findByEmail(authentication.getName());
        String message = "";
        try {
            // Validate parameters
            if (newCar.getLicensePlate().isBlank()){
                message = "Missing license plate";
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }
            if (newCar.getModel().isBlank()){
                message = "Missing model";
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }
            if (newCar.getVin().isBlank()){
                message = "Missing vin";
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }
            if (newCar.getColor().isBlank()){
                message = "Missing color";
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }
            if (newCar.getBrand().isBlank()){
                message = "Missing make";
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }
            if (newCar.getManufactureYear().isBlank()){
                message = "Missing year";
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }
            if (newCar.getCapacityFullPower() == null){
                message = "Missing mileage";
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }
            if (numberAccount.isBlank()){
                message = "Missing account number";
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }

            // Find the account with the provided account number
            Optional<Account> optionalAccount = clientCurrent.getAccounts().stream()
                    .filter(account -> account.getNumber().equals(numberAccount))
                    .findFirst();

            // Check if the account exists
            if (optionalAccount.isEmpty()) {
                message = "The provided account number does not belong to the current client";
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }

            // Get the account
            Account selectedAccount = optionalAccount.get();

            // Create the car
            Car car = new Car( newCar.getLicensePlate(),
                    newCar.getModel(),
                    newCar.getVin(),
                    newCar.getColor(),
                    newCar.getBrand(),
                    newCar.getManufactureYear(),
                    newCar.getCapacityFullPower());

            // Set the car's identifier
            String indentifierName ="CARD-" + getStringRandomIdentifier();
            long indentifierNumber = getIndentifierNumber() ;
            DeviceIdentifier cardIdentifier = new DeviceIdentifier(
                    indentifierName,
                    indentifierNumber,
                    "Card",
                    car);
            car.addDeviceIdentifier(cardIdentifier);
            cardIdentifier.setCar(car);

            String body = String.valueOf(cardIdentifier);
            // Set the car's account
            car.setAccount(selectedAccount);

            String email = clientCurrent.getEmail();
            Email emailMessage = new Email( email,
                        "Your Car Has Been Successfully Created!",
                                body);
            emailService.sendCarCreatedEmail(emailMessage);

            // Save the car
            carRepository.save(car);
            deviceIdentifierService.saveDeviceIdentifier(cardIdentifier);
            return new ResponseEntity<>("Car created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating car: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    int min = 00000000;
    int max = 99999999;

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public String getStringRandomIdentifier() {
        int randomNumber = getRandomNumber(min, max);
        return String.valueOf(randomNumber);
    }

    public Long getIndentifierNumber (){
        long randomNumber = getRandomNumber(min, max);
        return randomNumber;
    }

    @DeleteMapping("/clients/current/car/{id}")
    public ResponseEntity<Object> deleteCar(Authentication authentication, @PathVariable Long id) {
        Client client = clientService.findByEmail(authentication.getName());
        Car car = carService.findById(id);

        if (car == null) {
            String message = "Car not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        // Verificar si el carro pertenece al cliente actual
        if (!car.getAccount().getClient().equals(client)) {
            String message = "Unauthorized access: Car does not belong to the current client";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }

        // Buscar el dispositivo identificador asociado al carro
        DeviceIdentifier deviceIdentifier = null;
        for (DeviceIdentifier identifier : car.getDeviceIdentifiers()) {
            if (identifier.getCar().equals(car)) {
                deviceIdentifier = identifier;
                break;
            }
        }

        if (deviceIdentifier != null) {
            // Eliminar el dispositivo identificador asociado al carro
            deviceIdentifierService.deleteDeviceIdentifier(deviceIdentifier.getId());
        }

        // Eliminar el carro
        carService.deleteCar(id);

        // Enviar correo electrónico de confirmación de eliminación
        String email = client.getEmail();
        String body = "Your car has been deleted successfully.";
        Email emailMessage = new Email(email, "Your Car Has Been Successfully Deleted!", body);
        emailService.sendCarDeletedEmail(emailMessage);

        String message = "Car deleted successfully";
        return ResponseEntity.ok(message);
    }




}
