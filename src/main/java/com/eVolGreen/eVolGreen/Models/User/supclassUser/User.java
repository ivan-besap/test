//package com.eVolGreen.eVolGreen.Models.User.supclassUser;
//
//import com.eVolGreen.eVolGreen.Models.User.Role;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import org.hibernate.annotations.GenericGenerator;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.time.LocalDate;
//import java.util.Collection;
//import java.util.List;
//
//@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
//public class User implements UserDetails {
//
//    @Id
//    @GenericGenerator(name= "native", strategy = "native")
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
//    private Long id;
//
//    @Column(unique = true)
//    private String email;
//
//    private String password;
//
//    @NotBlank(message = "El número de teléfono no puede estar vacío")
//    private Integer telefono;
//
//    @NotNull(message = "La Fecha de Creacion es obligatoria")
//    private LocalDate FechaCreacion;
//
//    @Enumerated(EnumType.STRING)
//    private Role Rol;
//
//    public User() { }
//
//    public User(String email, String password, Role Rol, Integer telefono) {
//        this.email = email;
//        this.password = password;
//        this.FechaCreacion = LocalDate.now();
//        this.Rol = Rol;
//        this.telefono = telefono;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public @NotNull(message = "El Email es obligatorio") String getEmail() {
//        return email;
//    }
//
//    public void setEmail(@NotNull(message = "El Email es obligatorio") String email) {
//        email = email;
//    }
//
//    public void setPassword(@NotNull(message = "La Contraseña es obligatoria") String password) {
//        password = password;
//    }
//
//    public Role getRol() {
//        return Rol;
//    }
//
//    public void setRol(Role rol) {
//        Rol = rol;
//    }
//
//    public @NotNull(message = "La Fecha de Creacion es obligatoria") LocalDate getFechaCreacion() {
//        return FechaCreacion;
//    }
//
//    public void setFechaCreacion(@NotNull(message = "La Fecha de Creacion es obligatoria") LocalDate fechaCreacion) {
//        FechaCreacion = fechaCreacion;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(Rol.name()));
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    public @NotBlank(message = "El número de teléfono no puede estar vacío") Integer getTelefono() {
//        return telefono;
//    }
//
//    public void setTelefono(@NotBlank(message = "El número de teléfono no puede estar vacío") Integer telefono) {
//        this.telefono = telefono;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                ", telefono=" + telefono +
//                ", FechaCreacion=" + FechaCreacion +
//                ", Rol=" + Rol +
//                '}';
//    }
//}
