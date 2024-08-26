package com.eVolGreen.eVolGreen.Models.Account.Car;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Entity
public class DeviceIdentifier {

    @Id
    @GenericGenerator(name= "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long id;

    @NotNull(message = "El Nombre de Identificador es obligatorio")
    private String NombreDeIdentificador;

    @NotNull(message = "El RFID es obligatorio")
    private Integer RFID;

    @NotNull(message = "La fecha de expiración es obligatoria")
    private LocalDate fechaExpiracion;

    @NotNull(message = "El Auto es obligatorio")
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "Auto_id")
    @JsonBackReference ("Auto-RFID")
    private Car Auto;

    private Boolean activo = false;

    public DeviceIdentifier() { }

    public DeviceIdentifier(String NombreDeIdentificador, Integer RFID, LocalDate fechaExpiracion, Car Auto, Boolean activo) {
        this.NombreDeIdentificador = NombreDeIdentificador;
        this.RFID = RFID;
        this.fechaExpiracion = fechaExpiracion;
        this.Auto = Auto;
        this.activo = activo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotNull(message = "El Nombre de Identificador es obligatorio") String getNombreDeIdentificador() {
        return NombreDeIdentificador;
    }

    public void setNombreDeIdentificador(@NotNull(message = "El Nombre de Identificador es obligatorio") String nombreDeIdentificador) {
        NombreDeIdentificador = nombreDeIdentificador;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public @NotNull(message = "El RFID es obligatorio") Integer getRFID() {
        return RFID;
    }

    public void setRFID(@NotNull(message = "El RFID es obligatorio") Integer RFID) {
        this.RFID = RFID;
    }

    public @NotNull(message = "El Auto es obligatorio") Car getAuto() {
        return Auto;
    }

    public void setAuto(@NotNull(message = "El Auto es obligatorio") Car auto) {
        Auto = auto;
    }

    public @NotNull(message = "La fecha de expiración es obligatoria") LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(@NotNull(message = "La fecha de expiración es obligatoria") LocalDate fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    @Override
    public String toString() {
        return "DeviceIdentifier{" +
                "id=" + id +
                ", NombreDeIdentificador='" + NombreDeIdentificador + '\'' +
                ", RFID=" + RFID +
                ", Auto=" + Auto +
                '}';
    }
}