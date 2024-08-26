package com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount;

import com.eVolGreen.eVolGreen.Models.Account.Location;
import com.eVolGreen.eVolGreen.Models.User.Role;
import com.eVolGreen.eVolGreen.Models.Account.Car.Car;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Credential;
import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Permission;
import com.eVolGreen.eVolGreen.Models.Account.Transaction.Transaction;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.EmployeeUser;
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
public class AccountEmployee {

    @Id
    @GenericGenerator(name= "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long id;

    @NotNull(message = "El número de cuenta no puede ser nulo.")
    private String numeroDeCuenta;

    @NotNull(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El apellido paterno no puede estar vacío")
    private String apellidoPaterno;

    @NotBlank(message = "El apellido paterno no puede estar vacío")
    private String apellidoMaterno;

    @NotBlank(message = "El número de teléfono no puede estar vacío")
    private Integer telefono;

    @NotBlank(message = "El RUT no puede estar vacío")
    private String rut;

    @NotNull(message = "El Email es obligatorio")
    private String email;

    @NotNull(message = "La Contraseña es obligatoria")
    private String password;

    @OneToMany(mappedBy = "CuentaTrabajador", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("CuentaTrabajador-Auto")
    private Set<Car> Autos = new HashSet<>();

    @OneToMany(mappedBy = "CuentaTrabajador", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("CuentaTrabajador-Transaccion")
    private Set<Transaction> Transancciones = new HashSet<>();

    @OneToMany(mappedBy = "CuentaTrabajador", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("CuentaTrabajador-Terminal")
    private Set<ChargingStation> Terminales = new HashSet<>();

    @OneToMany(mappedBy = "CuentaTrabajador", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("CuentaTrabajador-Permisos")
    private Set<Permission> Permisos = new HashSet<>();

    @OneToMany(mappedBy = "cuentaTrabajador", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("CuentaTrabajador-Credenciales")
    private Set<Credential> credenciales = new HashSet<>();

    @OneToMany(mappedBy = "CuentaTrabajador", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("CuentaTrabajador-Tarifas")
    private Set<Fee> Tarifas = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Trabajador_id")
    @JsonBackReference("Trabajador-CuentaTrabajador")
    private EmployeeUser Trabajador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CuentaCompañia_id")
    @JsonBackReference("CuentaTrabajador-CuentaCompañia")
    private AccountCompany CuentaCompañia;

    @Enumerated(EnumType.STRING)
    private Role rol = Role.EMPLOYEE;

    @NotNull(message = "El tipo de cuenta no puede ser vacío")
    @Enumerated(EnumType.STRING)
    private TypeAccounts tipoCuenta = TypeAccounts.Employee;

    @NotNull(message = "La ubicación del usuario no puede ser nula.")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "Ubicacion_id")
    @JsonManagedReference("UbicacionCuentaTrabajador")
    private Location UbicacionCuentaTrabajador;

    private Boolean activo = false;

    public AccountEmployee() { }

    public AccountEmployee(String numeroDeCuenta, String nombre, String apellidoPaterno, String apellidoMaterno, Integer telefono, String rut, String email, String password, Role Rol, TypeAccounts tipoCuenta,Location ubicacionCuentaTrabajador) {
        this.numeroDeCuenta = numeroDeCuenta;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.rut = rut;
        this.email = email;
        this.password = password;
        this.rol = Rol;
        this.tipoCuenta = tipoCuenta;
        this.UbicacionCuentaTrabajador = ubicacionCuentaTrabajador;
        this.activo = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotNull(message = "El número de cuenta no puede ser nulo.") String getNumeroDeCuenta() {
        return numeroDeCuenta;
    }

    public void setNumeroDeCuenta(@NotNull(message = "El número de cuenta no puede ser nulo.") String numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
    }

    public @NotNull(message = "El nombre no puede estar vacío") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotNull(message = "El nombre no puede estar vacío") String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank(message = "El apellido paterno no puede estar vacío") String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(@NotBlank(message = "El apellido paterno no puede estar vacío") String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public @NotBlank(message = "El apellido paterno no puede estar vacío") String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(@NotBlank(message = "El apellido paterno no puede estar vacío") String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public @NotBlank(message = "El número de teléfono no puede estar vacío") Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(@NotBlank(message = "El número de teléfono no puede estar vacío") Integer telefono) {
        this.telefono = telefono;
    }

    public @NotBlank(message = "El RUT no puede estar vacío") String getRut() {
        return rut;
    }

    public void setRut(@NotBlank(message = "El RUT no puede estar vacío") String rut) {
        this.rut = rut;
    }

    public @NotNull(message = "El Email es obligatorio") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "El Email es obligatorio") String email) {
        this.email = email;
    }

    public @NotNull(message = "La Contraseña es obligatoria") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull(message = "La Contraseña es obligatoria") String password) {
        this.password = password;
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

    public Set<Permission> getPermisos() {
        return Permisos;
    }

    public void setPermisos(Set<Permission> permisos) {
        Permisos = permisos;
    }

    public Set<Credential> getCredenciales() {
        return credenciales;
    }

    public void setCredenciales(Set<Credential> credenciales) {
        this.credenciales = credenciales;
    }

    public Set<Fee> getTarifas() {
        return Tarifas;
    }

    public void setTarifas(Set<Fee> tarifas) {
        Tarifas = tarifas;
    }

    public EmployeeUser getTrabajador() {
        return Trabajador;
    }

    public void setTrabajador(EmployeeUser trabajador) {
        Trabajador = trabajador;
    }

    public AccountCompany getCuentaCompañia() {
        return CuentaCompañia;
    }

    public void setCuentaCompañia(AccountCompany cuentaCompañia) {
        CuentaCompañia = cuentaCompañia;
    }

    public Role getRol() {
        return rol;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }

    public @NotNull(message = "El tipo de cuenta no puede ser vacío") TypeAccounts getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(@NotNull(message = "El tipo de cuenta no puede ser vacío") TypeAccounts tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public @NotNull(message = "La ubicación del usuario no puede ser nula.") Location getUbicacionCuentaTrabajador() {
        return UbicacionCuentaTrabajador;
    }

    public void setUbicacionCuentaTrabajador(@NotNull(message = "La ubicación del usuario no puede ser nula.") Location ubicacionCuentaTrabajador) {
        UbicacionCuentaTrabajador = ubicacionCuentaTrabajador;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}

