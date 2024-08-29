package com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO;

import com.eVolGreen.eVolGreen.Models.Account.Fee.FeeConnector;
import com.eVolGreen.eVolGreen.Models.Account.Reservation;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.Connector;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.ConnectorStatus;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.TypeConnector;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ConnectorDTO {

    private long id;

    @NotNull(message = "El Alias es obligatorio")
    private String Alias;

    @NotNull(message = "El Tipo de Conector es obligatorio")
    private TypeConnector TipoConector;

    @NotNull(message = "El N° de Conector es obligatorio")
    private String NConector;

    @NotNull(message = "El Voltaje Maximo es obligatorio")
    @Column(name = "Voltaje_Maximo")
    private BigDecimal VoltajeMaximo;

    @NotNull(message = "La Potencia Maxima es obligatoria")
    @Column(name = "Potencia_Maxima")
    private BigDecimal PotenciaMaxima;

    @NotNull(message = "La Corriente Maxima es obligatoria")
    @Column(name = "Corriente_Maxima")
    private BigDecimal CorrienteMaxima;

    @Enumerated(EnumType.STRING)
    private ConnectorStatus EstadoConector;

    private Long Cargador;

    private Long Terminal;

    private Set<Reservation> Reservaciones;

    private Set<FeeConnector> TarifaConector;

    private String IdCargador;

    public ConnectorDTO(Connector Conector) {

        id = Conector.getId();
        Alias = Conector.getAlias();
        NConector = Conector.getNConector();
        VoltajeMaximo = Conector.getVoltajeMaximo();
        PotenciaMaxima = Conector.getPotenciaMaxima();
        CorrienteMaxima = Conector.getCorrienteMaxima();
        EstadoConector = Conector.getEstadoConector();
        Cargador = Conector.getCargador().getId();
        TipoConector = Conector.getTipoConector();
        Terminal = Conector.getTerminal().getId();
        Reservaciones = Conector.getReservaciones();
        TarifaConector = Conector.getTarifaConector();
        IdCargador = Conector.getCargador().getoCPPid();
    }

    public long getId() {
        return id;
    }

    public @NotNull(message = "El Alias es obligatorio") String getAlias() {
        return Alias;
    }

    public @NotNull(message = "El Tipo de Conector es obligatorio") TypeConnector getTipoConector() {
        return TipoConector;
    }

    public @NotNull(message = "El N° de Conector es obligatorio") String getNConector() {
        return NConector;
    }

    public @NotNull(message = "El Voltaje Maximo es obligatorio") BigDecimal getVoltajeMaximo() {
        return VoltajeMaximo;
    }

    public @NotNull(message = "La Potencia Maxima es obligatoria") BigDecimal getPotenciaMaxima() {
        return PotenciaMaxima;
    }

    public @NotNull(message = "La Corriente Maxima es obligatoria") BigDecimal getCorrienteMaxima() {
        return CorrienteMaxima;
    }

    public ConnectorStatus getEstadoConector() {
        return EstadoConector;
    }

    public long getCargador() {
        return Cargador;
    }

    public String getIdCargador() {
        return IdCargador;
    }

    public Set<Reservation> getReservaciones() {
        return Reservaciones;
    }

    public Set<FeeConnector> getTarifaConector() {
        return TarifaConector;
    }

    public Long getTerminal() {
        return Terminal;
    }

    @Override
    public String toString() {
        return "ConnectorDTO{" +
                "id=" + id +
                ", Alias='" + Alias + '\'' +
                ", TipoConector=" + TipoConector +
                ", NConector='" + NConector + '\'' +
                ", VoltajeMaximo=" + VoltajeMaximo +
                ", PotenciaMaxima=" + PotenciaMaxima +
                ", CorrienteMaxima=" + CorrienteMaxima +
                ", EstadoConector=" + EstadoConector +
                ", Cargador=" + Cargador +
                ", Reservaciones=" + Reservaciones +
                ", TarifaConector=" + TarifaConector +
                ", IdCargador=" + IdCargador +
                '}';
    }
}
