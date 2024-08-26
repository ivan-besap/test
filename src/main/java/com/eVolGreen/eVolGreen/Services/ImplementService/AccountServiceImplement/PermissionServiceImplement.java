package com.eVolGreen.eVolGreen.Services.ImplementService.AccountServiceImplement;

import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Permission;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.eVolGreen.eVolGreen.Repositories.PermissionRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImplement implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Permission> getPermissionsForCompany(Long companyId) {
        return permissionRepository.findByCuentaCompañia_Id(companyId);
    }

    @Override
    public Permission createPermissionForCompany(Permission permission, AccountCompany company) {
        permission.setCuentaCompañia(company);
        return permissionRepository.save(permission);
    }
}
