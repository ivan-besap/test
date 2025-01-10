package com.eVolGreen.eVolGreen.Models.Account.Car;

import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import javax.validation.constraints.NotNull;
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

    @NotNull(message = "La empresa no puede estar en nulo.")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empresa_id", nullable = false)
    @JsonBackReference("Empresa-RFID")
    private Empresa empresa;

    private Boolean activo = false;

    private Boolean usable = false;

    public DeviceIdentifier() { }

    public DeviceIdentifier(String NombreDeIdentificador, String RFID, LocalDate fechaExpiracion, Car auto, Boolean activo, Boolean usable) {
        this.NombreDeIdentificador = NombreDeIdentificador;
        this.RFID = RFID;
        this.fechaExpiracion = fechaExpiracion;
        this.activo = activo;
        this.usable = usable;
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean getUsable() {
        return usable;
    }

    public void setUsable(Boolean usable) {
        this.usable = usable;
    }

    @Override
    public String toString() {
        return "DeviceIdentifier{" +
                "id=" + id +
                ", NombreDeIdentificador='" + NombreDeIdentificador + '\'' +
                ", RFID='" + RFID + '\'' +
                ", fechaExpiracion=" + fechaExpiracion +
                ", auto=" + auto +
                ", empresa=" + empresa +
                ", activo=" + activo +
                ", usable=" + usable +
                '}';
    }
}