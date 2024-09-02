package com.eVolGreen.eVolGreen.Services.AccountService;

import com.eVolGreen.eVolGreen.Models.User.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByNombre(String name);
    void saveRole(Role role);
}
