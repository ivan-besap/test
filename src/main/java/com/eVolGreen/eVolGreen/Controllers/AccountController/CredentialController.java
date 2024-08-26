package com.eVolGreen.eVolGreen.Controllers.AccountController;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.PermissionCredentialDTO.CredentialDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.PermissionCredentialDTO.NewPermisionCredentialDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.PermissionCredentialDTO.PermissionCredentialDTO;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Credential;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Permission;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.PermissionCredential;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import com.eVolGreen.eVolGreen.Repositories.PermissionCredentialRepository;
import com.eVolGreen.eVolGreen.Repositories.PermissionRepository;
import com.eVolGreen.eVolGreen.Services.DUserService.CompanyUserService;
import com.eVolGreen.eVolGreen.Services.AccountService.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CredentialController {

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private CompanyUserService companyUserService;

    @Autowired
    private PermissionCredentialRepository permissionCredentialRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @GetMapping("/credential")
    public ResponseEntity<List<CredentialDTO>> getCredentials() {
        List<CredentialDTO> roles = credentialService.getCredentialsDTO();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/credential/{id}")
    public ResponseEntity<Object> getCredentialById(@PathVariable Long id) {
        CredentialDTO credentialDTO = credentialService.getCredentialDTO(id);
        if (credentialDTO == null) {
            return new ResponseEntity<>(" No se encontró el rol con el id proporcionado.", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(credentialDTO);
    }

    @PostMapping("/companies/current/credential")
    public ResponseEntity<Object> createCredential(Authentication authentication,
                                                   @RequestBody NewPermisionCredentialDTO credentialRequest) {

        CompanyUser company = companyUserService.findByEmailCompanyUser(authentication.getName());
        String mensaje = " ";

        if (company == null) {
            mensaje = "No se encontró la empresa.";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }

        if (credentialRequest.getNombreCredencial() == null) {
            mensaje = "Se requiere el nombre de Rol";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (credentialRequest.getPermisos() == null) {
            mensaje = "Se requiere uno o más permisos";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }

        Optional<AccountCompany> optionalAccountCompany = company.getCuentaCompañia().stream().findFirst();
        if (optionalAccountCompany.isEmpty()) {
            return new ResponseEntity<>("No se encontró la cuenta de la compañía", HttpStatus.NOT_FOUND);
        }

        AccountCompany accountCompany = optionalAccountCompany.get();

        // Crear la credencial
        Credential nuevaCredential = new Credential(
                credentialRequest.getNombreCredencial(),
                true
        );
        nuevaCredential.setCuentaCompañia(accountCompany);
        credentialService.saveCredential(nuevaCredential);

        // Crear el PermissionCredential asociado
        PermissionCredential nuevoPermisoCredencial = new PermissionCredential(
                nuevaCredential,
                LocalDateTime.now(),
                LocalDateTime.now().plusYears(1),
                true,
                company.getEmail().toString(),
                "Rol y permisos creados por " + company.getEmail()
        );

        permissionCredentialRepository.save(nuevoPermisoCredencial); // Guardar primero el PermissionCredential

        // Crear la lista de permisos clonados
        List<Permission> permisos = permissionRepository.findAllById(credentialRequest.getPermisos());
        Set<Permission> permisosClonados = permisos.stream().map(permission -> {
            Permission permisoClonado = new Permission();
            permisoClonado.setNombre(permission.getNombre());
            permisoClonado.setDescripcion(permission.getDescripcion());
            permisoClonado.setCuentaCompañia(accountCompany);
            permisoClonado.setPermisonCredencial(nuevoPermisoCredencial);
            return permisoClonado;
        }).collect(Collectors.toSet());

        // Guardar los permisos clonados y asociarlos al PermissionCredential
        permissionRepository.saveAll(permisosClonados);

        // Asociar el conjunto de permisos clonados a la credencial
        nuevoPermisoCredencial.setPermiso(permisosClonados);
        permissionCredentialRepository.save(nuevoPermisoCredencial);

        // Asociar el PermissionCredential a la credencial
        nuevaCredential.setPermisonCredencial(Set.of(nuevoPermisoCredencial));


        mensaje = "Credencial creada correctamente.";
        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
    }

    @PutMapping("/companies/current/credential/{id}")
    public ResponseEntity<Object> updateCredential(Authentication authentication,
                                                   @PathVariable Long id,
                                                   @RequestBody NewPermisionCredentialDTO credentialRequest) {

        CompanyUser company = companyUserService.findByEmailCompanyUser(authentication.getName());
        String mensaje = " ";

        if (company == null) {
            mensaje = "No se encontró la empresa.";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }

        // Buscar la credencial existente
        Credential existingCredential = credentialService.findById(id);
        if (existingCredential == null) {
            mensaje = "No se encontró la credencial.";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        if (credentialRequest.getNombreCredencial() == null) {
            mensaje = "Se requiere el nombre de Rol";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (credentialRequest.getPermisos() == null) {
            mensaje = "Se requiere uno o más permisos";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }

        // Actualizar el nombre de la credencial
        existingCredential.setNombre(credentialRequest.getNombreCredencial());

        // Obtener la cuenta de la compañía asociada
        Optional<AccountCompany> optionalAccountCompany = company.getCuentaCompañia().stream().findFirst();
        if (optionalAccountCompany.isEmpty()) {
            return new ResponseEntity<>("No se encontró la cuenta de la compañía", HttpStatus.NOT_FOUND);
        }

        AccountCompany accountCompany = optionalAccountCompany.get();

        // Actualizar los permisos asociados
        Set<Permission> existingPermissions = existingCredential.getPermisonCredencial()
                .stream()
                .flatMap(permCred -> permCred.getPermiso().stream())
                .collect(Collectors.toSet());

        existingPermissions.clear(); // Limpiar los permisos existentes

        // Crear nuevos permisos clonados
        List<Permission> permisos = permissionRepository.findAllById(credentialRequest.getPermisos());
        Set<Permission> permisosClonados = permisos.stream().map(permission -> {
            Permission permisoClonado = new Permission();
            permisoClonado.setNombre(permission.getNombre());
            permisoClonado.setDescripcion(permission.getDescripcion());
            permisoClonado.setCuentaCompañia(accountCompany);
            permisoClonado.setPermisonCredencial(existingCredential.getPermisonCredencial().iterator().next());
            return permisoClonado;
        }).collect(Collectors.toSet());

        // Guardar los permisos clonados y asociarlos al PermissionCredential existente
        permissionRepository.saveAll(permisosClonados);
        existingCredential.getPermisonCredencial().iterator().next().setPermiso(permisosClonados);

        // Guardar los cambios en la credencial y los permisos
        credentialService.saveCredential(existingCredential);

        mensaje = "Credencial actualizada correctamente.";
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @PatchMapping("/companies/current/credential/{id}/delete")
    public ResponseEntity<Object> deleteCredential(Authentication authentication, @PathVariable Long id) {
        // Obtener el usuario autenticado
        CompanyUser companyUser = companyUserService.findByEmailCompanyUser(authentication.getName());
        String mensaje = " ";

        if (companyUser == null) {
            mensaje = "La compañía no se encontró";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }

        // Buscar la credencial por su ID
        Credential credential = credentialService.findById(id);
        if (credential == null) {
            mensaje = "Credencial no encontrada";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }

        // Verificar si la credencial ya está desactivada
        if (!credential.isActivo()) {
            mensaje = "La credencial ya está desactivada";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
        }

        // Desactivar la credencial
        credential.setActivo(false);
        credentialService.saveCredential(credential);

        mensaje = "Credencial desactivada correctamente";
        return ResponseEntity.ok(mensaje);
    }

}
