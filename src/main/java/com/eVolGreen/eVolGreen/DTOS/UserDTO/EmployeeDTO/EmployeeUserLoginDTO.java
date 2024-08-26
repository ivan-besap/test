package com.eVolGreen.eVolGreen.DTOS.UserDTO.EmployeeDTO;

import com.eVolGreen.eVolGreen.Models.User.subclassUser.EmployeeUser;

public class EmployeeUserLoginDTO {

    private Long id;

    private String Nombre;

    private String ApellidoPaterno;

    private String Email;

    private Boolean isActive;

    public EmployeeUserLoginDTO(EmployeeUser Trabajador) {

        id = Trabajador.getId();
        Nombre = Trabajador.getNombre();
        ApellidoPaterno = Trabajador.getApellidoPaterno();
        Email = Trabajador.getEmail();
        isActive = Trabajador.getActivo();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public String getEmail() {
        return Email;
    }

    public Boolean getActive() {
        return isActive;
    }

    @Override
    public String toString() {
        return "EmployeeUserLoginDTO{" +
                "id=" + id +
                ", Nombre='" + Nombre + '\'' +
                ", ApellidoPaterno='" + ApellidoPaterno + '\'' +
                ", Email='" + Email + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
