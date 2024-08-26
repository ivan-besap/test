package com.eVolGreen.eVolGreen.DTOS.UserDTO.CompanyDTO;

import com.eVolGreen.eVolGreen.Models.User.Role;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;

public class CompanyUserRegisterDTO {

    private Long id;

    private String nombreCompañia;

    private String rut;

    private String email;

    private String password;

    private Integer telefono;

    private Boolean active = false;

    private Role role;

    public CompanyUserRegisterDTO() { }

    public CompanyUserRegisterDTO(CompanyUser Compañia) {

        id = Compañia.getId();
        nombreCompañia = Compañia.getNombreCompañia();
        rut = Compañia.getRut();
        email = Compañia.getEmail();
        password = Compañia.getPassword();
        telefono = Compañia.getTelefono();
        active = Compañia.getActivo();
        role = Compañia.getRol();
    }

    public Long getId() {
        return id;
    }

    public String getNombreCompañia() {
        return nombreCompañia;
    }

    public String getRut() {
        return rut;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getActive() {
        return active;
    }

    public Role getRole() {
        return role;
    }

    public Integer getTelefono() {
        return telefono;
    }
}
