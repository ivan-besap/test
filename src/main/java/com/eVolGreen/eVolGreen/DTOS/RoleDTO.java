package com.eVolGreen.eVolGreen.DTOS;

import com.eVolGreen.eVolGreen.Models.Permission;
import com.eVolGreen.eVolGreen.Models.Role;

import java.util.Set;
import java.util.stream.Collectors;

public class RoleDTO {

    private Long id;
    private String name;
    private Set<String> permissions;
    private boolean isActive;

    public RoleDTO() { }

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.name = role.getName();
        this.permissions = role.getPermissions().stream()
                .map(permission -> permission.getName())
                .collect(Collectors.toSet());
        this.isActive = role.getIsActive();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public boolean isActive() {
        return isActive;
    }
}
