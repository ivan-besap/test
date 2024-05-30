package com.eVolGreen.eVolGreen.DTOS;

import com.eVolGreen.eVolGreen.Models.Account;
import com.eVolGreen.eVolGreen.Models.Client;

import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer rut;
    private String email;
    private Integer phone;
    private String password;
    private Set<AccountDTO> account;


    public ClientDTO(Client client) {
        id = client.getId();
        firstName = client.getFirstName();
        lastName = client.getLastName();
        rut = client.getRut();
        email = client.getEmail();
        phone = client.getPhone();
        password = client.getPassword();
        account = client.getAccounts().stream().map(AccountDTO -> new AccountDTO(AccountDTO)).collect(Collectors.toSet());

    }

    public Long getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getPhone() {
        return phone;
    }

    public Integer getRut() {
        return rut;
    }

    public String getPassword() {
        return password;
    }

    public Set<AccountDTO> getAccount() {
        return account;
    }
}
