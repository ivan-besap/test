package com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO;

import com.eVolGreen.eVolGreen.Models.Account.Car.DeviceIdentifier;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class DeviceIdentifierDTO {

    private long id;

    @NotNull(message = "El nombre de identificador es obligatorio")
    private String nombreDeIdentificador;

    @NotNull(message = "El RFID es obligatorio")
    private Integer rfid;

    @NotNull(message = "La fecha de expiración es obligatoria")
    private LocalDate fechaExpiracion;

    private long auto;

    private Boolean activo = false;

    public DeviceIdentifierDTO(DeviceIdentifier deviceIdentifier) {
        id = deviceIdentifier.getId();
        nombreDeIdentificador = deviceIdentifier.getNombreDeIdentificador();
        rfid = deviceIdentifier.getRFID();
        fechaExpiracion = deviceIdentifier.getFechaExpiracion();
        auto = deviceIdentifier.getAuto().getId();
        activo = deviceIdentifier.getActivo();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreDeIdentificador() {
        return nombreDeIdentificador;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setNombreDeIdentificador(String nombreDeIdentificador) {
        this.nombreDeIdentificador = nombreDeIdentificador;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDate fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public Integer getRfid() {
        return rfid;
    }

    public void setRfid(Integer rfid) {
        this.rfid = rfid;
    }

    public long getAuto() {
        return auto;
    }

    public void setAuto(long auto) {
        this.auto = auto;
    }

    @Override
    public String toString() {
        return "DeviceIdentifierDTO{" +
                "id=" + id +
                ", nombreDeIdentificador='" + nombreDeIdentificador + '\'' +
                ", rfid=" + rfid +
                ", auto=" + auto +
                '}';
    }
}
