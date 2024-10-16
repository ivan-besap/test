package com.eVolGreen.eVolGreen.Models.User;

import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Models.Account.Permission.Permission;
import jakarta.persistence.Entity;
import jakarta.persistence.*;


import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El nombre del rol es obligatorio")
    private String nombre;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    @OrderBy("id ASC") // Ordena los permisos por id ascendente
    private List<Permission> permisos;

    // Relación con Empresa
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @NotNull
    private Boolean activo = true;

    public Role() {
    }

    public Role(String nombre, List<Permission> permisos, Empresa empresa, Boolean activo) {
        this.nombre = nombre;
        this.permisos = permisos;
        this.empresa = empresa;
        this.activo = activo;
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

    public List<Permission> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<Permission> permisos) {
        this.permisos = permisos;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}