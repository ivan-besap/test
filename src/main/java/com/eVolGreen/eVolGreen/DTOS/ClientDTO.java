package com.eVolGreen.eVolGreen.DTOS;

import com.eVolGreen.eVolGreen.Models.Account;
import com.eVolGreen.eVolGreen.Models.Client;

import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {
    private Long id;
    private String name;
    private String email;
    private String lastName;
    private Integer phone;
    private Integer rut;
    private String checkDigit;
    private String password;
    private Set<AccountDTO> account;


    public ClientDTO(Client client) {
        id = client.getId();
        name = client.getName();
        email = client.getEmail();
        lastName = client.getLastName();
        phone = client.getPhone();
        rut = client.getRut();
        checkDigit = client.getCheckDigit();
        password = client.getPassword();
        account = client.getAccounts().stream().map(AccountDTO -> new AccountDTO(AccountDTO)).collect(Collectors.toSet());

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public String getCheckDigit() {
        return checkDigit;
    }

    public String getPassword() {
        return password;
    }

    public Set<AccountDTO> getAccount() {
        return account;
    }
}
