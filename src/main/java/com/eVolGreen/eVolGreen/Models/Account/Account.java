package com.eVolGreen.eVolGreen.Models.Account;

import com.eVolGreen.eVolGreen.Models.Account.Car.DeviceIdentifier;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.TypeAccounts;
import com.eVolGreen.eVolGreen.Models.User.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account{

    @Id
    @GenericGenerator(name= "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long id;

    private String nombre;

    private String apellidoPaterno;

    private String apellidoMaterno;

    @NotNull(message = "El número de cuenta no puede ser nulo.")
    private String numeroDeCuenta;

    @NotBlank(message = "El nombre de la cuenta no puede estar vacío")
    private String nombreCuenta;

    @NotNull(message = "La fecha de creación no puede ser nula.")
    private LocalDate fechaDeCreacion;

    @NotNull(message = "El email no puede ser nulo.")
    private String email;

    @NotNull(message = "El password no puede ser nulo.")
    private String password;

    @NotNull(message = "El estado de la cuenta no puede ser nulo.")
    private Boolean activo = false;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El tipo de cuenta no puede ser nulo.")
    private TypeAccounts tipoCuenta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    private String telefono;

    private String rut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    @JsonBackReference
    private Empresa empresa;


    public Account() {}

    public Account(String nombre, String apellidoPaterno, String apellidoMaterno ,String numeroDeCuenta, String nombreCuenta, LocalDate fechaDeCreacion, String email, String password, TypeAccounts tipoCuenta, Role role, String telefono, String rut, Empresa empresa,Boolean activo) {

        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.numeroDeCuenta = numeroDeCuenta;
        this.nombreCuenta = nombreCuenta;
        this.fechaDeCreacion = fechaDeCreacion;
        this.email = email;
        this.password = password;
        this.tipoCuenta = tipoCuenta;
        this.role = role;
        this.telefono = telefono;
        this.rut = rut;
        this.empresa = empresa;
        this.activo = activo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getNumeroDeCuenta() {
        return numeroDeCuenta;
    }

    public void setNumeroDeCuenta(String numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    public LocalDate getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDate fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public TypeAccounts getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TypeAccounts tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Role getRol() {
        return role;
    }

    public void setRol(Role rol) {
        this.role = rol;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", numeroDeCuenta='" + numeroDeCuenta + '\'' +
                ", nombreCuenta='" + nombreCuenta + '\'' +
                ", fechaDeCreacion=" + fechaDeCreacion +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", tipoCuenta=" + tipoCuenta +
                ", role=" + role +
                ", telefono='" + telefono + '\'' +
                ", rut='" + rut + '\'' +
                ", empresa=" + empresa +
                ", activo=" + activo +
                '}';
    }

}
