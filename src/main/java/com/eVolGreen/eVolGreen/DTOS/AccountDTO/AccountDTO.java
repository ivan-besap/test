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
    private Role rol;


    @NotNull(message = "El teléfono no puede ser nulo.")
    private String telefono;

    @NotNull(message = "El RUT no puede ser nulo.")
    private String rut;

    private EmpresaDTO empresa;

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
        this.rol = account.getRol();
        this.telefono = account.getTelefono();
        this.rut = account.getRut();
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

    public Role getRol() {
        return rol;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getRut() {
        return rut;
    }

    public EmpresaDTO getEmpresa() {
        return empresa;
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
                ", empresa=" + (empresa != null ? empresa.getNombre() : "null") +
                '}';
    }
}