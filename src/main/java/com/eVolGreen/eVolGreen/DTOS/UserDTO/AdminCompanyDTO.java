package com.eVolGreen.eVolGreen.DTOS.UserDTO;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.AccountDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;
import com.eVolGreen.eVolGreen.Models.Account.RelationTypeCompany.CompanyClientRelation;
import com.eVolGreen.eVolGreen.Models.Account.RelationTypeCompany.CompanyEmployeeRelation;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.eVolGreen.eVolGreen.Models.User.Role;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.AdminCompanyUser;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AdminCompanyDTO {

    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    @NotBlank(message = "El número de teléfono no puede estar vacío")
    private Integer telefono;

    @NotBlank(message = "El nombre de la compañía no puede estar vacío")
    private String nombreCompañia;

    @NotBlank(message = "El RUT no puede estar vacío")
    private String rut;

    private List<Long> cuentaPrincipal;

    @NotNull(message = "El rol no puede ser nulo")
    @Enumerated(EnumType.STRING)
    private Role rol = Role.ADMIN_COMPANY;

    @NotNull(message = "El estado de la cuenta no puede ser nulo.")
    private Boolean activo = false;

    public AdminCompanyDTO() { }

    public AdminCompanyDTO(AdminCompanyUser adminCompañia){
        id = adminCompañia.getId();
        email = adminCompañia.getEmail();
        password = adminCompañia.getPassword();
        telefono = adminCompañia.getTelefono();
        nombreCompañia = adminCompañia.getNombreCompañia();
        rut = adminCompañia.getRut();
        cuentaPrincipal = adminCompañia.getCuentaPrincipal().stream().map(AccountDTO -> new AccountDTO(AccountDTO).getId()).collect(Collectors.toList());
        rol = adminCompañia.getRol();
        activo = adminCompañia.getActivo();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public @NotBlank(message = "El número de teléfono no puede estar vacío") Integer getTelefono() {
        return telefono;
    }

    public @NotBlank(message = "El nombre de la compañía no puede estar vacío") String getNombreCompañia() {
        return nombreCompañia;
    }

    public @NotBlank(message = "El RUT no puede estar vacío") String getRut() {
        return rut;
    }

    public List<Long> getCuentaPrincipal() {
        return cuentaPrincipal;
    }

    public @NotNull(message = "El rol no puede ser nulo") Role getRol() {
        return rol;
    }

    public @NotNull(message = "El estado de la cuenta no puede ser nulo.") Boolean getActivo() {
        return activo;
    }

    @Override
    public String toString() {
        return "AdminCompanyDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", telefono=" + telefono +
                ", nombreCompañia='" + nombreCompañia + '\'' +
                ", rut='" + rut + '\'' +
                ", cuentaPrincipal=" + cuentaPrincipal +
                ", rol=" + rol +
                ", activo=" + activo +
                '}';
    }
}
