package com.eVolGreen.eVolGreen.Controllers.AccountController;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO.DeviceIdentifierDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO.NewDeviceIdentifierDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Car.Car;
import com.eVolGreen.eVolGreen.Models.Account.Car.DeviceIdentifier;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Repositories.DeviceIdentifierRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountService;
import com.eVolGreen.eVolGreen.Services.AccountService.AuditLogService;
import com.eVolGreen.eVolGreen.Services.AccountService.CarService;
import com.eVolGreen.eVolGreen.Services.AccountService.DeviceIdentifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class DeviceIdentifierController {

    @Autowired
    private DeviceIdentifierService deviceIdentifierService;

    @Autowired
    private DeviceIdentifierRepository deviceIdentifierRepository;

    @Autowired
    private AuditLogService auditLogService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CarService carService;

    @GetMapping("/deviceIdentifiers")
    public List<DeviceIdentifierDTO> getDeviceIdentifiers() {
        return deviceIdentifierService.getDeviceIdentifiersDTO();
    }

    @GetMapping("/accounts/current/deviceIdentifiers/{id}")
    public ResponseEntity<DeviceIdentifierDTO> getDeviceIdentifier(@PathVariable Long id) {
        DeviceIdentifier deviceIdentifier = deviceIdentifierService.findById(id);
        if (deviceIdentifier == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DeviceIdentifierDTO(deviceIdentifier), HttpStatus.OK);
    }

    // Método para obtener dispositivos filtrados por usuario y estado activo
    @GetMapping("/empresa/current/deviceIdentifiers")
    public List<DeviceIdentifierDTO> getDeviceIdentifiersByEmpresa(Authentication authentication) {

        Optional<Account> accountOptional = accountService.findByEmail(authentication.getName());
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            Empresa empresa = account.getEmpresa(); // Obtener la empresa asociada a la cuenta
            List<DeviceIdentifier> deviceIdentifiers = deviceIdentifierRepository.findByEmpresaAndActivo(empresa, true);
            return deviceIdentifiers.stream().map(DeviceIdentifierDTO::new).collect(Collectors.toList());
        } else {
            return Collections.emptyList(); // Si no hay cuenta, devolver lista vacía
        }
    }


    @PostMapping("/accounts/current/device-identifiers")
    public ResponseEntity<Object> createDeviceIdentifier(Authentication authentication,
                                                         @RequestBody NewDeviceIdentifierDTO deviceIdentifierDTO) {

        // Buscar la cuenta del usuario autenticado
        Optional<Account> accountOptional = accountService.findByEmail(authentication.getName());
        if (accountOptional.isEmpty()) {
            return new ResponseEntity<>("La cuenta no se encontró", HttpStatus.NOT_FOUND);
        }
        Account account = accountOptional.get();

        Empresa empresa = account.getEmpresa();
        if (empresa == null) {
            return new ResponseEntity<>("No se encontró la empresa asociada", HttpStatus.NOT_FOUND);
        }

        String mensaje = "";
        if (deviceIdentifierDTO.getNombreDeIdentificador() == null) {
            mensaje = "El nombre de la tarjeta RFID es necesario";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (deviceIdentifierDTO.getRfid() == null) {
            mensaje = "El código RFID es necesario";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }

        DeviceIdentifier newDeviceIdentifier = new DeviceIdentifier(
                deviceIdentifierDTO.getNombreDeIdentificador(),
                deviceIdentifierDTO.getRfid(),
                deviceIdentifierDTO.getFechaExpiracion(),
                null,
                true
        );
        newDeviceIdentifier.setEmpresa(empresa);

        deviceIdentifierService.saveDeviceIdentifier(newDeviceIdentifier);

        String descripcion = "Usuario " + account.getEmail() + " creó una tarjeta RFID de nombre: " + deviceIdentifierDTO.getNombreDeIdentificador();
        auditLogService.recordAction(descripcion, account);

        return new ResponseEntity<>("Identificador de dispositivo creado exitosamente", HttpStatus.CREATED);
    }

    @PutMapping("/accounts/current/device-identifiers/{id}")
    public ResponseEntity<Object> updateDeviceIdentifier(Authentication authentication,
                                                         @PathVariable Long id,
                                                         @RequestBody NewDeviceIdentifierDTO deviceIdentifierDTO) {

        Optional<Account> accountOptional = accountService.findByEmail(authentication.getName());
        if (accountOptional.isEmpty()) {
            return new ResponseEntity<>("La cuenta no se encontró", HttpStatus.NOT_FOUND);
        }
        Account account = accountOptional.get();

        DeviceIdentifier existingDeviceIdentifier = deviceIdentifierService.findById(id);
        if (existingDeviceIdentifier == null) {
            return new ResponseEntity<>("El identificador del dispositivo no se encontró", HttpStatus.NOT_FOUND);
        }

        if (deviceIdentifierDTO.getNombreDeIdentificador() == null) {
            return new ResponseEntity<>("El nombre de la tarjeta RFID es necesario", HttpStatus.FORBIDDEN);
        }
        if (deviceIdentifierDTO.getRfid() == null) {
            return new ResponseEntity<>("El código RFID es necesario", HttpStatus.FORBIDDEN);
        }

        existingDeviceIdentifier.setNombreDeIdentificador(deviceIdentifierDTO.getNombreDeIdentificador());
        existingDeviceIdentifier.setRFID(deviceIdentifierDTO.getRfid());
        existingDeviceIdentifier.setFechaExpiracion(deviceIdentifierDTO.getFechaExpiracion());

        if (deviceIdentifierDTO.getAuto() != null) {
            Car car = carService.findById(deviceIdentifierDTO.getAuto());
            if (car == null) {
                return new ResponseEntity<>("El auto proporcionado no se encontró", HttpStatus.NOT_FOUND);
            }
            existingDeviceIdentifier.setAuto(car);
        } else {

            existingDeviceIdentifier.setAuto(null);
        }

        deviceIdentifierService.saveDeviceIdentifier(existingDeviceIdentifier);

        String descripcion = "Usuario " + account.getEmail() + " modificó una tarjeta RFID de nombre: " + existingDeviceIdentifier.getNombreDeIdentificador();
        auditLogService.recordAction(descripcion, account);

        return new ResponseEntity<>("Identificador de dispositivo actualizado exitosamente", HttpStatus.OK);
    }

    @PatchMapping("/accounts/current/device-identifiers/{id}/delete")
    public ResponseEntity<Object> deleteDeviceIdentifier(Authentication authentication,
                                                         @PathVariable Long id) {
        Optional<Account> accountOptional = accountService.findByEmail(authentication.getName());
        if (accountOptional.isEmpty()) {
            return new ResponseEntity<>("La cuenta no se encontró", HttpStatus.NOT_FOUND);
        }
        Account account = accountOptional.get();

        DeviceIdentifier deviceIdentifier = deviceIdentifierService.findById(id);
        if (deviceIdentifier == null) {
            return new ResponseEntity<>("El identificador del dispositivo no se encontró", HttpStatus.NOT_FOUND);
        }

        if (!deviceIdentifier.getActivo()) {
            return new ResponseEntity<>("El identificador del dispositivo ya está desactivado", HttpStatus.BAD_REQUEST);
        }

        deviceIdentifier.setActivo(false);
        deviceIdentifierService.saveDeviceIdentifier(deviceIdentifier);

        String descripcion = "Usuario " + account.getEmail() + " eliminó una tarjeta RFID de nombre: " + deviceIdentifier.getNombreDeIdentificador();
        auditLogService.recordAction(descripcion, account);

        return ResponseEntity.ok("Identificador del dispositivo desactivado correctamente.");
    }

    @PatchMapping("/accounts/current/device-identifiers/{id}/assign-car")
    public ResponseEntity<Object> assignCarToDeviceIdentifier(@PathVariable Long id,
                                                              @RequestBody Map<String, Long> payload) {
        Long carId = payload.get("carId");

        Optional<DeviceIdentifier> deviceIdentifierOptional = Optional.ofNullable(deviceIdentifierService.findById(id));
        if (deviceIdentifierOptional.isEmpty()) {
            return new ResponseEntity<>("Device Identifier not found", HttpStatus.NOT_FOUND);
        }
        Optional<Car> carOptional = Optional.ofNullable(carService.findById(carId));
        if (carOptional.isEmpty()) {
            return new ResponseEntity<>("Car not found", HttpStatus.NOT_FOUND);
        }
        Car car = carOptional.get();
        List<DeviceIdentifier> assignedIdentifiers = deviceIdentifierRepository.findByAuto(car);
        for (DeviceIdentifier identifier : assignedIdentifiers) {
            identifier.setAuto(null);
            deviceIdentifierService.saveDeviceIdentifier(identifier);
        }
        DeviceIdentifier deviceIdentifier = deviceIdentifierOptional.get();
        deviceIdentifier.setAuto(car);
        deviceIdentifierService.saveDeviceIdentifier(deviceIdentifier);

        return new ResponseEntity<>("Car assigned to Device Identifier", HttpStatus.OK);
    }
    @GetMapping("/unassigned-device-identifiers")
    public ResponseEntity<List<DeviceIdentifierDTO>> getUnassignedDeviceIdentifiers(Authentication authentication) {
        Optional<Account> accountOptional = accountService.findByEmail(authentication.getName());
        if (accountOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Account account = accountOptional.get();
        Empresa empresa = account.getEmpresa();
        if (empresa == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
        List<DeviceIdentifier> unassignedDeviceIdentifiers = deviceIdentifierRepository.findUnassignedDeviceIdentifiersByEmpresa(empresa);
        List<DeviceIdentifierDTO> deviceIdentifierDTOs = unassignedDeviceIdentifiers.stream()
                .map(DeviceIdentifierDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(deviceIdentifierDTOs);
    }

}
