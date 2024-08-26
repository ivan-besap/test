package com.eVolGreen.eVolGreen.Models.Account.PermissionCredential;

import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PermissionCredential {

    @Id
    @GenericGenerator(name= "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonManagedReference("Permiso-PermisonCredencial")
    private Set<Permission> permiso = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credencial_id")
    @JsonBackReference("Credencial-PermisonCredencial")
    private Credential credencial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuentaCompañia_id")
    @JsonBackReference("cuentaCompañia-PermisonCredencial")
    private AccountCompany cuentaCompañia;

    @NotNull(message = "La fecha de creacion es obligatoria")
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime FechaCreacion = LocalDateTime.now();

    @NotNull(message = "La fecha de expiracion es obligatoria")
    @Column(name = "fecha_expiracion")
    private LocalDateTime FechaExpiracion;

    private Boolean Estado = true;

    @NotNull(message = "El creador es obligatorio")
    @Column(name = "creado_por")
    private String CreadoPor;

    @Lob
    @Column(name = "historial_cambios")
    private String HistorialCambios;

    public PermissionCredential() { }

    public PermissionCredential(Credential credencial, LocalDateTime fechaCreacion, LocalDateTime fechaExpiracion, Boolean estado, String creadoPor, String historialCambios) {
        this.credencial = credencial;
        this.FechaCreacion = fechaCreacion;
        this.FechaExpiracion = fechaExpiracion;
        this.Estado = estado;
        this.CreadoPor = creadoPor;
        this.HistorialCambios = historialCambios;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Permission> getPermiso() {
        return permiso;
    }

    public void setPermiso(Set<Permission> permiso) {
        this.permiso = permiso;
    }

    public Credential getCredencial() {
        return credencial;
    }

    public void setCredencial(Credential credencial) {
        this.credencial = credencial;
    }

    public @NotNull(message = "La credencial es obligatoria") AccountCompany getCuentaCompañia() {
        return cuentaCompañia;
    }

    public void setCuentaCompañia(@NotNull(message = "La credencial es obligatoria") AccountCompany cuentaCompañia) {
        this.cuentaCompañia = cuentaCompañia;
    }

    public @NotNull(message = "La fecha de creacion es obligatoria") LocalDateTime getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(@NotNull(message = "La fecha de creacion es obligatoria") LocalDateTime fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }

    public @NotNull(message = "La fecha de expiracion es obligatoria") LocalDateTime getFechaExpiracion() {
        return FechaExpiracion;
    }

    public void setFechaExpiracion(@NotNull(message = "La fecha de expiracion es obligatoria") LocalDateTime fechaExpiracion) {
        FechaExpiracion = fechaExpiracion;
    }

    public Boolean getEstado() {
        return Estado;
    }

    public void setEstado(Boolean estado) {
        Estado = estado;
    }

    public @NotNull(message = "El creador es obligatorio") String getCreadoPor() {
        return CreadoPor;
    }

    public void setCreadoPor(@NotNull(message = "El creador es obligatorio") String creadoPor) {
        CreadoPor = creadoPor;
    }

    public String getHistorialCambios() {
        return HistorialCambios;
    }

    public void setHistorialCambios(String historialCambios) {
        HistorialCambios = historialCambios;
    }

    public void addPermission(Permission permission) {
        this.permiso.add(permission);
        permission.setPermisonCredencial(this);
    }
}
