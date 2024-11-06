package com.eVolGreen.eVolGreen.Controllers.ChargingStationController;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.ChargerManufacturerDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.ChargerModelDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.ChargerManufacturer;
import com.eVolGreen.eVolGreen.Repositories.AccountRepository;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargerManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ChargerManufacturerController {

    @Autowired
    private ChargerManufacturerService chargerManufacturerService;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/manufacturers")
    public ResponseEntity<List<ChargerManufacturerDTO>> getChargerManufacturerByEmpresa(Authentication authentication) {

        Optional<Account> cuentaOpt = accountRepository.findByEmail(authentication.getName());
        if (cuentaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Account cuenta = cuentaOpt.get();
        List<ChargerManufacturerDTO> manufacturers = chargerManufacturerService.getChargerManufacturerByEmpresa(cuenta.getEmpresa().getId());
        return ResponseEntity.ok(manufacturers);
    }

    @GetMapping("/manufacturers/{id}")
    public ResponseEntity<ChargerManufacturerDTO> getChargerManufacturerById(@PathVariable Long id) {
        ChargerManufacturer chargerManufacturer = chargerManufacturerService.findById(id);

        if (chargerManufacturer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Crear DTO utilizando el constructor con id y name
        ChargerManufacturerDTO chargerManufacturerDTO = new ChargerManufacturerDTO(
                chargerManufacturer.getId(),
                chargerManufacturer.getName()
        );

        return new ResponseEntity<>(chargerManufacturerDTO, HttpStatus.OK);
    }

    @PutMapping("/manufacturers/{id}")
    public ResponseEntity<String> updateChargerManufacturer(@PathVariable Long id, @RequestBody @Valid ChargerManufacturerDTO chargerManufacturerDTO) {
        ChargerManufacturer chargerManufacturer = chargerManufacturerService.findById(id);

        if (chargerManufacturer == null) {
            return new ResponseEntity<>("Fabricante no encontrado", HttpStatus.NOT_FOUND);
        }

        chargerManufacturer.setName(chargerManufacturerDTO.getName());
        chargerManufacturerService.saveChargerManufacturer(chargerManufacturer);

        return new ResponseEntity<>("Fabricante de cargador actualizado con éxito", HttpStatus.OK);
    }

    @PatchMapping("/manufacturers/{id}")
    public ResponseEntity<String> deleteChargerManufacturer(@PathVariable Long id) {
        ChargerManufacturer chargerManufacturer = chargerManufacturerService.findById(id);

        if (chargerManufacturer == null) {
            return new ResponseEntity<>("Fabricante no encontrado", HttpStatus.NOT_FOUND);
        }

        chargerManufacturerService.deleteById(id);
        return new ResponseEntity<>("Fabricante de cargador eliminado con éxito", HttpStatus.OK);
    }
}
