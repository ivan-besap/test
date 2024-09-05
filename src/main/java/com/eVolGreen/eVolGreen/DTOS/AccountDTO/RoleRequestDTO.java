package com.eVolGreen.eVolGreen.DTOS.AccountDTO;

import java.util.List;

public class RoleRequestDTO {
    private String nombre;
    private List<Long> permisosIds;

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Long> getPermisosIds() {
        return permisosIds;
    }

    public void setPermisosIds(List<Long> permisosIds) {
        this.permisosIds = permisosIds;
    }
}
