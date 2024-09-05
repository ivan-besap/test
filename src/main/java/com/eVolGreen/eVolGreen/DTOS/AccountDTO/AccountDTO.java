package com.eVolGreen.eVolGreen.DTOS.AccountDTO;

import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.TypeAccounts;
import com.eVolGreen.eVolGreen.Models.User.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class AccountDTO {

    private long id;

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

    @NotNull(message = "El tipo de cuenta no puede ser nulo.")
    private TypeAccounts tipoCuenta;

    @NotNull(message = "El rol no puede ser nulo.")
    private RoleDTO rol;


    @NotNull(message = "El teléfono no puede ser nulo.")
    private String telefono;

    @NotNull(message = "El RUT no puede ser nulo.")
    private String rut;

    private EmpresaDTO empresa;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;


    public AccountDTO() {}

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.numeroDeCuenta = account.getNumeroDeCuenta();
        this.nombreCuenta = account.getNombreCuenta();
        this.fechaDeCreacion = account.getFechaDeCreacion();
        this.email = account.getEmail();
        this.password = account.getPassword();
        this.activo = account.getActivo();
        this.tipoCuenta = account.getTipoCuenta();
        this.rol = new RoleDTO(account.getRole());
        this.telefono = account.getTelefono();
        this.rut = account.getRut();
        this.nombre = account.getNombre();
        this.apellidoPaterno = account.getApellidoPaterno();
        this.apellidoMaterno = account.getApellidoMaterno();
        this.empresa = new EmpresaDTO(account.getEmpresa());
    }

    public long getId() {
        return id;
    }

    public String getNumeroDeCuenta() {
        return numeroDeCuenta;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public LocalDate getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getActivo() {
        return activo;
    }

    public TypeAccounts getTipoCuenta() {
        return tipoCuenta;
    }

    public RoleDTO getRol() {
        return rol;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getRut() {
        return rut;
    }
    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public EmpresaDTO getEmpresa() {
        return empresa;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNumeroDeCuenta(String numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    public void setFechaDeCreacion(LocalDate fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public void setTipoCuenta(TypeAccounts tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public void setRol(RoleDTO rol) {
        this.rol = rol;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setEmpresa(EmpresaDTO empresa) {
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "id=" + id +
                ", numeroDeCuenta='" + numeroDeCuenta + '\'' +
                ", nombreCuenta='" + nombreCuenta + '\'' +
                ", fechaDeCreacion=" + fechaDeCreacion +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", activo=" + activo +
                ", tipoCuenta=" + tipoCuenta +
                ", rol=" + rol +
                ", telefono='" + telefono + '\'' +
                ", rut='" + rut + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", empresa=" + (empresa != null ? empresa.getNombre() : "null") +
                '}';
    }
}