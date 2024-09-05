package com.eVolGreen.eVolGreen.Services.AccountService;

import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Models.User.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> findByNombre(String name);
    void saveRole(Role role);
    Optional<Role> findById(Long id);
    List<Role> findRolesByEmpresa(Empresa empresa);
}