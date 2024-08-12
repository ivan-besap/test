package com.eVolGreen.eVolGreen.DTOS;

import java.util.List;

public class RoleRequestDTO {

    private RoleDTO roleDTO;
    private List<Long> permissionIds;

    public RoleDTO getRoleDTO() {
        return roleDTO;
    }

    public void setRoleDTO(RoleDTO roleDTO) {
        this.roleDTO = roleDTO;
    }

    public List<Long> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Long> permissionIds) {
        this.permissionIds = permissionIds;
    }
}
