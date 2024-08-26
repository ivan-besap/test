package com.eVolGreen.eVolGreen.DTOS.UserDTO.ClientDTO;

import com.eVolGreen.eVolGreen.Models.Account.RelationTypeCompany.CompanyClientRelation;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountClient;
import com.eVolGreen.eVolGreen.Models.User.Role;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.ClientUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public class ClientUserDTO {

    private long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String Nombre;

    @NotBlank(message = "El apellido paterno no puede estar vacío")
    private String ApellidoPaterno;

    @NotBlank(message = "El apellido materno no puede estar vacío")
    private String ApellidoMaterno;

    @NotBlank(message = "El RUT no puede estar vacío")
    private String Rut;

    private Set<CompanyClientRelation> RelacionCompañiaCliente;

    private Set<AccountClient> CuentaCliente;

    private long CuentaCompañia;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean Activo= false;

    public ClientUserDTO(ClientUser client) {

        id = client.getId();
        Nombre = client.getNombre();
        ApellidoPaterno = client.getApellidoPaterno();
        ApellidoMaterno = client.getApellidoMaterno();
        Rut = client.getRut();
        RelacionCompañiaCliente = client.getRelacionCompañiaCliente();
        CuentaCliente = client.getCuentaCliente();
        CuentaCompañia = client.getCuentaCompañia().getId();
        role = client.getRol();
        Activo = client.getActivo();

    }

    public long getId() {
        return id;
    }

    public @NotBlank(message = "El nombre no puede estar vacío") String getNombre() {
        return Nombre;
    }

    public @NotBlank(message = "El apellido paterno no puede estar vacío") String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public @NotBlank(message = "El apellido materno no puede estar vacío") String getApellidoMaterno() {
        return ApellidoMaterno;
    }

    public @NotBlank(message = "El RUT no puede estar vacío") String getRut() {
        return Rut;
    }

    public Set<CompanyClientRelation> getRelacionCompañiaCliente() {
        return RelacionCompañiaCliente;
    }

    public Set<AccountClient> getCuentaCliente() {
        return CuentaCliente;
    }

    public long getCuentaCompañia() {
        return CuentaCompañia;
    }

    public Role getRole() {
        return role;
    }

    public Boolean getActivo() {
        return Activo;
    }

    @Override
    public String toString() {
        return "ClientUserDTO{" +
                "Nombre='" + Nombre + '\'' +
                ", ApellidoPaterno='" + ApellidoPaterno + '\'' +
                ", ApellidoMaterno='" + ApellidoMaterno + '\'' +
                ", Rut='" + Rut + '\'' +
                ", RelacionCompañiaCliente=" + RelacionCompañiaCliente +
                ", CuentaCliente=" + CuentaCliente +
                ", CuentaCompañia=" + CuentaCompañia +
                ", role=" + role +
                ", Activo=" + Activo +
                '}';
    }
}
