package com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO;

import com.eVolGreen.eVolGreen.Models.Account.Car.DeviceIdentifier;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class NewDeviceIdentifierDTO {

    @NotNull(message = "El nombre de identificador es obligatorio")
    private String nombreDeIdentificador;

    @NotNull(message = "El RFID es obligatorio")
    private Integer rfid;

    @NotNull(message = "La fecha de expiración es obligatoria")
    private LocalDate fechaExpiracion;

    private long auto;

    private Boolean activo = false;

    public NewDeviceIdentifierDTO() { }

    public NewDeviceIdentifierDTO(DeviceIdentifier deviceIdentifier) {
        nombreDeIdentificador = deviceIdentifier.getNombreDeIdentificador();
        rfid = deviceIdentifier.getRFID();
        fechaExpiracion = deviceIdentifier.getFechaExpiracion();
        auto = deviceIdentifier.getAuto().getId();
        activo = deviceIdentifier.getActivo();
    }

    public @NotNull(message = "El nombre de identificador es obligatorio") String getNombreDeIdentificador() {
        return nombreDeIdentificador;
    }

    public Boolean getActivo() {
        return activo;
    }

    public @NotNull(message = "El RFID es obligatorio") Integer getRfid() {
        return rfid;
    }

    public @NotNull(message = "La fecha de expiración es obligatoria") LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public long getAuto() {
        return auto;
    }
}
