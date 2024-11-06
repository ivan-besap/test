package com.eVolGreen.eVolGreen.DTOS.AccountDTO;

import com.eVolGreen.eVolGreen.Models.Account.Empresa;

public class EmpresaDTO {

    private Long id;
    private String nombre;

    public EmpresaDTO() {}

    public EmpresaDTO(Empresa empresa) {
        if (empresa != null) {
            this.id = empresa.getId();
            this.nombre = empresa.getNombre();
        } else {
            this.id = null; // o algún valor por defecto
            this.nombre = null; // o algún valor por defecto
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}