package com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount;

import com.eVolGreen.eVolGreen.Models.Account.Location;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.PermissionCredential;
import com.eVolGreen.eVolGreen.Models.User.Role;
import com.eVolGreen.eVolGreen.Models.Account.Car.Car;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Credential;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Permission;
import com.eVolGreen.eVolGreen.Models.Account.Transaction.Transaction;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.ClientUser;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.EmployeeUser;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
public class AccountCompany  {

    @Id
    @GenericGenerator(name= "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long id;

    @NotNull(message = "El número de cuenta no puede ser nulo.")
    private String NumeroDeCuenta;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String NombreCompañia;

    @NotBlank(message = "El RUT no puede estar vacío")
    private String Rut;

    @NotNull(message = "El Email es obligatorio")
    private String Email;

    @NotNull(message = "La Contraseña es obligatoria")
    private String Password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Compañia_id", referencedColumnName = "id")
    @JsonBackReference("Compañia-CuentaCompañia")
    private CompanyUser Compañia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CuentaPrincipal_id")
    @JsonBackReference("CuentaPrincipal-CuentaCompañia")
    private Account cuentaPrincipal;

    @OneToMany(mappedBy = "CuentaCompañia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("Cliente-CuentaCompañia")
    private Set<ClientUser> Clientes = new HashSet<>();

    @OneToMany(mappedBy = "CuentaCompañia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("Trabajador-CuentaCompañia")
    private Set<EmployeeUser> Trabajador = new HashSet<>();

    @OneToMany(mappedBy = "CuentaCompañia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("Tarifa-CuentaCompañia")
    private Set<Fee> Tarifas = new HashSet<>();

    @OneToMany(mappedBy = "CuentaCompañia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("CuentaCompañia-Auto")
    private Set<Car> Autos = new HashSet<>();

    @OneToMany(mappedBy = "CuentaCompañia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("Terminal-CuentaCompañia")
    private Set<ChargingStation> Terminales = new HashSet<>();

    @OneToMany(mappedBy = "cuentaCompañia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("Permiso-CuentaCompañia")
    private Set<Permission> Permisos = new HashSet<>();

    @OneToMany(mappedBy = "cuentaCompañia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("Credenciales-CuentaCompañia")
    private Set<Credential> credenciales = new HashSet<>();

    @OneToMany(mappedBy = "CuentaCompañia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("CuentaTrabajador-CuentaCompañia")
    private Set<AccountEmployee> CuentaEmpleado = new HashSet<>();

    @OneToMany(mappedBy = "CuentaCompañia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("CuentaCliente-CuentaCompañia")
    private Set<AccountClient> CuentaCliente = new HashSet<>();

    @OneToMany(mappedBy = "CuentaCompañia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("Transacciones-CuentaCompañia")
    private Set<Transaction> Transacciones = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Role Rol = Role.COMPANY;

    @NotNull(message = "El tipo de cuenta no puede ser vacío")
    @Enumerated(EnumType.STRING)
    private TypeAccounts TipoCuenta = TypeAccounts.Company;

    @NotNull(message = "La ubicación de la compañia no puede ser nula.")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "Ubicacion_id")
    @JsonManagedReference("UbicacionCuentaCompañia")
    private Location UbicacionCuentaCompañia;

    @OneToMany(mappedBy = "cuentaCompañia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("PermisoCredencial-CuentaCompañia")
    private Set<PermissionCredential> permisoCredenciales = new HashSet<>();

    private Boolean Activo = false;

    public AccountCompany() {
    }

    public AccountCompany(String numeroDeCuenta, String nombreCompañia, String rut, String email, String password,  Role Rol, TypeAccounts TipoCuenta, Location UbicacionCuentaCompañia) {

        this.NumeroDeCuenta = numeroDeCuenta;
        this.NombreCompañia = nombreCompañia;
        this.Rut = rut;
        this.Email = email;
        this.Password = password;
        this.Rol = Rol;
        this.TipoCuenta = TipoCuenta;
        this.UbicacionCuentaCompañia = UbicacionCuentaCompañia;
        this.Activo = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotNull(message = "El número de cuenta no puede ser nulo.") String getNumeroDeCuenta() {
        return NumeroDeCuenta;
    }

    public void setNumeroDeCuenta(@NotNull(message = "El número de cuenta no puede ser nulo.") String numeroDeCuenta) {
        NumeroDeCuenta = numeroDeCuenta;
    }

    public @NotBlank(message = "El nombre no puede estar vacío") String getNombreCompañia() {
        return NombreCompañia;
    }

    public void setNombreCompañia(@NotBlank(message = "El nombre no puede estar vacío") String nombreCompañia) {
        NombreCompañia = nombreCompañia;
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

    public CompanyUser getCompañia() {
        return Compañia;
    }

    public void setCompañia(CompanyUser compañia) {
        Compañia = compañia;
    }

    public Account getCuentaPrincipal() {
        return cuentaPrincipal;
    }

    public void setCuentaPrincipal(Account cuentaPrincipal) {
        this.cuentaPrincipal = cuentaPrincipal;
    }

    public Set<ClientUser> getClientes() {
        return Clientes;
    }

    public void setClientes(Set<ClientUser> clientes) {
        Clientes = clientes;
    }

    public Set<EmployeeUser> getTrabajador() {
        return Trabajador;
    }

    public void setTrabajador(Set<EmployeeUser> trabajador) {
        Trabajador = trabajador;
    }

    public Set<Fee> getTarifas() {
        return Tarifas;
    }

    public void setTarifas(Set<Fee> tarifas) {
        Tarifas = tarifas;
    }

    public Set<Car> getAutos() {
        return Autos;
    }

    public void setAutos(Set<Car> autos) {
        Autos = autos;
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

    public Set<AccountEmployee> getCuentaEmpleado() {
        return CuentaEmpleado;
    }

    public void setCuentaEmpleado(Set<AccountEmployee> cuentaEmpleado) {
        CuentaEmpleado = cuentaEmpleado;
    }

    public Set<AccountClient> getCuentaCliente() {
        return CuentaCliente;
    }

    public void setCuentaCliente(Set<AccountClient> cuentaCliente) {
        CuentaCliente = cuentaCliente;
    }

    public Set<Transaction> getTransacciones() {
        return Transacciones;
    }

    public void setTransacciones(Set<Transaction> transacciones) {
        Transacciones = transacciones;
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

    public @NotNull(message = "La ubicación de la compañia no puede ser nula.") Location getUbicacionCuentaCompañia() {
        return UbicacionCuentaCompañia;
    }

    public void setUbicacionCuentaCompañia(@NotNull(message = "La ubicación de la compañia no puede ser nula.") Location ubicacionCuentaCompañia) {
        UbicacionCuentaCompañia = ubicacionCuentaCompañia;
    }

    public Set<PermissionCredential> getPermisoCredenciales() {
        return permisoCredenciales;
    }

    public void setPermisoCredenciales(Set<PermissionCredential> permisoCredenciales) {
        this.permisoCredenciales = permisoCredenciales;
    }

    public Boolean getActivo() {
        return Activo;
    }

    public void setActivo(Boolean activo) {
        Activo = activo;
    }

    public void addTrabajador(EmployeeUser trabajador) {
        this.Trabajador.add(trabajador);
        trabajador.setCuentaCompañia(this);
    }

    public void addPermission(Permission permission) {
        this.Permisos.add(permission);
        permission.setCuentaCompañia(this);
    }

    public void addPermissionCredential(PermissionCredential permisoCredenciales) {
        this.permisoCredenciales.add(permisoCredenciales);
        permisoCredenciales.setCuentaCompañia(this);
    }
}
