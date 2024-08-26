package com.eVolGreen.eVolGreen.DTOS.AccountDTO.TypeOfAccountDTO;

import com.eVolGreen.eVolGreen.Models.Account.Car.Car;
import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;
import com.eVolGreen.eVolGreen.Models.Account.Location;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Credential;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Permission;
import com.eVolGreen.eVolGreen.Models.Account.Transaction.Transaction;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountEmployee;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.TypeAccounts;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.eVolGreen.eVolGreen.Models.User.Role;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public class AccountEmployeeDTO {

    private long id;

    @NotNull(message = "El número de cuenta no puede ser nulo.")
    private String NumeroDeCuenta;

    @NotNull(message = "El nombre no puede estar vacío")
    private String Nombre;

    @NotBlank(message = "El apellido paterno no puede estar vacío")
    private String ApellidoPaterno;

    @NotBlank(message = "El número de teléfono no puede estar vacío")
    private Integer Telefono;

    @NotBlank(message = "El RUT no puede estar vacío")
    private String Rut;

    @NotNull(message = "El Email es obligatorio")
    private String Email;

    @NotNull(message = "La Contraseña es obligatoria")
    private String Password;

    private Set<Car> Autos;

    private Set<Transaction> Transancciones;

    private Set<ChargingStation> Terminales;

    private Set<Permission> Permisos;

    private Set<Credential> Credenciales;

    private Set<Fee> Tarifas;

    private long Trabajador;

    private long CuentaCompañia;

    @Enumerated(EnumType.STRING)
    private Role Rol = Role.EMPLOYEE;

    @NotNull(message = "El tipo de cuenta no puede ser vacío")
    @Enumerated(EnumType.STRING)
    private TypeAccounts TipoCuenta = TypeAccounts.Employee;

    @NotNull(message = "La ubicación del usuario no puede ser nula.")
    private Location UbicacionCuentaTrabajador;

    private Boolean Activo = false;

    public AccountEmployeeDTO(AccountEmployee CuentaTrabajador) {

        id = CuentaTrabajador.getId();
        NumeroDeCuenta = CuentaTrabajador.getNumeroDeCuenta();
        Nombre = CuentaTrabajador.getNombre();
        ApellidoPaterno = CuentaTrabajador.getApellidoPaterno();
        Telefono = CuentaTrabajador.getTelefono();
        Rut = CuentaTrabajador.getRut();
        Email = CuentaTrabajador.getEmail();
        Password = CuentaTrabajador.getPassword();
        Autos = CuentaTrabajador.getAutos();
        Transancciones = CuentaTrabajador.getTransancciones();
        Terminales = CuentaTrabajador.getTerminales();
        Permisos = CuentaTrabajador.getPermisos();
        Credenciales = CuentaTrabajador.getCredenciales();
        Tarifas = CuentaTrabajador.getTarifas();
        Trabajador = CuentaTrabajador.getTrabajador().getId();
        CuentaCompañia = CuentaTrabajador.getCuentaCompañia().getId();
        Rol = CuentaTrabajador.getRol();
        TipoCuenta = CuentaTrabajador.getTipoCuenta();
        UbicacionCuentaTrabajador = CuentaTrabajador.getUbicacionCuentaTrabajador();
        Activo = CuentaTrabajador.getActivo();
    }

    public long getId() {
        return id;
    }

    public @NotNull(message = "El número de cuenta no puede ser nulo.") String getNumeroDeCuenta() {
        return NumeroDeCuenta;
    }

    public @NotNull(message = "El nombre no puede estar vacío") String getNombre() {
        return Nombre;
    }

    public @NotBlank(message = "El apellido paterno no puede estar vacío") String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public @NotBlank(message = "El número de teléfono no puede estar vacío") Integer getTelefono() {
        return Telefono;
    }

    public @NotBlank(message = "El RUT no puede estar vacío") String getRut() {
        return Rut;
    }

    public @NotNull(message = "El Email es obligatorio") String getEmail() {
        return Email;
    }

    public @NotNull(message = "La Contraseña es obligatoria") String getPassword() {
        return Password;
    }

    public Set<Car> getAutos() {
        return Autos;
    }

    public Set<Transaction> getTransancciones() {
        return Transancciones;
    }

    public Set<ChargingStation> getTerminales() {
        return Terminales;
    }

    public Set<Permission> getPermisos() {
        return Permisos;
    }

    public Set<Credential> getCredenciales() {
        return Credenciales;
    }

    public Set<Fee> getTarifas() {
        return Tarifas;
    }

    public long getTrabajador() {
        return Trabajador;
    }

    public long getCuentaCompañia() {
        return CuentaCompañia;
    }

    public Role getRol() {
        return Rol;
    }

    public @NotNull(message = "El tipo de cuenta no puede ser vacío") TypeAccounts getTipoCuenta() {
        return TipoCuenta;
    }

    public @NotNull(message = "La ubicación del usuario no puede ser nula.") Location getUbicacionCuentaTrabajador() {
        return UbicacionCuentaTrabajador;
    }

    public Boolean getActivo() {
        return Activo;
    }

    @Override
    public String toString() {
        return "AccountEmployeeDTO{" +
                "id=" + id +
                ", NumeroDeCuenta='" + NumeroDeCuenta + '\'' +
                ", Nombre='" + Nombre + '\'' +
                ", ApellidoPaterno='" + ApellidoPaterno + '\'' +
                ", Telefono=" + Telefono +
                ", Rut='" + Rut + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", Autos=" + Autos +
                ", Transancciones=" + Transancciones +
                ", Terminales=" + Terminales +
                ", Permisos=" + Permisos +
                ", Credenciales=" + Credenciales +
                ", Tarifas=" + Tarifas +
                ", Trabajador=" + Trabajador +
                ", CuentaCompañia=" + CuentaCompañia +
                ", Rol=" + Rol +
                ", TipoCuenta=" + TipoCuenta +
                ", UbicacionCuentaTrabajador=" + UbicacionCuentaTrabajador +
                ", Activo=" + Activo +
                '}';
    }
}
