package com.eVolGreen.eVolGreen.Controllers.ChargingStationController;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.MantenimientoDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Mantenimiento;
import com.eVolGreen.eVolGreen.Repositories.AccountRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.AuditLogService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargerService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.MantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MantenimientoController {

    @Autowired
    private MantenimientoService mantenimientoService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AuditLogService auditLogService;

    @Autowired
    private ChargerService chargerService;

    @PostMapping("/create-mantenimiento")
    public ResponseEntity<String> createMantenimiento(Authentication authentication, @RequestBody @Valid MantenimientoDTO mantenimientoDTO) {

        Optional<Account> cuentaOpt = accountRepository.findByEmail(authentication.getName());
        if (cuentaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa no encontrada");
        }

        Account cuentaUsuario = cuentaOpt.get();

        Mantenimiento mantenimiento = new Mantenimiento(
                mantenimientoDTO.getDescripcion(),
                mantenimientoDTO.getFechaInicial(),
                mantenimientoDTO.getFechaFinal(),
                mantenimientoDTO.getHorarioInicio(),
                mantenimientoDTO.getHorarioFin(),
                mantenimientoDTO.getDiasDeLaSemana(),
                null,
                cuentaUsuario.getEmpresa()
        );

        String descripcion = "Usuario " + cuentaUsuario.getEmail() + " creó un mantenimiento con descripción : " + mantenimientoDTO.getDescripcion();
        auditLogService.recordAction(descripcion, cuentaUsuario);

        mantenimientoService.saveMantenimiento(mantenimiento);
        return new ResponseEntity<>("Mantenimiento creado con éxito", HttpStatus.CREATED);
    }

    @GetMapping("mantenimiento/{id}")
    public ResponseEntity<MantenimientoDTO> getMantenimientoById(@PathVariable Long id) {
        Mantenimiento mantenimiento = mantenimientoService.findById(id);
        if (mantenimiento != null) {
            // Utilizamos el constructor que convierte un objeto Mantenimiento en MantenimientoDTO
            MantenimientoDTO dto = new MantenimientoDTO(mantenimiento);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("mantenimiento/{id}")
    public ResponseEntity<String> updateMantenimiento(Authentication authentication, @PathVariable Long id, @RequestBody @Valid MantenimientoDTO mantenimientoDTO) {
        mantenimientoService.updateMantenimiento(id, mantenimientoDTO);
        Optional<Account> cuentaOpt = accountRepository.findByEmail(authentication.getName());
        if (cuentaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa no encontrada");
        }

        Account cuentaUsuario = cuentaOpt.get();
        String descripcion = "Usuario " + cuentaUsuario.getEmail() + " actualizó un mantenimiento con descripción : " + mantenimientoDTO.getDescripcion();
        auditLogService.recordAction(descripcion, cuentaUsuario);
        return new ResponseEntity<>("Mantenimiento actualizado con éxito", HttpStatus.OK);
    }

    @PatchMapping("mantenimiento/{id}")
    public ResponseEntity<String> deleteMantenimiento(Authentication authentication, @PathVariable Long id) {
        Optional<Account> cuentaOpt = accountRepository.findByEmail(authentication.getName());
        if (cuentaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa no encontrada");
        }

        Mantenimiento mantenimiento = mantenimientoService.findById(id);
        Account cuentaUsuario = cuentaOpt.get();

        String descripcion = "Usuario " + cuentaUsuario.getEmail() + " borró un mantenimiento de descripción : " + mantenimiento.getDescripcion();
        auditLogService.recordAction(descripcion, cuentaUsuario);

        mantenimientoService.deleteMantenimiento(id);

        return new ResponseEntity<>("Mantenimiento eliminado con éxito", HttpStatus.OK);
    }

    @GetMapping("/mantenimientos")
    public ResponseEntity<List<MantenimientoDTO>> getMantenimientosByEmpresa(Authentication authentication) {
        Optional<Account> cuentaOpt = accountRepository.findByEmail(authentication.getName());
        if (cuentaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Account cuentaUsuario = cuentaOpt.get();
        List<Mantenimiento> mantenimientos = mantenimientoService.findByEmpresa(cuentaUsuario.getEmpresa().getId());
        List<MantenimientoDTO> mantenimientoDTOs = mantenimientos.stream()
                .map(MantenimientoDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(mantenimientoDTOs);
    }

    @PatchMapping("/asignar-mantenimiento")
    public ResponseEntity<String> asignarMantenimiento(Authentication authentication, @RequestParam Long cargadorId, @RequestParam Long mantenimientoId) {

        Optional<Account> cuentaOpt = accountRepository.findByEmail(authentication.getName());
        if (cuentaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa no encontrada");
        }
        Account cuentaUsuario = cuentaOpt.get();

        boolean resultado = mantenimientoService.asignarMantenimientoACargador(cargadorId, mantenimientoId);
        if (resultado) {
            Mantenimiento mantenimiento = mantenimientoService.findById(mantenimientoId);
            Charger cargador = chargerService.findById(cargadorId);
            String descripcion = "Usuario " + cuentaUsuario.getEmail() + " asignó el mantenimiento : " + mantenimiento.getDescripcion() + " al cargador: " + cargador.getNombre();
            auditLogService.recordAction(descripcion, cuentaUsuario);
            return new ResponseEntity<>("Mantenimiento asignado con éxito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error al asignar el mantenimiento", HttpStatus.BAD_REQUEST);
        }
    }
}

