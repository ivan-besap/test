package com.eVolGreen.eVolGreen.DTOS.AccountDTO.PermissionCredentialDTO;

import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Permission;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.PermissionCredential;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountEmployee;

import jakarta.validation.constraints.NotNull;

import java.util.Set;

public class PermissionDTO {

    private Long id;

    @NotNull(message = "El nombre es obligatorio")
    private String Nombre;

    @NotNull(message = "La descripción es obligatoria")
    private String Descripcion;

    private Long PermisonCredencial;

    private Long CuentaCompañia;

    private Long CuentaTrabajador;

    public PermissionDTO (Permission Permiso) {

        id = Permiso.getId();
        Nombre = Permiso.getNombre();
        Descripcion = Permiso.getDescripcion();
        PermisonCredencial = Permiso.getPermisonCredencial().getId();
        CuentaCompañia = Permiso.getCuentaCompañia().getId();
        CuentaTrabajador = Permiso.getCuentaTrabajador().getId();

    }

    public Long getId() {
        return id;
    }

    public @NotNull(message = "El nombre es obligatorio") String getNombre() {
        return Nombre;
    }

    public @NotNull(message = "La descripción es obligatoria") String getDescripcion() {
        return Descripcion;
    }

    public Long getPermisonCredencial() {
        return PermisonCredencial;
    }

    public Long getCuentaCompañia() {
        return CuentaCompañia;
    }

    public Long getCuentaTrabajador() {
        return CuentaTrabajador;
    }

    @Override
    public String toString() {
        return "PermissionDTO{" +
                "id=" + id +
                ", Nombre='" + Nombre + '\'' +
                ", Descripcion='" + Descripcion + '\'' +
                ", PermisonCredencial=" + PermisonCredencial +
                ", CuentaCompañia=" + CuentaCompañia +
                ", CuentaTrabajador=" + CuentaTrabajador +
                '}';
    }
}
