package com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO;

import com.eVolGreen.eVolGreen.Models.Account.Car.Car;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class CarClientDTO {

    private long id;

    @NotNull(message = "La Patente es obligatoria")
    private String Patente;

    @NotNull(message = "El modelo es obligatorio")
    private String Modelo;

    @NotNull(message = "El VIN es obligatorio")
    private String vin;

    @NotNull(message = "El Color es obligatorio")
    private String Color;

    @NotNull(message = "La Marca es obligatoria")
    private String Marca;

    @NotNull(message = "El Año de Fabricacion es obligatorio")
    private String AñoFabricacion;

    @NotNull(message = "La Capacidad de Potencia es obligatoria")
    private BigDecimal CapacidadPotencia;

    private Set<DeviceIdentifierDTO> RFID;

    private long CuentaCliente;

    public CarClientDTO(Car Auto) {

        id = Auto.getId();
        Patente = Auto.getPatente();
        Modelo = Auto.getModelo();
        vin = Auto.getVin();
        Color = Auto.getColor();
        Marca = Auto.getMarca();
        AñoFabricacion = Auto.getAñoFabricacion();
        CapacidadPotencia = Auto.getCapacidadPotencia();
        RFID = Auto.getRFID().stream()
                .map(DeviceIdentifierDTO::new)
                .collect(Collectors.toSet());
        CuentaCliente = Auto.getCuentaCliente().getId();
    }

    public long getId() {
        return id;
    }

    public @NotNull(message = "La Patente es obligatoria") String getPatente() {
        return Patente;
    }

    public @NotNull(message = "El modelo es obligatorio") String getModelo() {
        return Modelo;
    }

    public @NotNull(message = "El VIN es obligatorio") String getVIN() {
        return vin;
    }

    public @NotNull(message = "El Color es obligatorio") String getColor() {
        return Color;
    }

    public @NotNull(message = "La Marca es obligatoria") String getMarca() {
        return Marca;
    }

    public @NotNull(message = "El Año de Fabricacion es obligatorio") String getAñoFabricacion() {
        return AñoFabricacion;
    }

    public @NotNull(message = "La Capacidad de Potencia es obligatoria") BigDecimal getCapacidadPotencia() {
        return CapacidadPotencia;
    }

    public Set<DeviceIdentifierDTO> getRFID() {
        return RFID;
    }

    public long getCuentaCliente() {
        return CuentaCliente;
    }

    @Override
    public String toString() {
        return "CarClientDTO{" +
                "id=" + id +
                ", Patente='" + Patente + '\'' +
                ", Modelo='" + Modelo + '\'' +
                ", VIN='" + vin + '\'' +
                ", Color='" + Color + '\'' +
                ", Marca='" + Marca + '\'' +
                ", AñoFabricacion='" + AñoFabricacion + '\'' +
                ", CapacidadPotencia=" + CapacidadPotencia +
                ", RFID=" + RFID +
                ", CuentaCliente=" + CuentaCliente +
                '}';
    }
}
