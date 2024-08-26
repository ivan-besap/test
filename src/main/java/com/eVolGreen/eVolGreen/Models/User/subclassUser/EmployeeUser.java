package com.eVolGreen.eVolGreen.Models.User.subclassUser;

import com.eVolGreen.eVolGreen.Models.User.Role;
import com.eVolGreen.eVolGreen.Models.Account.RelationTypeCompany.CompanyEmployeeRelation;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountEmployee;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class EmployeeUser implements UserDetails {

    @Id
    @GenericGenerator(name= "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    @NotBlank(message = "El número de teléfono no puede estar vacío")
    private Integer telefono;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El apellido paterno no puede estar vacío")
    private String apellidoPaterno;

    @NotBlank(message = "El apellido materno no puede estar vacío")
    private String apellidoMaterno;

    @OneToMany(mappedBy = "Trabajador", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("Trabajador-RelacionCompañiaEmpleado")
    private Set<CompanyEmployeeRelation> RelacionCompañiaEmpleado = new HashSet<>();

    @OneToMany(mappedBy = "Trabajador", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("Trabajador-CuentaTrabajador")
    private Set<AccountEmployee> CuentaTrabajador = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Compañia_id")
    @JsonBackReference("Trabajador-CuentaCompañia")
    private AccountCompany CuentaCompañia;

    @NotNull(message = "El rol no puede ser nulo")
    @Enumerated(EnumType.STRING)
    private Role rol = Role.EMPLOYEE;

    private Boolean activo = false;

    public EmployeeUser() {}

    public EmployeeUser(String email, String password, Integer telefono, String nombre, String apellidoPaterno, String apellidoMaterno, Role rol, Boolean activo) {
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
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

    public @NotBlank(message = "El nombre no puede estar vacío") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "El nombre no puede estar vacío") String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank(message = "El apellido paterno no puede estar vacío") String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(@NotBlank(message = "El apellido paterno no puede estar vacío") String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public @NotBlank(message = "El apellido materno no puede estar vacío") String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(@NotBlank(message = "El apellido materno no puede estar vacío") String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Set<CompanyEmployeeRelation> getRelacionCompañiaEmpleado() {
        return RelacionCompañiaEmpleado;
    }

    public void setRelacionCompañiaEmpleado(Set<CompanyEmployeeRelation> relacionCompañiaEmpleado) {
        RelacionCompañiaEmpleado = relacionCompañiaEmpleado;
    }

    public Set<AccountEmployee> getCuentaTrabajador() {
        return CuentaTrabajador;
    }

    public void setCuentaTrabajador(Set<AccountEmployee> cuentaTrabajador) {
        CuentaTrabajador = cuentaTrabajador;
    }

    public AccountCompany getCuentaCompañia() {
        return CuentaCompañia;
    }

    public void setCuentaCompañia(AccountCompany cuentaCompañia) {
        CuentaCompañia = cuentaCompañia;
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
        return "EmployeeUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", telefono=" + telefono +
                ", nombre='" + nombre + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", RelacionCompañiaEmpleado=" + RelacionCompañiaEmpleado +
                ", CuentaTrabajador=" + CuentaTrabajador +
                ", CuentaCompañia=" + CuentaCompañia +
                ", rol=" + rol +
                ", activo=" + activo +
                '}';
    }
}
