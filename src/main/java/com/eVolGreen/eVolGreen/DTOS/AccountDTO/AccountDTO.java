package com.eVolGreen.eVolGreen.DTOS.AccountDTO;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.TypeOfAccountDTO.AccountCompanyDTO;
import com.eVolGreen.eVolGreen.DTOS.UserDTO.AdminCompanyDTO;
import com.eVolGreen.eVolGreen.DTOS.UserDTO.CompanyDTO.CompanyUserDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.AdminCompanyUser;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDTO {

    private long id;

    @NotNull(message = "El número de cuenta no puede ser nulo.")
    private String NumeroDeCuenta;

    @NotBlank(message = "El nombre de la cuenta no puede estar vacío")
    private String NombreCuenta;

    @NotNull(message = "La fecha de creación no puede ser nula.")
    private LocalDate FechaDeCreacion;

    @NotNull(message = "El email no puede ser nulo.")
    private String Email;

    @NotNull(message = "El password no puede ser nulo.")
    private String Password;

    @NotNull(message = "El estado de la cuenta no puede ser nulo.")
    private Boolean Activo = false;

    private List<Long> CuentaCompañiaIds;

    private Long CompañiaId;

    private Long adminCompañiaId;

    public AccountDTO() { }

    public AccountDTO(Account account) {

        id = account.getId();
        NumeroDeCuenta = account.getNumeroDeCuenta();
        NombreCuenta = account.getNombreCuenta();
        FechaDeCreacion = account.getFechaDeCreacion();
        Email = account.getEmail();
        Password = account.getPassword();
        Activo = account.getActivo();
        CuentaCompañiaIds = account.getCuentaCompañia()
                .stream()
                .map(AccountCompanyDTO -> new AccountCompanyDTO(AccountCompanyDTO).getId())
                .collect(Collectors.toList());
        CompañiaId = account.getCompañia() != null ? account.getCompañia().getId() : null;
        adminCompañiaId = account.getAdminCompañia() != null ? account.getAdminCompañia().getId() : null;
    }

    public long getId() {
        return id;
    }

    public @NotNull(message = "El número de cuenta no puede ser nulo.") String getNumeroDeCuenta() {
        return NumeroDeCuenta;
    }

    public @NotBlank(message = "El nombre de la cuenta no puede estar vacío") String getNombreCuenta() {
        return NombreCuenta;
    }

    public @NotNull(message = "La fecha de creación no puede ser nula.") LocalDate getFechaDeCreacion() {
        return FechaDeCreacion;
    }

    public @NotNull(message = "El email no puede ser nulo.") String getEmail() {
        return Email;
    }

    public @NotNull(message = "El password no puede ser nulo.") String getPassword() {
        return Password;
    }

    public @NotNull(message = "El estado de la cuenta no puede ser nulo.") Boolean getActivo() {
        return Activo;
    }

    public List<Long> getCuentaCompañiaIds() {
        return CuentaCompañiaIds;
    }

    public Long getCompañiaId() {
        return CompañiaId;
    }

    public Long getAdminCompañiaId() {
        return adminCompañiaId;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "id=" + id +
                ", NumeroDeCuenta='" + NumeroDeCuenta + '\'' +
                ", NombreCuenta='" + NombreCuenta + '\'' +
                ", FechaDeCreacion=" + FechaDeCreacion +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", Activo=" + Activo +
                ", CuentaCompañiaIds=" + CuentaCompañiaIds +
                ", CompañiaId=" + CompañiaId +
                ", adminCompañiaId=" + adminCompañiaId +
                '}';
    }
}


