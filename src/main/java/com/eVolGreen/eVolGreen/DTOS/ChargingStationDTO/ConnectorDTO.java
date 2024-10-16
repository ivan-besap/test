package com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.FeeDTO.FeeDTO;
import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;
import com.eVolGreen.eVolGreen.Models.Account.Reservation;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.Connector;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.ConnectorStatus;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.TypeConnector;
import jakarta.persistence.*;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

public class ConnectorDTO {

    private long id;

    @NotNull(message = "El Alias es obligatorio")
    private String Alias;

    @NotNull(message = "El Tipo de Conector es obligatorio")
    private TypeConnectorDTO tipoConector;

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

    private FeeDTO tarifa;

    private String IdCargador;

    private String nombreTerminal;

    public ConnectorDTO(Connector Conector) {

        id = Conector.getId();
        Alias = Conector.getAlias();
        NConector = Conector.getNConector();
        VoltajeMaximo = Conector.getVoltajeMaximo();
        PotenciaMaxima = Conector.getPotenciaMaxima();
        CorrienteMaxima = Conector.getCorrienteMaxima();
        EstadoConector = Conector.getEstadoConector();
        Cargador = Conector.getCargador().getId();
        this.tipoConector = new TypeConnectorDTO(Conector.getTipoConector());
        Terminal = Conector.getTerminal().getId();
        nombreTerminal = Conector.getTerminal().getNombreTerminal();
        Reservaciones = Conector.getReservaciones();
        if (Conector.getTarifa() != null) {
            this.tarifa = new FeeDTO(Conector.getTarifa());
        } else {
            this.tarifa = null;
        }
        IdCargador = Conector.getCargador().getoCPPid();
    }

    public long getId() {
        return id;
    }

    public @NotNull(message = "El Alias es obligatorio") String getAlias() {
        return Alias;
    }

    public @NotNull(message = "El Tipo de Conector es obligatorio") TypeConnectorDTO getTipoConector() {
        return tipoConector;
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

    public FeeDTO getTarifa() {
        return tarifa;
    }

    public Long getTerminal() {
        return Terminal;
    }

    public String getNombreTerminal() {
        return nombreTerminal; // Método getter para el nombre del terminal
    }

    @Override
    public String toString() {
        return "ConnectorDTO{" +
                "id=" + id +
                ", Alias='" + Alias + '\'' +
                ", TipoConector=" + tipoConector +
                ", NConector='" + NConector + '\'' +
                ", VoltajeMaximo=" + VoltajeMaximo +
                ", PotenciaMaxima=" + PotenciaMaxima +
                ", CorrienteMaxima=" + CorrienteMaxima +
                ", EstadoConector=" + EstadoConector +
                ", Cargador=" + Cargador +
                ", Reservaciones=" + Reservaciones +
                ", Tarifa=" + tarifa +
                ", IdCargador=" + IdCargador +
                '}';
    }
}
