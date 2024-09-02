package com.eVolGreen.eVolGreen.Controllers.AccountController;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.PermissionCredentialDTO.PermissionDTO;
import com.eVolGreen.eVolGreen.Models.Account.Permission.Permission;
import com.eVolGreen.eVolGreen.Repositories.PermissionRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public List<PermissionDTO> getPermissions() {
        return permissionRepository.findAll()
                .stream()
                .map(PermissionDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<Object> createPermission(@RequestBody PermissionDTO permissionDTO) {
        if (permissionDTO.getNombre() == null || permissionDTO.getNombre().isBlank()) {
            return new ResponseEntity<>("Permission name cannot be blank", HttpStatus.BAD_REQUEST);
        }

        if (permissionDTO.getDescripcion() == null || permissionDTO.getDescripcion().isBlank()) {
            return new ResponseEntity<>("Permission description cannot be blank", HttpStatus.BAD_REQUEST);
        }

        Permission permission = new Permission(permissionDTO.getNombre(), permissionDTO.getDescripcion());
        permissionService.savePermission(permission);

        return ResponseEntity.status(HttpStatus.CREATED).body(new PermissionDTO(permission));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePermission(@PathVariable Long id, @RequestBody PermissionDTO permissionDTO) {
        Permission existingPermission = permissionService.findById(id);
        if (existingPermission == null) {
            return new ResponseEntity<>("Permission not found", HttpStatus.NOT_FOUND);
        }

        existingPermission.setNombre(permissionDTO.getNombre());
        existingPermission.setDescripcion(permissionDTO.getDescripcion());
        permissionService.savePermission(existingPermission);

        return ResponseEntity.ok(new PermissionDTO(existingPermission));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePermission(@PathVariable Long id) {
        Permission existingPermission = permissionService.findById(id);
        if (existingPermission == null) {
            return new ResponseEntity<>("Permission not found", HttpStatus.NOT_FOUND);
        }

        permissionService.deletePermission(id);
        return ResponseEntity.ok("Permission deleted successfully");
    }
//    @PostMapping("/company")
//    public ResponseEntity<Object> createPermission(Authentication authentication,
//                                                   @RequestBody PermissionDTO permissionDTO) {
//        CompanyUser company = companyUserService.findByEmailCompanyUser(authentication.getName());
//        if (company == null) {
//            return new ResponseEntity<>("Unauthorized. Only companies can create permissions.", HttpStatus.FORBIDDEN);
//        }
//
//        if (permissionDTO.getNombre() == null || permissionDTO.getNombre().isBlank()) {
//            return new ResponseEntity<>("Permission name cannot be blank", HttpStatus.BAD_REQUEST);
//        }
//
//        if (permissionDTO.getDescripcion() == null || permissionDTO.getDescripcion().isBlank()) {
//            return new ResponseEntity<>("Permission description cannot be blank", HttpStatus.BAD_REQUEST);
//        }
//
//        Permission permission = new Permission(permissionDTO.getNombre(), permissionDTO.getDescripcion());
////        Permission savedPermission = permissionService.createPermissionForCompany(permission, company);
//
////        PermissionDTO savedPermissionDTO = new PermissionDTO(savedPermission);
////        return ResponseEntity.status(HttpStatus.CREATED).body(savedPermissionDTO);
//    }
}
