package com.eVolGreen.eVolGreen.DTOS.AccountDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditLogDTO {

    private String email;
    private String nombre;
    private String apellidoPaterno;
    private String descripcion;
    private String fecha;

    public AuditLogDTO(String email, String nombre, String apellidoPaterno, String descripcion, LocalDateTime fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.email = email;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.descripcion = descripcion;
        this.fecha = fecha.format(formatter);
    }

    // Getters y setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getDescription() {
        return descripcion;
    }

    public void setDescription(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDate() {
        return fecha;
    }

    public void setDate(String fecha) {
        this.fecha = fecha;
    }
}
