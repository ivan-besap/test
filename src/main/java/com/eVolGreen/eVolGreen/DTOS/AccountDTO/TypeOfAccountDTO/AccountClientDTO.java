package com.eVolGreen.eVolGreen.DTOS.AccountDTO.TypeOfAccountDTO;

import com.eVolGreen.eVolGreen.Models.Account.Car.Car;
import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;
import com.eVolGreen.eVolGreen.Models.Account.Location;
import com.eVolGreen.eVolGreen.Models.Account.Reservation;
import com.eVolGreen.eVolGreen.Models.Account.Transaction.Transaction;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountClient;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.TypeAccounts;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.eVolGreen.eVolGreen.Models.User.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public class AccountClientDTO {

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

    private Set<Reservation> Reservaciones;

    private Set<Fee> Tarifas;

    private long Cliente;

    private long CuentaCompañia;

    @Enumerated(EnumType.STRING)
    private Role Rol = Role.CLIENT;

    @NotNull(message = "El tipo de cuenta no puede ser vacío")
    private TypeAccounts TipoCuenta = TypeAccounts.Client;

    @NotNull(message = "La ubicación del cliente no puede ser nula.")
    private Location UbicacionCuentaCliente;

    private Boolean Activo = false;

    public AccountClientDTO(AccountClient CuentaCliente) {

        id = CuentaCliente.getId();
        NumeroDeCuenta = CuentaCliente.getNumeroDeCuenta();
        Nombre = CuentaCliente.getNombre();
        ApellidoPaterno = CuentaCliente.getApellidoPaterno();
        Telefono = CuentaCliente.getTelefono();
        Rut = CuentaCliente.getRut();
        Email = CuentaCliente.getEmail();
        Password = CuentaCliente.getPassword();
        Autos = CuentaCliente.getAutos();
        Transancciones = CuentaCliente.getTransancciones();
        Terminales = CuentaCliente.getTerminales();
        Reservaciones = CuentaCliente.getReservaciones();
        Tarifas = CuentaCliente.getTarifas();
        Cliente = CuentaCliente.getCliente().getId();
        CuentaCompañia = CuentaCliente.getCuentaCompañia().getId();
        Rol = CuentaCliente.getRol();
        TipoCuenta = CuentaCliente.getTipoCuenta();
        UbicacionCuentaCliente = CuentaCliente.getUbicacionCuentaCliente();
        Activo = CuentaCliente.getActivo();
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

    public Set<Reservation> getReservaciones() {
        return Reservaciones;
    }

    public Set<Fee> getTarifas() {
        return Tarifas;
    }

    public long getCliente() {
        return Cliente;
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

    public @NotNull(message = "La ubicación del cliente no puede ser nula.") Location getUbicacionCuentaCliente() {
        return UbicacionCuentaCliente;
    }

    public Boolean getActivo() {
        return Activo;
    }

    @Override
    public String toString() {
        return "AccountClientDTO{" +
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
                ", Reservaciones=" + Reservaciones +
                ", Tarifas=" + Tarifas +
                ", Cliente=" + Cliente +
                ", CuentaCompañia=" + CuentaCompañia +
                ", Rol=" + Rol +
                ", TipoCuenta=" + TipoCuenta +
                ", UbicacionCuentaCliente=" + UbicacionCuentaCliente +
                ", Activo=" + Activo +
                '}';
    }
}
