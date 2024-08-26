package com.eVolGreen.eVolGreen.DTOS.UserDTO.ClientDTO;

import com.eVolGreen.eVolGreen.Models.User.subclassUser.ClientUser;

public class ClientUserLoginDTO {

    private Long id;

    private String Nombre;

    private String ApellidoPaterno;

    private String Email;

    private Boolean isActive;


    public ClientUserLoginDTO(ClientUser client) {

        id = client.getId();
        Nombre = client.getNombre();
        ApellidoPaterno = client.getApellidoPaterno();
        Email = client.getEmail();
        isActive = client.getActivo();

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
        return "ClientUserLoginDTO{" +
                "id=" + id +
                ", Nombre='" + Nombre + '\'' +
                ", ApellidoPaterno='" + ApellidoPaterno + '\'' +
                ", Email='" + Email + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
