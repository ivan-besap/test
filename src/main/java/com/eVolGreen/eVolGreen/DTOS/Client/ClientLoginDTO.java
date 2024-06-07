package com.eVolGreen.eVolGreen.DTOS.Client;

import com.eVolGreen.eVolGreen.Models.Client;

public class ClientLoginDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;


    public ClientLoginDTO(Client client) {
        id = client.getId();
        firstName = client.getFirstName();
        lastName = client.getLastName();
        email = client.getEmail();

    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

}
