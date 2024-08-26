package com.eVolGreen.eVolGreen.DTOS.UserDTO.EmployeeDTO;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.PermissionCredentialDTO.CredentialDTO;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Credential;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountEmployee;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.EmployeeUser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeRegisterDTO {

    private long id;

    private String nombre;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private String email;

    private String password;

    private Integer telefono;

    private String nombreCredencial;


    public EmployeeRegisterDTO() { }

    public EmployeeRegisterDTO(EmployeeUser employee, AccountEmployee accountEmployee) {
        id = employee.getId();
        nombre = employee.getNombre();
        apellidoPaterno = employee.getApellidoPaterno();
        apellidoMaterno = employee.getApellidoMaterno();
        email = employee.getEmail();
        password = employee.getPassword();
        telefono = employee.getTelefono();
        nombreCredencial = accountEmployee.getCredenciales().stream().findFirst().map(Credential::getNombre).orElse(null);
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public String getNombreCredencial() {
        return nombreCredencial;
    }
}
