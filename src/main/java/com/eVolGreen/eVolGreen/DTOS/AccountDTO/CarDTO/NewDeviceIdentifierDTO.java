package com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO;

import com.eVolGreen.eVolGreen.Models.Account.Car.DeviceIdentifier;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class NewDeviceIdentifierDTO {

    @NotNull(message = "El nombre de identificador es obligatorio")
    private String nombreDeIdentificador;

    @NotNull(message = "El RFID es obligatorio")
    private String rfid;

    @NotNull(message = "La fecha de expiración es obligatoria")
    private LocalDate fechaExpiracion;

    private Long auto;

    private Long cuenta;

    public NewDeviceIdentifierDTO() { }

    public NewDeviceIdentifierDTO(DeviceIdentifier deviceIdentifier) {
        nombreDeIdentificador = deviceIdentifier.getNombreDeIdentificador();
        rfid = deviceIdentifier.getRFID();
        fechaExpiracion = deviceIdentifier.getFechaExpiracion();
        auto = deviceIdentifier.getAuto().getId();
        cuenta = deviceIdentifier.getCuenta().getId();
    }

    public @NotNull(message = "El nombre de identificador es obligatorio") String getNombreDeIdentificador() {
        return nombreDeIdentificador;
    }

    public @NotNull(message = "El RFID es obligatorio") String getRfid() {
        return rfid;
    }

    public @NotNull(message = "La fecha de expiración es obligatoria") LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public Long getAuto() {
        return auto;
    }

    public Long getCuenta() {
        return cuenta;
    }

    @Override
    public String toString() {
        return "NewDeviceIdentifierDTO{" +
                "nombreDeIdentificador='" + nombreDeIdentificador + '\'' +
                ", rfid='" + rfid + '\'' +
                ", fechaExpiracion=" + fechaExpiracion +
                ", auto=" + auto +
                ", cuenta=" + cuenta +
                '}';
    }
}
