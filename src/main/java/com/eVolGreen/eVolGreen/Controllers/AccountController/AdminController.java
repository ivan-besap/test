package com.eVolGreen.eVolGreen.Controllers.AccountController;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.AccountDTO;
import com.eVolGreen.eVolGreen.DTOS.RegisterDTO.AdminCompanyRegisterDTO;
import com.eVolGreen.eVolGreen.DTOS.UserDTO.AdminCompanyDTO;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.AdminCompanyUser;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountService;
import com.eVolGreen.eVolGreen.Services.DUserService.AdminCompanyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AdminController {

    @Autowired
    private AdminCompanyUserService adminCompanyUserService;

    @Autowired
    private AccountService accountService;

    // Obtener un AdminCompanyUser por ID
    @GetMapping("/adminCompany/{id}")
    public ResponseEntity<AdminCompanyDTO> getAdminCompanyById(@PathVariable Long id) {
        Optional<AdminCompanyUser> optionalAdminCompanyUser = adminCompanyUserService.findById(id);
        if (optionalAdminCompanyUser.isPresent()) {
            return new ResponseEntity<>(new AdminCompanyDTO(optionalAdminCompanyUser.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Obtener todas las cuentas asociadas a un AdminCompanyUser por ID
    @GetMapping("/adminCompany/{id}/accounts")
    public ResponseEntity<List<AccountDTO>> getAccountsByAdminCompanyId(@PathVariable Long id) {
        Optional<AdminCompanyUser> optionalAdminCompanyUser = adminCompanyUserService.findById(id);
        if (optionalAdminCompanyUser.isPresent()) {
            List<AccountDTO> accountDTOList = optionalAdminCompanyUser.get().getCuentaPrincipal()
                    .stream().map(AccountDTO::new).collect(Collectors.toList());
            return new ResponseEntity<>(accountDTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Actualizar un AdminCompanyUser por ID
    @PutMapping("/adminCompany/{id}")
    public ResponseEntity<Object> updateAdminCompanyById(@PathVariable Long id,
                                                         @RequestBody AdminCompanyRegisterDTO updatedCompany) {

        String mensaje = " ";
        Optional<AdminCompanyUser> optionalAdminCompanyUser = adminCompanyUserService.findById(id);
        if (!optionalAdminCompanyUser.isPresent()) {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }

        if (updatedCompany.getNombreCompañia().isBlank()) {
            mensaje = "El nombre de la empresa es requerido";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (updatedCompany.getRut() == null) {
            mensaje = "El rut es requerido";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (updatedCompany.getTelefono() == null) {
            mensaje = "El telefono es requerido";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (updatedCompany.getEmail().isBlank()) {
            mensaje = "El email es requerido";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (updatedCompany.getPassword().isBlank()) {
            mensaje = "El password es requerido";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (!updatedCompany.getPassword().equals(updatedCompany.getPasswordConfirmation())) {
            mensaje = "Las contraseñas no coinciden";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }

        AdminCompanyUser adminCompanyUser = optionalAdminCompanyUser.get();
        // Validaciones y actualizaciones similares al método existente
        adminCompanyUser.setNombreCompañia(updatedCompany.getNombreCompañia());
        adminCompanyUser.setRut(updatedCompany.getRut());
        adminCompanyUser.setEmail(updatedCompany.getEmail());
        adminCompanyUser.setPassword(updatedCompany.getPassword());
        adminCompanyUser.setTelefono(updatedCompany.getTelefono());

        adminCompanyUserService.saveAdminCompanyUser(adminCompanyUser);

        // Actualizar todas las cuentas asociadas
        adminCompanyUser.getCuentaPrincipal().forEach(account -> {
            account.setNombreCuenta("Cuenta principal de la Compañia " + updatedCompany.getNombreCompañia());
            account.setPassword(updatedCompany.getPassword());
            account.setEmail(updatedCompany.getEmail());
            accountService.saveAccount(account);
        });

        return new ResponseEntity<>("Usuario actualizado con éxito", HttpStatus.OK);
    }
    // Eliminar un AdminCompanyUser por ID
    @DeleteMapping("/adminCompany/{id}")
    public ResponseEntity<Object> removeAdminCompanyById(@PathVariable Long id) {

        String mensaje = " ";
        Optional<AdminCompanyUser> optionalAdminCompanyUser = adminCompanyUserService.findById(id);
        if (!optionalAdminCompanyUser.isPresent()) {
            mensaje = "Usuario no encontrado";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        AdminCompanyUser adminCompanyUser = optionalAdminCompanyUser.get();
        if (!adminCompanyUser.getActivo()) {
            mensaje = "El usuario ya está desactivado";
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }

        // Desactivar usuario y todas las cuentas asociadas
        adminCompanyUser.setActivo(false);
        adminCompanyUser.getCuentaPrincipal().forEach(account -> {
            account.setActivo(false);
            accountService.saveAccount(account);
        });
        adminCompanyUserService.saveAdminCompanyUser(adminCompanyUser);

        return new ResponseEntity<>("Usuario eliminado con éxito", HttpStatus.OK);
    }

}
