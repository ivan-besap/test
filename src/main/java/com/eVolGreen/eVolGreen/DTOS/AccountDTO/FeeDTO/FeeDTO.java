package com.eVolGreen.eVolGreen.DTOS.AccountDTO.FeeDTO;

import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;
import com.eVolGreen.eVolGreen.Models.Account.Fee.FeeConnector;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public class FeeDTO {

    private long id;

    @NotNull(message = "El nombre de la tarifa es obligatorio")
    private String NombreTarifa;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate FechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    private LocalDate FechaFin;

    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalTime HoraInicio;

    @NotNull(message = "La hora de fin es obligatoria")
    private LocalTime HoraFin;

    @NotNull(message = "Los dias de la semana son obligatorios")
    private Set<String> DiasDeLaSemana;

    @NotNull(message = "El precio de la tarifa es obligatorio")
    private BigDecimal PrecioTarifa;

    private Set<FeeConnector> TarifaConector;

    private long CuentaCliente;

    private long CuentaCompañia;

    private long CuentaTrabajador;

    private String Compañia;

    private Boolean activo = false;

    public FeeDTO() {}

    public FeeDTO( Fee Tarifa) {

        id = Tarifa.getId();
        NombreTarifa = Tarifa.getNombreTarifa();
        FechaInicio = Tarifa.getFechaInicio();
        FechaFin = Tarifa.getFechaFin();
        HoraInicio = Tarifa.getHoraInicio();
        HoraFin = Tarifa.getHoraFin();
        DiasDeLaSemana = Tarifa.getDiasDeLaSemana();
        PrecioTarifa = Tarifa.getPrecioTarifa();
        TarifaConector = Tarifa.getTarifaConector();
        CuentaCliente = Tarifa.getCuentaCliente().getId();
        CuentaCompañia = Tarifa.getCuentaCompañia().getId();
        CuentaTrabajador = Tarifa.getCuentaTrabajador().getId();
        Compañia = Tarifa.getCuentaCompañia().getNombreCompañia();
        activo = Tarifa.getActivo();

    }

    public long getId() {
        return id;
    }

    public Boolean getActivo() {
        return activo;
    }

    public @NotNull(message = "El nombre de la tarifa es obligatorio") String getNombreTarifa() {
        return NombreTarifa;
    }

    public @NotNull(message = "La fecha de inicio es obligatoria") LocalDate getFechaInicio() {
        return FechaInicio;
    }

    public @NotNull(message = "La fecha de fin es obligatoria") LocalDate getFechaFin() {
        return FechaFin;
    }

    public @NotNull(message = "La hora de inicio es obligatoria") LocalTime getHoraInicio() {
        return HoraInicio;
    }

    public @NotNull(message = "La hora de fin es obligatoria") LocalTime getHoraFin() {
        return HoraFin;
    }

    public @NotNull(message = "Los dias de la semana son obligatorios") Set<String> getDiasDeLaSemana() {
        return DiasDeLaSemana;
    }

    public @NotNull(message = "El precio de la tarifa es obligatorio") BigDecimal getPrecioTarifa() {
        return PrecioTarifa;
    }

    public Set<FeeConnector> getTarifaConector() {
        return TarifaConector;
    }

    public long getCuentaCliente() {
        return CuentaCliente;
    }

    public long getCuentaCompañia() {
        return CuentaCompañia;
    }

    public long getCuentaTrabajador() {
        return CuentaTrabajador;
    }

    public String getCompañia() {
        return Compañia;
    }

    @Override
    public String toString() {
        return "FeeDTO{" +
                "id=" + id +
                ", NombreTarifa='" + NombreTarifa + '\'' +
                ", FechaInicio=" + FechaInicio +
                ", FechaFin=" + FechaFin +
                ", HoraInicio=" + HoraInicio +
                ", HoraFin=" + HoraFin +
                ", DiasDeLaSemana=" + DiasDeLaSemana +
                ", PrecioTarifa=" + PrecioTarifa +
                ", TarifaConector=" + TarifaConector +
                ", CuentaCliente=" + CuentaCliente +
                ", CuentaCompañia=" + CuentaCompañia +
                ", CuentaTrabajador=" + CuentaTrabajador +
                ", Compañia='" + Compañia + '\'' +
                '}';
    }
}
