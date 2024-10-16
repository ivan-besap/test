package com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO;

import com.eVolGreen.eVolGreen.Models.Account.Car.DeviceIdentifier;


import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class DeviceIdentifierDTO {

    private Long id;

    @NotNull(message = "El Nombre de Identificador es obligatorio")
    private String nombreDeIdentificador;

    @NotNull(message = "El RFID es obligatorio")
    private String RFID;

    @NotNull(message = "La fecha de expiración es obligatoria")
    private LocalDate fechaExpiracion;

    private Long auto;

    private Long empresa;

    private Boolean activo = false;

    public DeviceIdentifierDTO(DeviceIdentifier deviceIdentifier) {
        id = deviceIdentifier.getId();
        nombreDeIdentificador = deviceIdentifier.getNombreDeIdentificador();
        RFID = deviceIdentifier.getRFID();
        fechaExpiracion = deviceIdentifier.getFechaExpiracion();
        this.empresa = deviceIdentifier.getEmpresa().getId();
        auto = deviceIdentifier.getAuto() != null ? deviceIdentifier.getAuto().getId() : null;
        activo = deviceIdentifier.getActivo();
    }

    public Long getId() {
        return id;
    }

    public @NotNull(message = "El Nombre de Identificador es obligatorio") String getNombreDeIdentificador() {
        return nombreDeIdentificador;
    }

    public @NotNull(message = "El RFID es obligatorio") String getRFID() {
        return RFID;
    }

    public @NotNull(message = "La fecha de expiración es obligatoria") LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public Long getAuto() {
        return auto;
    }

    public Long getEmpresa() {
        return empresa;
    }

    public Boolean getActivo() {
        return activo;
    }

    @Override
    public String toString() {
        return "DeviceIdentifierDTO{" +
                "id=" + id +
                ", nombreDeIdentificador='" + nombreDeIdentificador + '\'' +
                ", RFID='" + RFID + '\'' +
                ", fechaExpiracion=" + fechaExpiracion +
                ", auto=" + auto +
                ", empresa=" + empresa +
                ", activo=" + activo +
                '}';
    }
}
