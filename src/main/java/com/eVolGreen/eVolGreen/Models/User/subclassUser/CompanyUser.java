package com.eVolGreen.eVolGreen.Models.User.subclassUser;

import com.eVolGreen.eVolGreen.Models.User.Role;
import com.eVolGreen.eVolGreen.Models.Account.RelationTypeCompany.CompanyClientRelation;
import com.eVolGreen.eVolGreen.Models.Account.RelationTypeCompany.CompanyEmployeeRelation;
import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
public class CompanyUser implements UserDetails {

    @Id
    @GenericGenerator(name= "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    @NotBlank(message = "El número de teléfono no puede estar vacío")
    private Integer telefono;

    @NotBlank(message = "El nombre de la compañía no puede estar vacío")
    private String nombreCompañia;

    private String rut;

    @OneToMany(mappedBy = "Compañia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("Compañia-RelacionCompañiaEmpleado")
    private Set<CompanyEmployeeRelation> RelacionCompañiaEmpleado = new HashSet<>();

    @OneToMany(mappedBy = "Compañia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("Compañia-RelacionCompañiaCliente")
    private Set<CompanyClientRelation> RelacionCompañiaCliente = new HashSet<>();

    @OneToMany(mappedBy = "Compañia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("Compañia-Tarifa")
    private Set<Fee> Tarifas = new HashSet<>();

    @OneToMany(mappedBy = "Compañia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("Compañia-CuentaCompañia")
    private Set<AccountCompany> CuentaCompañia = new HashSet<>();

    @OneToMany(mappedBy = "Compañia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("CuentaPrincipal-Compañia")
    private Set<Account> CuentaPrincipal = new HashSet<>();

    @NotNull(message = "El rol no puede ser nulo")
    @Enumerated(EnumType.STRING)
    private Role rol = Role.COMPANY;

    private Boolean activo = false;

    public CompanyUser() { }

    public CompanyUser(String email, String password, Integer telefono, String nombreCompañia, String rut, Role rol,Boolean activo) {
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.nombreCompañia = nombreCompañia;
        this.rut = rut;
        this.rol = rol;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public @NotBlank(message = "El número de teléfono no puede estar vacío") Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(@NotBlank(message = "El número de teléfono no puede estar vacío") Integer telefono) {
        this.telefono = telefono;
    }

    public @NotBlank(message = "El nombre de la compañía no puede estar vacío") String getNombreCompañia() {
        return nombreCompañia;
    }

    public void setNombreCompañia(@NotBlank(message = "El nombre de la compañía no puede estar vacío") String nombreCompañia) {
        this.nombreCompañia = nombreCompañia;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public Set<CompanyEmployeeRelation> getRelacionCompañiaEmpleado() {
        return RelacionCompañiaEmpleado;
    }

    public void setRelacionCompañiaEmpleado(Set<CompanyEmployeeRelation> relacionCompañiaEmpleado) {
        RelacionCompañiaEmpleado = relacionCompañiaEmpleado;
    }

    public Set<CompanyClientRelation> getRelacionCompañiaCliente() {
        return RelacionCompañiaCliente;
    }

    public void setRelacionCompañiaCliente(Set<CompanyClientRelation> relacionCompañiaCliente) {
        RelacionCompañiaCliente = relacionCompañiaCliente;
    }

    public Set<Fee> getTarifas() {
        return Tarifas;
    }

    public void setTarifas(Set<Fee> tarifas) {
        Tarifas = tarifas;
    }

    public Set<AccountCompany> getCuentaCompañia() {
        return CuentaCompañia;
    }

    public void setCuentaCompañia(Set<AccountCompany> cuentaCompañia) {
        CuentaCompañia = cuentaCompañia;
    }

    public Set<Account> getCuentaPrincipal() {
        return CuentaPrincipal;
    }

    public void setCuentaPrincipal(Set<Account> cuentaPrincipal) {
        CuentaPrincipal = cuentaPrincipal;
    }

    public @NotNull(message = "El rol no puede ser nulo") Role getRol() {
        return rol;
    }

    public void setRol(@NotNull(message = "El rol no puede ser nulo") Role rol) {
        this.rol = rol;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "CompanyUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", telefono=" + telefono +
                ", nombreCompañia='" + nombreCompañia + '\'' +
                ", rut='" + rut + '\'' +
                ", RelacionCompañiaEmpleado=" + RelacionCompañiaEmpleado +
                ", RelacionCompañiaCliente=" + RelacionCompañiaCliente +
                ", Tarifas=" + Tarifas +
                ", CuentaCompañia=" + CuentaCompañia +
                ", CuentaPrincipal=" + CuentaPrincipal +
                ", rol=" + rol +
                ", activo=" + activo +
                '}';
    }
}
