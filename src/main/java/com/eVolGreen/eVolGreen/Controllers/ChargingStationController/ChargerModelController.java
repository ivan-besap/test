package com.eVolGreen.eVolGreen.Controllers.ChargingStationController;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.ChargerManufacturerDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.ChargerModelDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.NewChargerModelDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.ChargerManufacturer;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.ChargerModel;
import com.eVolGreen.eVolGreen.Repositories.AccountRepository;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargerModelService;
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
public class ChargerModelController {

    @Autowired
    private ChargerModelService chargerModelService;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/models")
    public ResponseEntity<List<ChargerModelDTO>> getModelsByEmpresa(Authentication authentication) {

        Optional<Account> cuentaOpt = accountRepository.findByEmail(authentication.getName());
        if (cuentaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Account cuenta = cuentaOpt.get();

        List<ChargerModelDTO> models = chargerModelService.getChargerModelsByEmpresa(cuenta.getEmpresa().getId());
        return ResponseEntity.ok(models);
    }
    @GetMapping("/models/{id}")
    public ResponseEntity<ChargerModelDTO> getModelById(@PathVariable Long id) {
        ChargerModel chargerModel = chargerModelService.findById(id);

        if (chargerModel == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Crear DTO utilizando el constructor con id y name
        ChargerModelDTO chargerModelDTO = new ChargerModelDTO(
                chargerModel.getId(),
                chargerModel.getName()
        );

        return new ResponseEntity<>(chargerModelDTO, HttpStatus.OK);
    }

    @PutMapping("/models/{id}")
    public ResponseEntity<String> updateChargerModel(@PathVariable Long id, @RequestBody @Valid NewChargerModelDTO newChargerModelDTO) {
        ChargerModel chargerModel = chargerModelService.findById(id);

        if (chargerModel == null) {
            return new ResponseEntity<>("Modelo no encontrado", HttpStatus.NOT_FOUND);
        }

        chargerModel.setName(newChargerModelDTO.getName());
        chargerModelService.saveChargerModel(chargerModel);

        return new ResponseEntity<>("Modelo de cargador actualizado con éxito", HttpStatus.OK);
    }

    @PatchMapping("/models/{id}")
    public ResponseEntity<String> deleteChargerModel(@PathVariable Long id) {
        ChargerModel chargerModel = chargerModelService.findById(id);

        if (chargerModel == null) {
            return new ResponseEntity<>("Modelo no encontrado", HttpStatus.NOT_FOUND);
        }

        chargerModelService.deleteById(id);
        return new ResponseEntity<>("Modelo de cargador eliminado con éxito", HttpStatus.OK);
    }
}
