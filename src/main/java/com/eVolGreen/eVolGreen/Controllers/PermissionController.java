package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.PermissionDTO;
import com.eVolGreen.eVolGreen.Models.Company;
import com.eVolGreen.eVolGreen.Models.Permission;
import com.eVolGreen.eVolGreen.Repositories.PermissionRepository;
import com.eVolGreen.eVolGreen.Services.CompanyService;
import com.eVolGreen.eVolGreen.Services.EmployeeService;
import com.eVolGreen.eVolGreen.Services.PermissionService;
import com.eVolGreen.eVolGreen.Services.RoleService;
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
    private EmployeeService employeeService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private RoleService roleService;

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
        Company company = companyService.findByEmailCompany(authentication.getName());
        if (company == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        List<Permission> permissions = permissionService.getPermissionsForCompany(company.getId());

        List<PermissionDTO> permissionDTOs = permissions.stream()
                .map(PermissionDTO::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(permissionDTOs, HttpStatus.OK);
    }

    @PostMapping("/company")
    public ResponseEntity<Object> createPermission(Authentication authentication,
                                                   @RequestBody PermissionDTO permissionDTO) {
        Company company = companyService.findByEmailCompany(authentication.getName());
        if (company == null) {
            return new ResponseEntity<>("Unauthorized. Only companies can create permissions.", HttpStatus.FORBIDDEN);
        }

        if (permissionDTO.getName() == null || permissionDTO.getName().isBlank()) {
            return new ResponseEntity<>("Permission name cannot be blank", HttpStatus.BAD_REQUEST);
        }

        if (permissionDTO.getDescription() == null || permissionDTO.getDescription().isBlank()) {
            return new ResponseEntity<>("Permission description cannot be blank", HttpStatus.BAD_REQUEST);
        }

        Permission permission = new Permission(permissionDTO.getName(), permissionDTO.getDescription());
        Permission savedPermission = permissionService.createPermissionForCompany(permission, company);

        PermissionDTO savedPermissionDTO = new PermissionDTO(savedPermission);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPermissionDTO);
    }
}
