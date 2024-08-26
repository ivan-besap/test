package com.eVolGreen.eVolGreen.Controllers.AccountController;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO.DeviceIdentifierDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO.NewDeviceIdentifierDTO;
import com.eVolGreen.eVolGreen.Models.Account.Car.Car;
import com.eVolGreen.eVolGreen.Models.Account.Car.DeviceIdentifier;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import com.eVolGreen.eVolGreen.Repositories.DeviceIdentifierRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.CarService;
import com.eVolGreen.eVolGreen.Services.AccountService.DeviceIdentifierService;
import com.eVolGreen.eVolGreen.Services.DUserService.CompanyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DeviceIdentifierController {

    @Autowired
    private DeviceIdentifierService deviceIdentifierService;

    @Autowired
    private DeviceIdentifierRepository deviceIdentifierRepository;

    @Autowired
    private CompanyUserService companyService;

    @Autowired
    private CarService carService;

    @GetMapping("/deviceIdentifiers")
    public List<DeviceIdentifierDTO> getDeviceIdentifiers() {
        return deviceIdentifierService.getDeviceIdentifiersDTO();
    }

    @GetMapping("/companies/current/deviceIdentifiers/{id}")
    public DeviceIdentifierDTO getDeviceIdentifier(@PathVariable Long id) {
        return new DeviceIdentifierDTO(deviceIdentifierService.findById(id));
    }

    @PostMapping("/companies/current/device-identifiers")
    public ResponseEntity<Object> createDeviceIdentifier(Authentication authentication,
                                                         @RequestBody NewDeviceIdentifierDTO deviceIdentifierDTO) {

        CompanyUser companyUser = companyService.findByEmailCompanyUser(authentication.getName());
        String mensaje = " ";

        if (companyUser == null) {
            mensaje = "La compañia no se encontro";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        if (deviceIdentifierDTO.getNombreDeIdentificador() == null){
            mensaje = " El nombre de la tarjeta RFID es necesario";
            return new ResponseEntity<>(mensaje,HttpStatus.FORBIDDEN);
        }
        if (deviceIdentifierDTO.getRfid() == null){
            mensaje = "El codigo RFID es necesario";
            return new ResponseEntity<>(mensaje,HttpStatus.FORBIDDEN);
        }

        Car car = carService.findById(deviceIdentifierDTO.getAuto());
        if (car == null) {
            mensaje = "El auto es necesario para asociar la tarjeta RFID";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        DeviceIdentifier newDeviceIdentifier = new DeviceIdentifier(
                deviceIdentifierDTO.getNombreDeIdentificador(),
                deviceIdentifierDTO.getRfid(),
                deviceIdentifierDTO.getFechaExpiracion(),
                car,
                false
        );

        deviceIdentifierService.saveDeviceIdentifier(newDeviceIdentifier);
        return new ResponseEntity<>("Device Identifier created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/companies/current/device-identifiers/{id}")
    public ResponseEntity<Object> updateDeviceIdentifier(Authentication authentication,
                                                         @PathVariable Long id,
                                                         @RequestBody NewDeviceIdentifierDTO deviceIdentifierDTO) {

        // Obtener la compañía del usuario autenticado
        CompanyUser companyUser = companyService.findByEmailCompanyUser(authentication.getName());
        String mensaje = " ";

        if (companyUser == null) {
            mensaje = "La compañía no se encontró";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        // Buscar el DeviceIdentifier por su ID
        DeviceIdentifier existingDeviceIdentifier = deviceIdentifierService.findById(id);
        if (existingDeviceIdentifier == null) {
            mensaje = "El identificador del dispositivo no se encontró";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        // Validar los campos necesarios
        if (deviceIdentifierDTO.getNombreDeIdentificador() == null) {
            mensaje = "El nombre de la tarjeta RFID es necesario";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (deviceIdentifierDTO.getRfid() == null) {
            mensaje = "El código RFID es necesario";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }

        // Buscar el auto por su ID
        Car car = carService.findById(deviceIdentifierDTO.getAuto());
        if (car == null) {
            mensaje = "El auto es necesario para asociar la tarjeta RFID";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        // Actualizar los campos del DeviceIdentifier existente
        existingDeviceIdentifier.setNombreDeIdentificador(deviceIdentifierDTO.getNombreDeIdentificador());
        existingDeviceIdentifier.setRFID(deviceIdentifierDTO.getRfid());
        existingDeviceIdentifier.setFechaExpiracion(deviceIdentifierDTO.getFechaExpiracion());
        existingDeviceIdentifier.setAuto(car);

        // Guardar los cambios en la base de datos
        deviceIdentifierService.saveDeviceIdentifier(existingDeviceIdentifier);

        return new ResponseEntity<>("Device Identifier updated successfully", HttpStatus.OK);
    }

    @PatchMapping("companies/current/device-identifiers/{id}/delete")
    public ResponseEntity<Object> deleteDeviceIdentifier(Authentication authentication,
                                                         @PathVariable Long id) {
        // Obtener la compañía del usuario autenticado
        CompanyUser companyUser = companyService.findByEmailCompanyUser(authentication.getName());
        String mensaje = " ";

        if (companyUser == null) {
            mensaje = "La compañía no se encontró";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }

        // Buscar el DeviceIdentifier por su ID
        DeviceIdentifier deviceIdentifier = deviceIdentifierService.findById(id);
        if (deviceIdentifier == null) {
            mensaje = "El identificador del dispositivo no se encontró";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }

        // Verificar si el DeviceIdentifier ya está desactivado
        if (!deviceIdentifier.getActivo()) {
            mensaje = "El identificador del dispositivo ya está desactivado";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
        }

        // Desactivar el DeviceIdentifier
        deviceIdentifier.setActivo(false);
        deviceIdentifierService.saveDeviceIdentifier(deviceIdentifier);

        mensaje = "Identificador del dispositivo desactivado correctamente";
        return ResponseEntity.ok(mensaje);
    }

}
