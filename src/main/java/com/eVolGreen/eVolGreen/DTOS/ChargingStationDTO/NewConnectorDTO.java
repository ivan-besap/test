package com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO;

import com.eVolGreen.eVolGreen.Models.Account.Fee.FeeConnector;
import com.eVolGreen.eVolGreen.Models.Account.Reservation;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.Connector;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.ConnectorStatus;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.TypeConnector;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class NewConnectorDTO {

    private long id;

    @NotNull(message = "El Alias es obligatorio")
    private String alias;

    @NotNull(message = "El Tipo de Conector es obligatorio")
    private TypeConnector tipoConector;

    @NotNull(message = "El N° de Conector es obligatorio")
    private String nConector;

    @NotNull(message = "El Voltaje Maximo es obligatorio")
    private BigDecimal voltajeMaximo;

    @NotNull(message = "La Potencia Maxima es obligatoria")
    private BigDecimal potenciaMaxima;

    @NotNull(message = "La Corriente Maxima es obligatoria")
    private BigDecimal corrienteMaxima;

    @Enumerated(EnumType.STRING)
    private ConnectorStatus EstadoConector;

    private Long cargador;

    private Long terminal;


    public NewConnectorDTO() { }

    public NewConnectorDTO(Connector Conector) {


        alias = Conector.getAlias();
        tipoConector = Conector.getTipoConector();
        nConector = Conector.getNConector();
        voltajeMaximo = Conector.getVoltajeMaximo();
        potenciaMaxima = Conector.getPotenciaMaxima();
        corrienteMaxima = Conector.getCorrienteMaxima();
        cargador = Conector.getCargador().getId();
        terminal = Conector.getTerminal().getId();
    }

    public long getId() {
        return id;
    }

    public @NotNull(message = "El Alias es obligatorio") String getAlias() {
        return alias;
    }

    public @NotNull(message = "El Tipo de Conector es obligatorio") TypeConnector getTipoConector() {
        return tipoConector;
    }

    public @NotNull(message = "El N° de Conector es obligatorio") String getnConector() {
        return nConector;
    }

    public @NotNull(message = "El Voltaje Maximo es obligatorio") BigDecimal getVoltajeMaximo() {
        return voltajeMaximo;
    }

    public @NotNull(message = "La Potencia Maxima es obligatoria") BigDecimal getPotenciaMaxima() {
        return potenciaMaxima;
    }

    public @NotNull(message = "La Corriente Maxima es obligatoria") BigDecimal getCorrienteMaxima() {
        return corrienteMaxima;
    }

    public ConnectorStatus getEstadoConector() {
        return EstadoConector;
    }

    public Long getCargador() {
        return cargador;
    }

    public Long getTerminal() {
        return terminal;
    }
}
