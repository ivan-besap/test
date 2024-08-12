package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.RoleDTO;
import com.eVolGreen.eVolGreen.DTOS.RoleRequestDTO;
import com.eVolGreen.eVolGreen.Models.Company;
import com.eVolGreen.eVolGreen.Models.Permission;
import com.eVolGreen.eVolGreen.Models.Role;
import com.eVolGreen.eVolGreen.Services.CompanyService;
import com.eVolGreen.eVolGreen.Services.RoleService;
import com.eVolGreen.eVolGreen.Repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private PermissionRepository permissionRepository;

    @PostMapping("/roles")
    public ResponseEntity<Object> createRole(Authentication authentication,
                                             @RequestBody RoleRequestDTO roleRequest) {

        Company company = companyService.findByEmailCompany(authentication.getName());
        if (company == null) {
            return buildErrorResponse("POST /roles: No se encontró la empresa.", HttpStatus.NOT_FOUND);
        }

        String validationError = roleService.validateRoleDTO(roleRequest.getRoleDTO());
        if (validationError != null) {
            return buildErrorResponse("POST /roles: " + validationError, HttpStatus.BAD_REQUEST);
        }

        Set<Permission> permissions = Set.copyOf(permissionRepository.findAllById(roleRequest.getPermissionIds()));
        Role role = roleService.convertToEntity(roleRequest.getRoleDTO());
        role.setPermissions(permissions);
        roleService.saveRole(role);

        return new ResponseEntity<>("POST /roles: Rol creado correctamente.", HttpStatus.CREATED);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleDTO>> getRoles() {
        List<RoleDTO> roles = roleService.getRolesDTO();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Object> getRoleById(@PathVariable Long id) {
        RoleDTO roleDTO = roleService.getRoleDTO(id);
        if (roleDTO == null) {
            return buildErrorResponse("GET /roles/{id}: No se encontró el rol con el id proporcionado.", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(roleDTO);
    }

    @PatchMapping("/roles/activate/{id}")
    public ResponseEntity<Object> activateRole(@PathVariable Long id) {
        try {
            roleService.activateRole(id);
            return new ResponseEntity<>("PATCH /roles/activate/{id}: Rol activado correctamente.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return buildErrorResponse("PATCH /roles/activate/{id}: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/roles/delete/{id}")
    public ResponseEntity<Object> deleteRole(@PathVariable Long id) {
        try {
            roleService.deleteRole(id);
            return new ResponseEntity<>("DELETE /roles/delete/{id}: Rol eliminado correctamente.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return buildErrorResponse("DELETE /roles/delete/{id}: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/roles/update/{id}")
    public ResponseEntity<Object> updateRole(@PathVariable Long id, @RequestBody RoleRequestDTO roleRequest) {
        String validationError = roleService.validateRoleDTO(roleRequest.getRoleDTO());
        if (validationError != null) {
            return buildErrorResponse("PUT /roles/update/{id}: " + validationError, HttpStatus.BAD_REQUEST);
        }

        Set<Permission> permissions = Set.copyOf(permissionRepository.findAllById(roleRequest.getPermissionIds()));

        try {
            Role updatedRole = roleService.updateRole(id, roleRequest.getRoleDTO(), permissions);
            RoleDTO updatedRoleDTO = new RoleDTO(updatedRole);
            return new ResponseEntity<>(updatedRoleDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            return buildErrorResponse("PUT /roles/update/{id}: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }




    @PutMapping("/roles/addPermissions/{roleId}")
    public ResponseEntity<Object> addPermissions(@PathVariable Long roleId, @RequestBody List<Long> permissionIds) {
        try {
            roleService.addPermissions(roleId, permissionIds);
            return new ResponseEntity<>("PUT /roles/addPermissions/{roleId}: Permisos agregados correctamente.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return buildErrorResponse("PUT /roles/addPermissions/{roleId}: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/roles/removePermissions/{roleId}")
    public ResponseEntity<Object> removePermissions(@PathVariable Long roleId, @RequestBody List<Long> permissionIds) {
        try {
            roleService.removePermissions(roleId, permissionIds);
            return new ResponseEntity<>("PUT /roles/removePermissions/{roleId}: Permisos eliminados correctamente.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return buildErrorResponse("PUT /roles/removePermissions/{roleId}: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity<Object> buildErrorResponse(String message, HttpStatus status) {
        return new ResponseEntity<>(message, status);
    }
}
