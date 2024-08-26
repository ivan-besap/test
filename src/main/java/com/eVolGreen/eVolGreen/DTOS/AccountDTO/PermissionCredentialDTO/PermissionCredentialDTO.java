package com.eVolGreen.eVolGreen.DTOS.AccountDTO.PermissionCredentialDTO;

import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Credential;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Permission;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.PermissionCredential;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PermissionCredentialDTO {

    private long id;

    private List<Long> Permiso;

    private CredentialDTO Credencial;

    @NotNull(message = "La fecha de creacion es obligatoria")
    private LocalDateTime FechaCreacion;

    @NotNull(message = "La fecha de expiracion es obligatoria")
    private LocalDateTime FechaExpiracion;

    @NotNull(message = "El estado es obligatorio")
    private Boolean Estado;

    @NotNull(message = "El creador es obligatorio")
    private String CreadoPor;

    @Lob
    @Column(name = "historial_cambios")
    private String HistorialCambios;

    public PermissionCredentialDTO(PermissionCredential PermisoCredential) {

        id = PermisoCredential.getId();
        Permiso = PermisoCredential.getPermiso().stream().map(PermissionDTO -> new PermissionDTO(PermissionDTO).getId()).collect(Collectors.toList());
        Credencial = new CredentialDTO(PermisoCredential.getCredencial());
        FechaCreacion = PermisoCredential.getFechaCreacion();
        FechaExpiracion = PermisoCredential.getFechaExpiracion();
        Estado = PermisoCredential.getEstado();
        CreadoPor = PermisoCredential.getCreadoPor();
        HistorialCambios = PermisoCredential.getHistorialCambios();
    }

    public long getId() {
        return id;
    }

    public @NotNull(message = "El permiso es obligatorio") List<Long> getPermiso() {
        return Permiso;
    }

    public @NotNull(message = "La credencial es obligatoria") CredentialDTO getCredencial() {
        return Credencial;
    }

    public @NotNull(message = "La fecha de creacion es obligatoria") LocalDateTime getFechaCreacion() {
        return FechaCreacion;
    }

    public @NotNull(message = "La fecha de expiracion es obligatoria") LocalDateTime getFechaExpiracion() {
        return FechaExpiracion;
    }

    public @NotNull(message = "El estado es obligatorio") Boolean getEstado() {
        return Estado;
    }

    public @NotNull(message = "El creador es obligatorio") String getCreadoPor() {
        return CreadoPor;
    }

    public String getHistorialCambios() {
        return HistorialCambios;
    }
}
