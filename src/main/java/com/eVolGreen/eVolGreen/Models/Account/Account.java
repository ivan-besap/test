package com.eVolGreen.eVolGreen.Models.Account;

import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.AdminCompanyUser;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account{

    @Id
    @GenericGenerator(name= "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long id;

    @NotNull(message = "El número de cuenta no puede ser nulo.")
    private String numeroDeCuenta;

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

    @OneToMany(mappedBy = "cuentaPrincipal", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference ("CuentaPrincipal-CuentaCompañia")
    private Set<AccountCompany> cuentaCompañia = new HashSet<>();


    //revizar
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compañia_id")
    @JsonIgnore
    private CompanyUser Compañia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adminCompañia_id")
    @JsonIgnore
    private AdminCompanyUser adminCompañia;

    public Account() {}

    public Account(String numeroDeCuenta, String nombreCuenta, LocalDate fechaDeCreacion, String email, String password, CompanyUser compañia, AdminCompanyUser adminCompañia) {
        this.numeroDeCuenta = numeroDeCuenta;
        this.NombreCuenta = nombreCuenta;
        this.FechaDeCreacion = fechaDeCreacion;
        this.Email = email;
        this.Password = password;
        this.Compañia = compañia;
        this.adminCompañia = adminCompañia;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotNull(message = "El número de cuenta no puede ser nulo.") String getNumeroDeCuenta() {
        return numeroDeCuenta;
    }

    public void setNumeroDeCuenta(@NotNull(message = "El número de cuenta no puede ser nulo.") String numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
    }

    public @NotBlank(message = "El nombre de la cuenta no puede estar vacío") String getNombreCuenta() {
        return NombreCuenta;
    }

    public void setNombreCuenta(@NotBlank(message = "El nombre de la cuenta no puede estar vacío") String nombreCuenta) {
        NombreCuenta = nombreCuenta;
    }

    public @NotNull(message = "La fecha de creación no puede ser nula.") LocalDate getFechaDeCreacion() {
        return FechaDeCreacion;
    }

    public void setFechaDeCreacion(@NotNull(message = "La fecha de creación no puede ser nula.") LocalDate fechaDeCreacion) {
        FechaDeCreacion = fechaDeCreacion;
    }

    public @NotNull(message = "El email no puede ser nulo.") String getEmail() {
        return Email;
    }

    public void setEmail(@NotNull(message = "El email no puede ser nulo.") String email) {
        Email = email;
    }

    public @NotNull(message = "El password no puede ser nulo.") String getPassword() {
        return Password;
    }

    public void setPassword(@NotNull(message = "El password no puede ser nulo.") String password) {
        Password = password;
    }

    public @NotNull(message = "El estado de la cuenta no puede ser nulo.") Boolean getActivo() {
        return Activo;
    }

    public void setActivo(@NotNull(message = "El estado de la cuenta no puede ser nulo.") Boolean activo) {
        Activo = activo;
    }

    public Set<AccountCompany> getCuentaCompañia() {
        return cuentaCompañia;
    }

    public void setCuentaCompañia(Set<AccountCompany> cuentaCompañia) {
        this.cuentaCompañia = cuentaCompañia;
    }

    public CompanyUser getCompañia() {
        return Compañia;
    }

    public void setCompañia(CompanyUser compañia) {
        Compañia = compañia;
    }

    public AdminCompanyUser getAdminCompañia() {
        return adminCompañia;
    }

    public void setAdminCompañia(AdminCompanyUser adminCompañia) {
        this.adminCompañia = adminCompañia;
    }


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", numeroDeCuenta='" + numeroDeCuenta + '\'' +
                ", NombreCuenta='" + NombreCuenta + '\'' +
                ", FechaDeCreacion=" + FechaDeCreacion +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", Activo=" + Activo +
                ", cuentaCompañia=" + cuentaCompañia +
                ", Compañia=" + Compañia +
                '}';
    }
}
