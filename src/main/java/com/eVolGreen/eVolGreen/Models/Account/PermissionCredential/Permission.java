package com.eVolGreen.eVolGreen.Models.Account.PermissionCredential;

import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountEmployee;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Permission {

    @Id
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private Long id;

    @NotNull(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "La descripción es obligatoria")
    private String descripcion;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "permisonCredencial_id")
    @JsonBackReference("Permiso-PermisonCredencial")
    private PermissionCredential permisonCredencial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Compañia_id")
    @JsonBackReference("Permiso-CuentaCompañia")
    private AccountCompany cuentaCompañia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CuentaTrabajador_id")
    @JsonBackReference("CuentaTrabajador-Permisos")
    private AccountEmployee CuentaTrabajador;

    public Permission() {
    }

    public Permission(String Nombre, String descripcion) {
        this.nombre = Nombre;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "El nombre es obligatorio") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotNull(message = "El nombre es obligatorio") String nombre) {
        this.nombre = nombre;
    }

    public @NotNull(message = "La descripción es obligatoria") String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NotNull(message = "La descripción es obligatoria") String descripcion) {
        this.descripcion = descripcion;
    }

    public PermissionCredential getPermisonCredencial() {
        return permisonCredencial;
    }

    public void setPermisonCredencial(PermissionCredential permisonCredencial) {
        this.permisonCredencial = permisonCredencial;
    }

    public AccountCompany getCuentaCompañia() {
        return cuentaCompañia;
    }

    public void setCuentaCompañia(AccountCompany cuentaCompañia) {
        this.cuentaCompañia = cuentaCompañia;
    }

    public AccountEmployee getCuentaTrabajador() {
        return CuentaTrabajador;
    }

    public void setCuentaTrabajador(AccountEmployee cuentaTrabajador) {
        CuentaTrabajador = cuentaTrabajador;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", Nombre='" + nombre + '\'' +
                ", Descripcion='" + descripcion + '\'' +
                ", PermisonCredencial=" + permisonCredencial +
                ", CuentaCompañia=" + cuentaCompañia +
                ", CuentaTrabajador=" + CuentaTrabajador +
                '}';
    }
}
