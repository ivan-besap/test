package com.eVolGreen.eVolGreen.DTOS.UserDTO.CompanyDTO;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.AccountDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.FeeDTO.FeeDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.RelationTypeCompanyDTO.CompanyClientRelationDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.RelationTypeCompanyDTO.CompanyEmployeeRelationDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.TypeOfAccountDTO.AccountCompanyDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;
import com.eVolGreen.eVolGreen.Models.Account.RelationTypeCompany.CompanyClientRelation;
import com.eVolGreen.eVolGreen.Models.Account.RelationTypeCompany.CompanyEmployeeRelation;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.eVolGreen.eVolGreen.Models.User.Role;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CompanyUserDTO {

    private long id;

    @NotBlank(message = "El nombre de la compañía no puede estar vacío")
    private String NombreCompañia;

    @NotBlank(message = "El RUT no puede estar vacío")
    private String Rut;

    @NotNull(message = "El email no puede ser nulo.")
    private String Email;

    @NotNull(message = "El password no puede ser nulo.")
    private String Password;

    @NotNull(message = "El Telefono no puede ser nulo.")
    private Integer telefono;

    private List<CompanyEmployeeRelationDTO> RelacionCompañiaEmpleado ;

    private List<CompanyClientRelationDTO> RelacionCompañiaCliente ;

    private List<FeeDTO> Tarifas;

    private List<AccountCompany> CuentaCompañia;

    private List<Long> CuentaPrincipal ;

    @NotNull(message = "El rol no puede ser nulo")
    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean Activo = false;

    public CompanyUserDTO() { }

    public CompanyUserDTO(CompanyUser Compañia) {

        id = Compañia.getId();
        Email = Compañia.getEmail();
        Password = Compañia.getPassword();
        telefono = Compañia.getTelefono();
        NombreCompañia = Compañia.getNombreCompañia();
        Rut = Compañia.getRut();
        RelacionCompañiaEmpleado = Compañia.getRelacionCompañiaEmpleado()
                .stream()
                .map(CompanyEmployeeRelationDTO -> new CompanyEmployeeRelationDTO(CompanyEmployeeRelationDTO))
                .collect(Collectors.toList());
        RelacionCompañiaCliente = Compañia.getRelacionCompañiaCliente()
                .stream()
                .map(CompanyClientRelationDTO -> new CompanyClientRelationDTO(CompanyClientRelationDTO))
                .collect(Collectors.toList());
        Tarifas = Compañia.getTarifas()
                .stream()
                .map(FeeDTO -> new FeeDTO(FeeDTO))
                .collect(Collectors.toList());
        CuentaCompañia = Compañia.getCuentaCompañia()
                .stream()
                .map(AccountCompany -> new AccountCompany())
                .collect(Collectors.toList());
        CuentaPrincipal = Compañia.getCuentaPrincipal()
                .stream()
                .map(AccountDTO -> new AccountDTO(AccountDTO).getId())
                .collect(Collectors.toList());
        role = Compañia.getRol();
        Activo = Compañia.getActivo();
    }

    public long getId() {
        return id;
    }

    public @NotBlank(message = "El nombre de la compañía no puede estar vacío") String getNombreCompañia() {
        return NombreCompañia;
    }

    public @NotBlank(message = "El RUT no puede estar vacío") String getRut() {
        return Rut;
    }

    public @NotNull(message = "El email no puede ser nulo.") String getEmail() {
        return Email;
    }

    public @NotNull(message = "El password no puede ser nulo.") String getPassword() {
        return Password;
    }

    public @NotNull(message = "El Telefono no puede ser nulo.") Integer getTelefono() {
        return telefono;
    }

    public List<CompanyEmployeeRelationDTO> getRelacionCompañiaEmpleado() {
        return RelacionCompañiaEmpleado;
    }

    public List<CompanyClientRelationDTO> getRelacionCompañiaCliente() {
        return RelacionCompañiaCliente;
    }

    public List<FeeDTO> getTarifas() {
        return Tarifas;
    }

    public List<AccountCompany> getCuentaCompañia() {
        return CuentaCompañia;
    }

    public List<Long> getCuentaPrincipal() {
        return CuentaPrincipal;
    }

    public @NotNull(message = "El rol no puede ser nulo") Role getRole() {
        return role;
    }

    public Boolean getActivo() {
        return Activo;
    }
}
