package com.eVolGreen.eVolGreen.Models.Account.Car;

import com.eVolGreen.eVolGreen.Models.Account.Account;
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
    private Long id;

    @NotNull(message = "El Nombre de Identificador es obligatorio")
    private String NombreDeIdentificador;

    @NotNull(message = "El RFID es obligatorio")
    private String RFID;

    @NotNull(message = "La fecha de expiración es obligatoria")
    private LocalDate fechaExpiracion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Auto_id")
    @JsonBackReference("Auto-RFID")
    private Car auto;

    @NotNull(message = "La cuenta no puede estar en nulo.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    @JsonBackReference("Account-RFID")
    private Account cuenta;

    private Boolean activo = false;

    public DeviceIdentifier() { }

    public DeviceIdentifier(String NombreDeIdentificador, String RFID, LocalDate fechaExpiracion, Car auto, Boolean activo) {
        this.NombreDeIdentificador = NombreDeIdentificador;
        this.RFID = RFID;
        this.fechaExpiracion = fechaExpiracion;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "El Nombre de Identificador es obligatorio") String getNombreDeIdentificador() {
        return NombreDeIdentificador;
    }

    public void setNombreDeIdentificador(@NotNull(message = "El Nombre de Identificador es obligatorio") String nombreDeIdentificador) {
        NombreDeIdentificador = nombreDeIdentificador;
    }

    public @NotNull(message = "El RFID es obligatorio") String getRFID() {
        return RFID;
    }

    public void setRFID(@NotNull(message = "El RFID es obligatorio") String RFID) {
        this.RFID = RFID;
    }

    public @NotNull(message = "La fecha de expiración es obligatoria") LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(@NotNull(message = "La fecha de expiración es obligatoria") LocalDate fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public Car getAuto() {
        return auto;
    }

    public void setAuto(Car auto) {
        this.auto = auto;
    }

    public @NotNull(message = "La cuenta no puede estar en nulo.") Account getCuenta() {
        return cuenta;
    }

    public void setCuenta(@NotNull(message = "La cuenta no puede estar en nulo.") Account cuenta) {
        this.cuenta = cuenta;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "DeviceIdentifier{" +
                "id=" + id +
                ", NombreDeIdentificador='" + NombreDeIdentificador + '\'' +
                ", RFID='" + RFID + '\'' +
                ", fechaExpiracion=" + fechaExpiracion +
                ", auto=" + auto +
                ", cuenta=" + cuenta +
                ", activo=" + activo +
                '}';
    }
}