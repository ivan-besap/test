package com.eVolGreen.eVolGreen.Controllers.AccountController;


import com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO.CarCompanyDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO.NewCarCompanyDTO;

import com.eVolGreen.eVolGreen.Models.Account.Car.Car;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import com.eVolGreen.eVolGreen.Repositories.CarRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountCompanyService;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountService;
import com.eVolGreen.eVolGreen.Services.AccountService.CarService;
import com.eVolGreen.eVolGreen.Services.AccountService.DeviceIdentifierService;
import com.eVolGreen.eVolGreen.Services.DUserService.ClientUserService;
import com.eVolGreen.eVolGreen.Services.DUserService.CompanyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CarController {
    @Autowired
    private CarService carService;
    @Autowired
    private ClientUserService clientUserService;
    @Autowired
    private DeviceIdentifierService deviceIdentifierService;
//    @Autowired
//    private EmailService emailService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CompanyUserService companyService;
    @Autowired
    private AccountCompanyService accountCompanyService;

    @GetMapping("/cars")
    public List<CarCompanyDTO> getCars() {
        return carService.getCarsCompanyDTO();
    }

    @GetMapping("/companies/current/cars/{id}")
    public CarCompanyDTO getCardDTO(@PathVariable Long id) {
        return carService.getCardCompanyDTO(id);
    }

    @PostMapping("/companies/current/cars")
    public ResponseEntity<Object> createCar (Authentication authentication,
                                             @RequestBody NewCarCompanyDTO car){

        CompanyUser company = companyService.findByEmailCompanyUser(authentication.getName());
        String mensaje = " ";

        if (company == null) {
            mensaje = "La compañia no se encontro";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        if (car.getPatente() == null){
            mensaje = "La pantente es necesaria";
            return new ResponseEntity<>(mensaje,HttpStatus.FORBIDDEN);
        }
        if (car.getModelo() == null){
            mensaje = "El modelo es necesario";
            return new ResponseEntity<>(mensaje,HttpStatus.FORBIDDEN);
        }
        if(car.getVin() == null){
            mensaje = "El vin es necesario";
            return new ResponseEntity<>(mensaje,HttpStatus.FORBIDDEN);
        }
        if(car.getColor() == null){
            mensaje = "El color del auto es necesario";
            return new ResponseEntity<>(mensaje,HttpStatus.FORBIDDEN);
        }
        if(car.getMarca() == null){
            mensaje = "La marca es necesaria en el auto";
            return new ResponseEntity<>(mensaje,HttpStatus.FORBIDDEN);
        }
        if (car.getAñoFabricacion() ==null){
            mensaje = "El año de fabricacion es necesario";
            return new ResponseEntity<>(mensaje,HttpStatus.FORBIDDEN);
        }
        if (car.getCapacidadPotencia() == null){
            mensaje = "La capacidad de potencia maxima del auto es necesaria";
            return  new ResponseEntity<>(mensaje,HttpStatus.FORBIDDEN);
        }

        Optional<AccountCompany> optionalAccountCompany = company.getCuentaCompañia().stream().findFirst();
        if (optionalAccountCompany.isEmpty()) {
            return new ResponseEntity<>("No se encontró la cuenta de la compañía", HttpStatus.NOT_FOUND);
        }

        AccountCompany accountCompany = optionalAccountCompany.get();

        Car nuevoAuto = new Car(
                car.getPatente(),
                car.getModelo(),
                car.getVin(),
                car.getColor(),
                car.getMarca(),
                car.getAñoFabricacion(),
                car.getCapacidadPotencia(),
                true
        );
        nuevoAuto.setCuentaCompañia(accountCompany);
        carService.saveCar(nuevoAuto);

        mensaje = "El auto se ha creado con exito";
        return new ResponseEntity<>(mensaje,HttpStatus.CREATED);
    }

    @PutMapping("/companies/current/cars/{id}")
    public ResponseEntity<Object> updateCar(Authentication authentication,
                                            @PathVariable Long id,
                                            @RequestBody NewCarCompanyDTO carDTO) {

        // Verificar si la compañía del usuario autenticado existe
        CompanyUser company = companyService.findByEmailCompanyUser(authentication.getName());
        if (company == null) {
            return new ResponseEntity<>("La compañía no se encontró", HttpStatus.NOT_FOUND);
        }

        // Buscar el auto existente por su ID
        Car existingCar = carService.findById(id);
        if (existingCar == null) {
            return new ResponseEntity<>("El auto no se encontró", HttpStatus.NOT_FOUND);
        }

        // Verificar los campos requeridos en el DTO
        if (carDTO.getPatente() == null) {
            return new ResponseEntity<>("La patente es necesaria", HttpStatus.FORBIDDEN);
        }
        if (carDTO.getModelo() == null) {
            return new ResponseEntity<>("El modelo es necesario", HttpStatus.FORBIDDEN);
        }
        if (carDTO.getVin() == null) {
            return new ResponseEntity<>("El VIN es necesario", HttpStatus.FORBIDDEN);
        }
        if (carDTO.getColor() == null) {
            return new ResponseEntity<>("El color del auto es necesario", HttpStatus.FORBIDDEN);
        }
        if (carDTO.getMarca() == null) {
            return new ResponseEntity<>("La marca es necesaria en el auto", HttpStatus.FORBIDDEN);
        }
        if (carDTO.getAñoFabricacion() == null) {
            return new ResponseEntity<>("El año de fabricación es necesario", HttpStatus.FORBIDDEN);
        }
        if (carDTO.getCapacidadPotencia() == null) {
            return new ResponseEntity<>("La capacidad de potencia máxima del auto es necesaria", HttpStatus.FORBIDDEN);
        }

        // Actualizar los datos del auto existente
        existingCar.setPatente(carDTO.getPatente());
        existingCar.setModelo(carDTO.getModelo());
        existingCar.setVin(carDTO.getVin());
        existingCar.setColor(carDTO.getColor());
        existingCar.setMarca(carDTO.getMarca());
        existingCar.setAñoFabricacion(carDTO.getAñoFabricacion());
        existingCar.setCapacidadPotencia(carDTO.getCapacidadPotencia());

        // Guardar los cambios en la base de datos
        carService.saveCar(existingCar);

        return new ResponseEntity<>("El auto se ha actualizado con éxito", HttpStatus.OK);
    }

    @PatchMapping("/companies/current/cars/{id}/delete")
    public ResponseEntity<Object> deleteCar(Authentication authentication, @PathVariable Long id) {
        // Obtener la compañía del usuario autenticado
        CompanyUser company = companyService.findByEmailCompanyUser(authentication.getName());
        if (company == null) {
            return new ResponseEntity<>("La compañía no se encontró", HttpStatus.NOT_FOUND);
        }

        // Buscar el auto por su ID
        Car car = carService.findById(id);
        if (car == null) {
            return new ResponseEntity<>("El auto no se encontró", HttpStatus.NOT_FOUND);
        }

        // Verificar si el auto ya está desactivado
        if (!car.getActivo()) {
            return new ResponseEntity<>("El auto ya está desactivado", HttpStatus.BAD_REQUEST);
        }

        // Desactivar el auto
        car.setActivo(false);
        carService.saveCar(car);

        return ResponseEntity.ok("El auto ha sido desactivado correctamente.");
    }


//
//    @PostMapping("/clients/current/car")
//    public ResponseEntity<Object> createCar(Authentication authentication,
//                                            @RequestBody Car newCar,
//                                            @RequestParam String numberAccount) {
//
//        Client clientCurrent = clientUserService.findByEmail(authentication.getName());
//        String message = "";
//        try {
//            // Validate parameters
//            if (newCar.getLicensePlate().isBlank()){
//                message = "Missing license plate";
//                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//            }
//            if (newCar.getModel().isBlank()){
//                message = "Missing model";
//                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//            }
//            if (newCar.getVin().isBlank()){
//                message = "Missing vin";
//                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//            }
//            if (newCar.getColor().isBlank()){
//                message = "Missing color";
//                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//            }
//            if (newCar.getBrand().isBlank()){
//                message = "Missing make";
//                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//            }
//            if (newCar.getManufactureYear().isBlank()){
//                message = "Missing year";
//                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//            }
//            if (newCar.getCapacityFullPower() == null){
//                message = "Missing mileage";
//                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//            }
//            if (numberAccount.isBlank()){
//                message = "Missing account number";
//                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//            }
//
//            // Find the account with the provided account number
//            Optional<Account> optionalAccount = clientCurrent.getAccounts().stream()
//                    .filter(account -> account.getNumber().equals(numberAccount))
//                    .findFirst();
//
//            // Check if the account exists
//            if (optionalAccount.isEmpty()) {
//                message = "The provided account number does not belong to the current client";
//                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//            }
//
//            // Get the account
//            Account selectedAccount = optionalAccount.get();
//
//            // Create the car
//            Car car = new Car( newCar.getLicensePlate(),
//                    newCar.getModel(),
//                    newCar.getVin(),
//                    newCar.getColor(),
//                    newCar.getBrand(),
//                    newCar.getManufactureYear(),
//                    newCar.getCapacityFullPower());
//
//            // Set the car's identifier
//            String indentifierName ="CARD-" + getStringRandomIdentifier();
//            long indentifierNumber = getIndentifierNumber() ;
//            DeviceIdentifier cardIdentifier = new DeviceIdentifier(
//                    indentifierName,
//                    indentifierNumber,
//                    "Card",
//                    car);
//            car.addDeviceIdentifier(cardIdentifier);
//            cardIdentifier.setCar(car);
//
//            String body = String.valueOf(cardIdentifier);
//            // Set the car's account
//            car.setAccount(selectedAccount);
//
//            String email = clientCurrent.getEmail();
//            Email emailMessage = new Email( email,
//                        "Your Car Has Been Successfully Created!",
//                                body);
//            emailService.sendCarCreatedEmail(emailMessage);
//
//            // Save the car
//            carRepository.save(car);
//            deviceIdentifierService.saveDeviceIdentifier(cardIdentifier);
//            return new ResponseEntity<>("Car created successfully", HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Error creating car: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    int min = 00000000;
//    int max = 99999999;
//
//    public int getRandomNumber(int min, int max) {
//        return (int) ((Math.random() * (max - min)) + min);
//    }
//
//    public String getStringRandomIdentifier() {
//        int randomNumber = getRandomNumber(min, max);
//        return String.valueOf(randomNumber);
//    }
//
//    public Long getIndentifierNumber (){
//        long randomNumber = getRandomNumber(min, max);
//        return randomNumber;
//    }
//
//    @DeleteMapping("/clients/current/car/{id}")
//    public ResponseEntity<Object> deleteCar(Authentication authentication, @PathVariable Long id) {
//        Client client = clientUserService.findByEmail(authentication.getName());
//        Car car = carService.findById(id);
//
//        if (car == null) {
//            String message = "Car not found";
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
//        }
//
//        // Verificar si el carro pertenece al cliente actual
//        if (!car.getAccount().getClient().equals(client)) {
//            String message = "Unauthorized access: Car does not belong to the current client";
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
//        }
//
//        // Buscar el dispositivo identificador asociado al carro
//        DeviceIdentifier deviceIdentifier = null;
//        for (DeviceIdentifier identifier : car.getDeviceIdentifiers()) {
//            if (identifier.getCar().equals(car)) {
//                deviceIdentifier = identifier;
//                break;
//            }
//        }
//
//        if (deviceIdentifier != null) {
//            // Eliminar el dispositivo identificador asociado al carro
//            deviceIdentifierService.deleteDeviceIdentifier(deviceIdentifier.getId());
//        }
//
//        // Eliminar el carro
//        carService.deleteCar(id);
//
//        // Enviar correo electrónico de confirmación de eliminación
//        String email = client.getEmail();
//        String body = "Your car has been deleted successfully.";
//        Email emailMessage = new Email(email, "Your Car Has Been Successfully Deleted!", body);
//        emailService.sendCarDeletedEmail(emailMessage);
//
//        String message = "Car deleted successfully";
//        return ResponseEntity.ok(message);
//    }
//
//


}
