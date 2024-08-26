package com.eVolGreen.eVolGreen.Controllers.AccountController;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.PermissionCredentialDTO.PermissionDTO;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Permission;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import com.eVolGreen.eVolGreen.Repositories.PermissionRepository;
import com.eVolGreen.eVolGreen.Services.DUserService.CompanyUserService;
import com.eVolGreen.eVolGreen.Services.DUserService.EmployeeUserService;
import com.eVolGreen.eVolGreen.Services.AccountService.CredentialService;
import com.eVolGreen.eVolGreen.Services.AccountService.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private EmployeeUserService employeeUserService;

    @Autowired
    private CompanyUserService companyUserService;

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/default")
    public List<PermissionDTO> getPermissions() {
        return permissionRepository.findAll()
                .stream()
                .map(PermissionDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/company")
    public ResponseEntity<List<PermissionDTO>> getPermissions(Authentication authentication) {
        CompanyUser company = companyUserService.findByEmailCompanyUser(authentication.getName());
        if (company == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        List<Permission> permissions = permissionService.getPermissionsForCompany(company.getId());

        List<PermissionDTO> permissionDTOs = permissions.stream()
                .map(PermissionDTO::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(permissionDTOs, HttpStatus.OK);
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
