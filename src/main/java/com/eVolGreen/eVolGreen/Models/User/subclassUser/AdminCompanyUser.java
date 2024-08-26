package com.eVolGreen.eVolGreen.Models.User.subclassUser;

import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.User.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class AdminCompanyUser implements UserDetails {

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

    @NotBlank(message = "El RUT no puede estar vacío")
    private String rut;

    @OneToMany(mappedBy = "adminCompañia")
    @JsonManagedReference("cuentaPrincipal-adminCompañia")
    private Set<Account> cuentaPrincipal = new HashSet<>();

    @NotNull(message = "El rol no puede ser nulo")
    @Enumerated(EnumType.STRING)
    private Role rol = Role.ADMIN_COMPANY;

    @NotNull(message = "El estado de la cuenta no puede ser nulo.")
    private Boolean activo = false;

    public AdminCompanyUser() { }

    public AdminCompanyUser(String email, String password, Integer telefono, String nombreCompañia, String rut, Role rol, Boolean activo) {
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

    public @NotBlank(message = "El RUT no puede estar vacío") String getRut() {
        return rut;
    }

    public void setRut(@NotBlank(message = "El RUT no puede estar vacío") String rut) {
        this.rut = rut;
    }

    public Set<Account> getCuentaPrincipal() {
        return cuentaPrincipal;
    }

    public void setCuentaPrincipal(Set<Account> cuentaPrincipal) {
        this.cuentaPrincipal = cuentaPrincipal;
    }

    public @NotNull(message = "El rol no puede ser nulo") Role getRol() {
        return rol;
    }

    public void setRol(@NotNull(message = "El rol no puede ser nulo") Role rol) {
        this.rol = rol;
    }

    public @NotNull(message = "El estado de la cuenta no puede ser nulo.") Boolean getActivo() {
        return activo;
    }

    public void setActivo(@NotNull(message = "El estado de la cuenta no puede ser nulo.") Boolean activo) {
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "AdminCompanyUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", telefono=" + telefono +
                ", nombreCompañia='" + nombreCompañia + '\'' +
                ", rut='" + rut + '\'' +
                ", cuentaPrincipal=" + cuentaPrincipal +
                ", rol=" + rol +
                ", activo=" + activo +
                '}';
    }
}
