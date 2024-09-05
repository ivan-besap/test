package com.eVolGreen.eVolGreen.DTOS.AccountDTO;

import com.eVolGreen.eVolGreen.Models.Account.Account;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;

public class EmployeeRegisterDTO {

    @NotNull(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "El apellido paterno es obligatorio")
    private String apellidoPaterno;

    @NotNull(message = "El apellido materno es obligatorio")
    private String apellidoMaterno;

    @NotNull(message = "El email no puede ser nulo.")
    private String email;

    @NotNull(message = "El password no puede ser nulo.")
    private String password;

    @NotNull(message = "El teléfono no puede ser nulo.")
    private String telefono;

    @NotNull(message = "El RUT no puede ser nulo.")
    private String rut;

    @JoinColumn(name = "role_id", nullable = false)
    private Long role;

    public EmployeeRegisterDTO() { }

    public EmployeeRegisterDTO(Account account) {
        nombre = account.getNombre();
        apellidoPaterno = account.getApellidoPaterno();
        apellidoMaterno = account.getApellidoMaterno();
        email = account.getEmail();
        password = account.getPassword();
        telefono = account.getTelefono();
        rut = account.getRut();
        role = account.getRole().getId();
    }

    public @NotNull(message = "El nombre es obligatorio") String getNombre() {
        return nombre;
    }

    public @NotNull(message = "El apellido paterno es obligatorio") String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public @NotNull(message = "El apellido materno es obligatorio") String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public @NotNull(message = "El email no puede ser nulo.") String getEmail() {
        return email;
    }

    public @NotNull(message = "El password no puede ser nulo.") String getPassword() {
        return password;
    }

    public @NotNull(message = "El teléfono no puede ser nulo.") String getTelefono() {
        return telefono;
    }

    public @NotNull(message = "El RUT no puede ser nulo.") String getRut() {
        return rut;
    }

    public Long getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "EmployeeRegisterDTO{" +
                "nombre='" + nombre + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", telefono='" + telefono + '\'' +
                ", rut='" + rut + '\'' +
                ", role=" + role +
                '}';
    }
}
