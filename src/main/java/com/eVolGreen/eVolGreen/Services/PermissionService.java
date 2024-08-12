package com.eVolGreen.eVolGreen.Services;

import com.eVolGreen.eVolGreen.Models.Company;
import com.eVolGreen.eVolGreen.Models.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> getPermissionsForCompany(Long companyId);


    Permission createPermissionForCompany(Permission permission, Company company);
}
