package com.eVolGreen.eVolGreen.DTOS.RegisterDTO;

import com.eVolGreen.eVolGreen.Models.User.Role;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.AdminCompanyUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AdminCompanyRegisterDTO {

    @NotBlank(message = "El nombre de la compañía no puede estar vacío")
    private String nombreCompañia;

    @NotBlank(message = "El RUT no puede estar vacío")
    private String rut;

    @NotNull(message = "El número de teléfono no puede estar vacío")
    private Integer telefono;

    @NotNull(message = "El Email es obligatorio")
    private String email;

    @NotNull(message = "La Contraseña es obligatoria")
    private String password;

    @NotNull(message = "La Contraseña es obligatoria")
    private String passwordConfirmation;

    @NotNull(message = "El rol no puede ser nulo")
    private Role rol;

    @NotNull(message = "El estado de la cuenta no puede ser nulo.")
    private Boolean activo;

    // Constructor vacío
    public AdminCompanyRegisterDTO() { }

    // Constructor que toma un AdminCompanyUser y la confirmación de la contraseña
    public AdminCompanyRegisterDTO(AdminCompanyUser adminCompany, String passwordConfirmation) {
        this.nombreCompañia = adminCompany.getNombreCompañia();
        this.rut = adminCompany.getRut();
        this.telefono = adminCompany.getTelefono();
        this.email = adminCompany.getEmail();
        this.password = adminCompany.getPassword();
        this.rol = adminCompany.getRol();
        this.activo = adminCompany.getActivo();
        this.passwordConfirmation = passwordConfirmation;
    }

    // Getters y Setters
    public String getNombreCompañia() {
        return nombreCompañia;
    }

    public void setNombreCompañia(String nombreCompañia) {
        this.nombreCompañia = nombreCompañia;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
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

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public Role getRol() {
        return rol;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
