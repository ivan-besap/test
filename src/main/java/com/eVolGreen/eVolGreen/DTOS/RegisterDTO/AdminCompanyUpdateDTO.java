package com.eVolGreen.eVolGreen.DTOS.RegisterDTO;

import com.eVolGreen.eVolGreen.Models.Account.Location;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.PermissionCredential;
import com.eVolGreen.eVolGreen.Models.User.Role;
import com.eVolGreen.eVolGreen.Models.Account.Car.Car;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Credential;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Permission;
import com.eVolGreen.eVolGreen.Models.Account.Transaction.Transaction;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.AdminCompanyUser;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.ClientUser;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.EmployeeUser;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

public class AdminCompanyUpdateDTO {

    // Propiedades de AdminCompanyRegisterDTO
    @NotBlank(message = "El nombre de la compañía no puede estar vacío")
    private String nombreCompañia;

    @NotBlank(message = "El RUT no puede estar vacío")
    private String rut;

    @NotBlank(message = "El número de teléfono no puede estar vacío")
    private Integer telefono;

    @NotNull(message = "El Email es obligatorio")
    private String email;

    @NotNull(message = "La Contraseña es obligatoria")
    private String password;

    @NotNull(message = "La Contraseña es obligatoria")
    private String passwordConfirmation;

    private Role rol;

    @NotNull(message = "El estado de la cuenta no puede ser nulo.")
    private Boolean activo = false;

    // Propiedades de CompanyUser

    @NotNull(message = "El Email es obligatorio")
    private String emailCompañia;

    @NotNull(message = "La Contraseña es obligatoria")
    private String passwordCompañia;

    @NotBlank(message = "El RUT no puede estar vacío")
    private String rutCompañia;

    public AdminCompanyUpdateDTO(AdminCompanyUser adminCompanyUser, CompanyUser companyUser) {
        nombreCompañia = adminCompanyUser.getNombreCompañia();
        rut = adminCompanyUser.getRut();
        telefono = adminCompanyUser.getTelefono();
        email = adminCompanyUser.getEmail();
        password = adminCompanyUser.getPassword();
        rol = adminCompanyUser.getRol();
        activo = adminCompanyUser.getActivo();
        emailCompañia = companyUser.getEmail();
        passwordCompañia = companyUser.getPassword();
        rutCompañia = adminCompanyUser.getRut();
    }

    public AdminCompanyUpdateDTO() {}

    public @NotBlank(message = "El nombre de la compañía no puede estar vacío") String getNombreCompañia() {
        return nombreCompañia;
    }

    public @NotBlank(message = "El RUT no puede estar vacío") String getRut() {
        return rut;
    }

    public @NotBlank(message = "El número de teléfono no puede estar vacío") Integer getTelefono() {
        return telefono;
    }

    public @NotNull(message = "El Email es obligatorio") String getEmail() {
        return email;
    }

    public @NotNull(message = "La Contraseña es obligatoria") String getPassword() {
        return password;
    }

    public @NotNull(message = "La Contraseña es obligatoria") String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public Role getRol() {
        return rol;
    }

    public @NotNull(message = "El estado de la cuenta no puede ser nulo.") Boolean getActivo() {
        return activo;
    }

    public @NotNull(message = "El Email es obligatorio") String getEmailCompañia() {
        return emailCompañia;
    }

    public @NotNull(message = "La Contraseña es obligatoria") String getPasswordCompañia() {
        return passwordCompañia;
    }

    public @NotBlank(message = "El RUT no puede estar vacío") String getRutCompañia() {
        return rutCompañia;
    }

    @Override
    public String toString() {
        return "AdminCompanyUpdateDTO{" +
                "nombreCompañia='" + nombreCompañia + '\'' +
                ", rut='" + rut + '\'' +
                ", telefono=" + telefono +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirmation='" + passwordConfirmation + '\'' +
                ", rol=" + rol +
                ", activo=" + activo +
                ", emailCompañia='" + emailCompañia + '\'' +
                ", passwordCompañia='" + passwordCompañia + '\'' +
                ", rutCompañia='" + rutCompañia + '\'' +
                '}';
    }
}
