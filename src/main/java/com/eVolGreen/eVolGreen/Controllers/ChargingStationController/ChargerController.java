package com.eVolGreen.eVolGreen.Controllers.ChargingStationController;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.ChargerDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.NewChargerDTO;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargerService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargingStationsService;
import com.eVolGreen.eVolGreen.Services.DUserService.CompanyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ChargerController {

    @Autowired
    private ChargerService chargerService;

    @Autowired
    private ChargingStationsService chargingStationsService;

    @Autowired
    private CompanyUserService companyUserService;

    @GetMapping("/chargers")
    public List<ChargerDTO> getChargers() {
        return chargerService.getChargersDTO();
    }

    @GetMapping("/chargers/{id}")
    public ChargerDTO getCharger(@PathVariable Long id) {
        return chargerService.getChargerDTO(id);
    }

    @PostMapping("/companies/current/chargers")
    public ResponseEntity<Object> createCharger(Authentication authentication,
                                                @RequestBody NewChargerDTO chargerDTO) {

        CompanyUser company = companyUserService.findByEmailCompanyUser(authentication.getName());
        String mensaje = " ";

        if (company == null) {
            mensaje = "No se encontró la empresa";
            return ResponseEntity.status(400).body(mensaje);
        }

        if (chargerDTO.getoCPPid() == null) {
            mensaje = "El Id del cargador es obligatorio";
            return ResponseEntity.status(400).body(mensaje);
        }
        if (chargerDTO.getNombre() == null) {
            mensaje = "El nombre del cargador no puede estar vacío";
            return ResponseEntity.status(400).body(mensaje);
        }
        if (chargerDTO.getAlias() == null) {
            mensaje = "El alias del cargador no puede estar vacío";
            return ResponseEntity.status(400).body(mensaje);
        }
        if (chargerDTO.getFabricante() == null) {
            mensaje = "El fabricante del cargador no puede estar vacío";
            return ResponseEntity.status(400).body(mensaje);
        }
        if (chargerDTO.getModelo() == null) {
            mensaje = "El modelo del cargador no puede estar vacío";
            return ResponseEntity.status(400).body(mensaje);
        }
        if (chargerDTO.getTerminal() == null) {
            mensaje = "La terminal del cargador no puede estar vacío";
            return ResponseEntity.status(400).body(mensaje);
        }

        ChargingStation chargingStation = chargingStationsService.findById(chargerDTO.getTerminal());

        Charger charger = new Charger(
                chargerDTO.getoCPPid(),
                chargerDTO.getNombre(),
                chargerDTO.getAlias(),
                chargerDTO.getFabricante(),
                chargerDTO.getModelo(),
                false,
                LocalDate.now(),
                chargingStation

        );



        chargerService.saveCharger(charger);


        return ResponseEntity.status(201).body("Charger created successfully and associated with the charging station.");
    }

    @PutMapping("/companies/current/chargers/{id}")
    public ResponseEntity<Object> updateCharger(Authentication authentication,
                                                @PathVariable Long id,
                                                @RequestBody NewChargerDTO chargerDTO) {

        // Obtener la compañía del usuario autenticado
        CompanyUser company = companyUserService.findByEmailCompanyUser(authentication.getName());
        String mensaje = " ";

        if (company == null) {
            mensaje = "No se encontró la empresa";
            return ResponseEntity.status(400).body(mensaje);
        }

        // Buscar el cargador existente
        Charger existingCharger = chargerService.findById(id);
        if (existingCharger == null) {
            mensaje = "El cargador no existe";
            return ResponseEntity.status(404).body(mensaje);
        }

        // Validar los campos requeridos en el DTO
        if (chargerDTO.getoCPPid() == null) {
            mensaje = "El Id del cargador es obligatorio";
            return ResponseEntity.status(400).body(mensaje);
        }
        if (chargerDTO.getNombre() == null) {
            mensaje = "El nombre del cargador no puede estar vacío";
            return ResponseEntity.status(400).body(mensaje);
        }
        if (chargerDTO.getAlias() == null) {
            mensaje = "El alias del cargador no puede estar vacío";
            return ResponseEntity.status(400).body(mensaje);
        }
        if (chargerDTO.getFabricante() == null) {
            mensaje = "El fabricante del cargador no puede estar vacío";
            return ResponseEntity.status(400).body(mensaje);
        }
        if (chargerDTO.getModelo() == null) {
            mensaje = "El modelo del cargador no puede estar vacío";
            return ResponseEntity.status(400).body(mensaje);
        }
        if (chargerDTO.getTerminal() == null) {
            mensaje = "La terminal del cargador no puede estar vacío";
            return ResponseEntity.status(400).body(mensaje);
        }

        // Buscar y validar la terminal asociada
        ChargingStation chargingStation = chargingStationsService.findById(chargerDTO.getTerminal());
        if (chargingStation == null) {
            mensaje = "La terminal no existe";
            return ResponseEntity.status(400).body(mensaje);
        }

        // Actualizar los campos del cargador existente
        existingCharger.setoCPPid(chargerDTO.getoCPPid());
        existingCharger.setNombre(chargerDTO.getNombre());
        existingCharger.setAlias(chargerDTO.getAlias());
        existingCharger.setFabricante(chargerDTO.getFabricante());
        existingCharger.setModelo(chargerDTO.getModelo());
        existingCharger.setTerminal(chargingStation);

        // Guardar los cambios en la base de datos
        chargerService.saveCharger(existingCharger);

        return ResponseEntity.status(200).body("Charger updated successfully.");
    }

    @PatchMapping("/companies/current/chargers/{id}/delete")
    public ResponseEntity<Object> deleteCharger(Authentication authentication,
                                                @PathVariable Long id) {
        // Obtener el usuario autenticado
        CompanyUser companyUser = companyUserService.findByEmailCompanyUser(authentication.getName());
        String mensaje = " ";

        if (companyUser == null) {
            mensaje = "La compañía no se encontró";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }

        // Buscar el cargador por su ID
        Charger charger = chargerService.findById(id);
        if (charger == null) {
            mensaje = "Cargador no encontrado";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }

        // Verificar si el cargador ya está desactivado
        if (!charger.getActivo()) {
            mensaje = "El cargador ya está desactivado";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
        }

        // Desactivar el cargador
        charger.setActivo(false);
        chargerService.saveCharger(charger);

        mensaje = "Cargador desactivado correctamente";
        return ResponseEntity.ok(mensaje);
    }

}
