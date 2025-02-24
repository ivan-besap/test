package com.eVolGreen.eVolGreen.Controllers.ChargingStationController;

import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.PerfilCargaCargador;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.PerfilCargaCargadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PerfilCargaCargadorController {

    @Autowired
    private PerfilCargaCargadorService perfilCargaCargadorService;

    // Obtener perfil de carga por el ID del cargador
    @GetMapping("/profile-charger/{chargerId}")
    public ResponseEntity<PerfilCargaCargador> getProfileByCharger(@PathVariable Long chargerId) {
        Optional<PerfilCargaCargador> profile = perfilCargaCargadorService.getProfileByChargerId(chargerId);
        return profile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear o actualizar un perfil de carga
    @PostMapping("profile-charger/{chargerId}")
    public ResponseEntity<PerfilCargaCargador> saveOrUpdateProfile(@PathVariable Long chargerId, @RequestBody PerfilCargaCargador perfilCargaCargador) {
        PerfilCargaCargador savedProfile = perfilCargaCargadorService.saveOrUpdateProfile(chargerId, perfilCargaCargador);
        return ResponseEntity.ok(savedProfile);
    }
}
