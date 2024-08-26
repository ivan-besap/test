package com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount;

import com.eVolGreen.eVolGreen.Models.Account.Location;
import com.eVolGreen.eVolGreen.Models.Account.Reservation;
import com.eVolGreen.eVolGreen.Models.User.Role;
import com.eVolGreen.eVolGreen.Models.Account.Car.Car;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;
import com.eVolGreen.eVolGreen.Models.Account.Transaction.Transaction;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.ClientUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
public class AccountClient {

    @Id
    @GenericGenerator(name= "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
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

    @OneToMany(mappedBy = "CuentaCliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("CuentaCliente-Auto")
    private Set<Car> Autos = new HashSet<>();

    @OneToMany(mappedBy = "CuentaCliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("CuentaCliente-Transaccion")
    private Set<Transaction> Transancciones = new HashSet<>();

    @OneToMany(mappedBy = "CuentaCliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("CuentaCliente-Terminal")
    private Set<ChargingStation> Terminales = new HashSet<>();

    @OneToMany(mappedBy = "CuentaCliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("CuentaCliente-Reservacion")
    private Set<Reservation> Reservaciones = new HashSet<>();

    @OneToMany(mappedBy = "CuentaCliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("CuentaCliente-Tarifa")
    private Set<Fee> Tarifas = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Cliente_id")
    @JsonBackReference("Cliente-CuentaCliente")
    private ClientUser Cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CuentaCompañia_id")
    @JsonBackReference("CuentaCliente-CuentaCompañia")
    private AccountCompany CuentaCompañia;

    @Enumerated(EnumType.STRING)
    private Role Rol = Role.CLIENT;

    @NotNull(message = "El tipo de cuenta no puede ser vacío")
    @Enumerated(EnumType.STRING)
    private TypeAccounts TipoCuenta = TypeAccounts.Client;

    @NotNull(message = "La ubicación del cliente no puede ser nula.")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "Ubicacion_id")
    @JsonManagedReference("UbicacionCuentaCliente")
    private Location UbicacionCuentaCliente;

    private Boolean Activo = false;

    public AccountClient() {}

    public AccountClient(String numeroDeCuenta, String nombre, String apellidoPaterno, Integer telefono, String rut, String email, String password, Role Rol, TypeAccounts TipoCuenta, Location UbicacionCuentaCliente) {
        NumeroDeCuenta = numeroDeCuenta;
        Nombre = nombre;
        ApellidoPaterno = apellidoPaterno;
        Telefono = telefono;
        Rut = rut;
        Email = email;
        Password = password;
        this.Rol = Rol;
        this.TipoCuenta = TipoCuenta;
        this.UbicacionCuentaCliente = UbicacionCuentaCliente;
        this.Activo = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "El número de cuenta no puede ser nulo.") String getNumeroDeCuenta() {
        return NumeroDeCuenta;
    }

    public void setNumeroDeCuenta(@NotNull(message = "El número de cuenta no puede ser nulo.") String numeroDeCuenta) {
        NumeroDeCuenta = numeroDeCuenta;
    }

    public @NotNull(message = "El nombre no puede estar vacío") String getNombre() {
        return Nombre;
    }

    public void setNombre(@NotNull(message = "El nombre no puede estar vacío") String nombre) {
        Nombre = nombre;
    }

    public @NotBlank(message = "El apellido paterno no puede estar vacío") String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public void setApellidoPaterno(@NotBlank(message = "El apellido paterno no puede estar vacío") String apellidoPaterno) {
        ApellidoPaterno = apellidoPaterno;
    }

    public @NotBlank(message = "El número de teléfono no puede estar vacío") Integer getTelefono() {
        return Telefono;
    }

    public void setTelefono(@NotBlank(message = "El número de teléfono no puede estar vacío") Integer telefono) {
        Telefono = telefono;
    }

    public @NotBlank(message = "El RUT no puede estar vacío") String getRut() {
        return Rut;
    }

    public void setRut(@NotBlank(message = "El RUT no puede estar vacío") String rut) {
        Rut = rut;
    }

    public @NotNull(message = "El Email es obligatorio") String getEmail() {
        return Email;
    }

    public void setEmail(@NotNull(message = "El Email es obligatorio") String email) {
        Email = email;
    }

    public @NotNull(message = "La Contraseña es obligatoria") String getPassword() {
        return Password;
    }

    public void setPassword(@NotNull(message = "La Contraseña es obligatoria") String password) {
        Password = password;
    }

    public Set<Car> getAutos() {
        return Autos;
    }

    public void setAutos(Set<Car> autos) {
        Autos = autos;
    }

    public Set<Transaction> getTransancciones() {
        return Transancciones;
    }

    public void setTransancciones(Set<Transaction> transancciones) {
        Transancciones = transancciones;
    }

    public Set<ChargingStation> getTerminales() {
        return Terminales;
    }

    public void setTerminales(Set<ChargingStation> terminales) {
        Terminales = terminales;
    }

    public Set<Reservation> getReservaciones() {
        return Reservaciones;
    }

    public void setReservaciones(Set<Reservation> reservaciones) {
        Reservaciones = reservaciones;
    }

    public Set<Fee> getTarifas() {
        return Tarifas;
    }

    public void setTarifas(Set<Fee> tarifas) {
        Tarifas = tarifas;
    }

    public ClientUser getCliente() {
        return Cliente;
    }

    public void setCliente(ClientUser cliente) {
        Cliente = cliente;
    }

    public AccountCompany getCuentaCompañia() {
        return CuentaCompañia;
    }

    public void setCuentaCompañia(AccountCompany cuentaCompañia) {
        CuentaCompañia = cuentaCompañia;
    }

    public Role getRol() {
        return Rol;
    }

    public void setRol(Role rol) {
        Rol = rol;
    }

    public @NotNull(message = "El tipo de cuenta no puede ser vacío") TypeAccounts getTipoCuenta() {
        return TipoCuenta;
    }

    public void setTipoCuenta(@NotNull(message = "El tipo de cuenta no puede ser vacío") TypeAccounts tipoCuenta) {
        TipoCuenta = tipoCuenta;
    }

    public @NotNull(message = "La ubicación del cliente no puede ser nula.") Location getUbicacionCuentaCliente() {
        return UbicacionCuentaCliente;
    }

    public void setUbicacionCuentaCliente(@NotNull(message = "La ubicación del cliente no puede ser nula.") Location ubicacionCuentaCliente) {
        UbicacionCuentaCliente = ubicacionCuentaCliente;
    }

    public Boolean getActivo() {
        return Activo;
    }

    public void setActivo(Boolean activo) {
        Activo = activo;
    }

    @Override
    public String toString() {
        return "AccountClient{" +
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
