package com.eVolGreen.eVolGreen.DTOS.AccountDTO;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.PermissionCredentialDTO.PermissionDTO;
import com.eVolGreen.eVolGreen.Models.User.Role;

import java.util.Set;
import java.util.stream.Collectors;

public class RoleDTO {
    private Long id;
    private String nombre;
    private Set<PermissionDTO> permisos;
    private EmpresaDTO empresa;

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.nombre = role.getNombre();
        this.permisos = role.getPermisos().stream()
                .map(PermissionDTO::new)
                .collect(Collectors.toSet());
        this.empresa = new EmpresaDTO(role.getEmpresa());
    }



    // Getters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Set<PermissionDTO> getPermisos() {
        return permisos;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPermisos(Set<PermissionDTO> permisos) {
        this.permisos = permisos;
    }

    public EmpresaDTO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaDTO empresa) {
        this.empresa = empresa;
    }
}