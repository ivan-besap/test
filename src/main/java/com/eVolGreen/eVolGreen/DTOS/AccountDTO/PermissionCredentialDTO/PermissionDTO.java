package com.eVolGreen.eVolGreen.DTOS.AccountDTO.PermissionCredentialDTO;

import com.eVolGreen.eVolGreen.Models.Account.Permission.Permission;

import jakarta.validation.constraints.NotNull;

public class PermissionDTO {

    private Long id;

    @NotNull(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "La descripción es obligatoria")
    private String descripcion;

    public PermissionDTO() {}

    public PermissionDTO(Permission permiso) {
        this.id = permiso.getId();
        this.nombre = permiso.getNombre();
        this.descripcion = permiso.getDescripcion();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "PermissionDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}