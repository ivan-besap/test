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
public class Credential {

    @Id
    @GenericGenerator(name= "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long id;

    @NotNull(message = "El Nombre es obligatorio")
    private String nombre;

    private boolean activo = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Compañia_id")
    @JsonBackReference("Credenciales-CuentaCompañia")
    private AccountCompany cuentaCompañia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Trabajador_id")
    @JsonBackReference("CuentaTrabajador-Credenciales")
    private AccountEmployee cuentaTrabajador;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "Crendenciales_Permisos",
//            joinColumns = @JoinColumn(name = "Credenciales_id"),
//            inverseJoinColumns = @JoinColumn(name = "Permisos_id")
//    )
//    @JsonBackReference("Permisos-Credenciales")
//    private Set<Permission> Permisos = new HashSet<>();

    @OneToMany(mappedBy = "credencial", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("Credencial-PermisonCredencial")
    private Set<PermissionCredential> permisonCredencial = new HashSet<>();

    public Credential() { }

    public Credential(String nombre, boolean activo) {
        this.nombre = nombre;
        this.activo = activo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotNull(message = "El Nombre es obligatorio") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotNull(message = "El Nombre es obligatorio") String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public AccountCompany getCuentaCompañia() {
        return cuentaCompañia;
    }

    public void setCuentaCompañia(AccountCompany cuentaCompañia) {
        this.cuentaCompañia = cuentaCompañia;
    }

    public AccountEmployee getCuentaTrabajador() {
        return cuentaTrabajador;
    }

    public void setCuentaTrabajador(AccountEmployee cuentaTrabajador) {
        this.cuentaTrabajador = cuentaTrabajador;
    }

    public Set<PermissionCredential> getPermisonCredencial() {
        return permisonCredencial;
    }

    public void setPermisonCredencial(Set<PermissionCredential> permisonCredencial) {
        this.permisonCredencial = permisonCredencial;
    }

    @Override
    public String toString() {
        return "Credential{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", activo=" + activo +
                ", cuentaCompañia=" + cuentaCompañia +
                ", cuentaTrabajador=" + cuentaTrabajador +
                ", permisonCredencial=" + permisonCredencial +
                '}';
    }
}
