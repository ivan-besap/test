package com.eVolGreen.eVolGreen.DTOS.AccountDTO.PermissionCredentialDTO;

import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Permission;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.PermissionCredential;

import java.util.List;
import java.util.stream.Collectors;

public class NewPermisionCredentialDTO {

    private String nombreCredencial;

    private List<Long> permisos;

    public NewPermisionCredentialDTO() { }

    public NewPermisionCredentialDTO(PermissionCredential permissionCredential) {
        this.nombreCredencial = permissionCredential.getCredencial().getNombre();
        this.permisos = permissionCredential.getPermiso().stream().map(Permission -> new Permission().getId()).collect(Collectors.toList());
    }

    public String getNombreCredencial() {
        return nombreCredencial;
    }

    public List<Long> getPermisos() {
        return permisos;
    }
}
