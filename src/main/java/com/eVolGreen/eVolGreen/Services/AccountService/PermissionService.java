package com.eVolGreen.eVolGreen.Services.AccountService;

import com.eVolGreen.eVolGreen.Models.Account.Permission.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> getPermissions();
    Permission savePermission(Permission permission);
    Permission findById(Long id);
    void deletePermission(Long id);
}