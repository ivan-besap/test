package com.eVolGreen.eVolGreen.DTOS.AccountDTO.PermissionCredentialDTO;

import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Credential;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.PermissionCredential;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CredentialDTO {

    private long id;

    private String Nombre;

    private boolean Activo = false;

    private Long CuentaTrabajador;

    private List<Long> PermisonCredencial;


    public CredentialDTO(Credential credential) {

        id = credential.getId();
        Nombre = credential.getNombre();
        Activo = credential.isActivo();
        CuentaTrabajador = credential.getCuentaTrabajador().getId();
        PermisonCredencial = credential.getPermisonCredencial().stream().map(PermissionCredentialDTO -> new PermissionCredentialDTO(PermissionCredentialDTO).getId()).collect(Collectors.toList());

    }

    public long getId() {
        return id;
    }

    public @NotNull(message = "El Nombre es obligatorio") String getNombre() {
        return Nombre;
    }

    public boolean isActivo() {
        return Activo;
    }

    public Long getCuentaTrabajador() {
        return CuentaTrabajador;
    }

    public List<Long> getPermisonCredencial() {
        return PermisonCredencial;
    }

    @Override
    public String toString() {
        return "CredentialDTO{" +
                "id=" + id +
                ", Nombre='" + Nombre + '\'' +
                ", Activo=" + Activo +
                ", CuentaTrabajador=" + CuentaTrabajador +
                ", PermisonCredencial=" + PermisonCredencial +
                '}';
    }
}
