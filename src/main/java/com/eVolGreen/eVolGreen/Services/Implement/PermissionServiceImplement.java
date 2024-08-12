package com.eVolGreen.eVolGreen.Services.Implement;

import com.eVolGreen.eVolGreen.Models.Company;
import com.eVolGreen.eVolGreen.Models.Permission;
import com.eVolGreen.eVolGreen.Repositories.PermissionRepository;
import com.eVolGreen.eVolGreen.Services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImplement implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Permission> getPermissionsForCompany(Long companyId) {
        return permissionRepository.findByCompanyId(companyId);
    }

    @Override
    public Permission createPermissionForCompany(Permission permission, Company company) {
        permission.setCompany(company);
        return permissionRepository.save(permission);
    }
}
