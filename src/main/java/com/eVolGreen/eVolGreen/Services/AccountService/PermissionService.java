package com.eVolGreen.eVolGreen.Services.AccountService;

import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Permission;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;

import java.util.List;

public interface PermissionService {

    List<Permission> getPermissionsForCompany(Long companyId);

    Permission createPermissionForCompany(Permission permission, AccountCompany company);
}
