package com.eVolGreen.eVolGreen.Models.ChargingStation.Connector;

import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;
import com.eVolGreen.eVolGreen.Models.Account.Reservation;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Connector {

    @Id
    @GenericGenerator(name= "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long id;

    @NotNull(message = "El Alias es obligatorio")
    private String Alias;

    @NotNull(message = "El Tipo de Conector es obligatorio")
    @Column(name = "Tipo_Conector")
    @Enumerated(EnumType.STRING)
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
    private ConnectorStatus EstadoConector = ConnectorStatus.DISCONNECTED;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Cargador_id")
    @JsonBackReference("Cargador-Conector")
    private Charger cargador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terminal_id")
    @JsonBackReference("Terminal-Conector")
    private ChargingStation terminal;

    @OneToMany(mappedBy = "conector", fetch = FetchType.LAZY)
    @JsonManagedReference("Conector-Reservacion")
    private Set<Reservation> Reservaciones = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tarifa_id", nullable = true)
    @JsonBackReference("Tarifa-Conector")
    private Fee tarifa;  // Relación directa con la tarifa

    private boolean Activo = false;

    public Connector() { }

    public Connector(String Alias, TypeConnector TipoConector, String NConector, BigDecimal VoltajeMaximo, BigDecimal PotenciaMaxima, BigDecimal CorrienteMaxima, Charger charger, ChargingStation terminal, Fee tarifa, ConnectorStatus EstadoConector, boolean Activo) {
        this.Alias = Alias;
        this.TipoConector = TipoConector;
        this.NConector = NConector;
        this.VoltajeMaximo = VoltajeMaximo;
        this.PotenciaMaxima = PotenciaMaxima;
        this.CorrienteMaxima = CorrienteMaxima;
        this.cargador = charger;
        this.terminal = terminal;
        this.EstadoConector = EstadoConector;
        this.Activo = Activo;
        this.tarifa = tarifa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "El Alias es obligatorio") String getAlias() {
        return Alias;
    }

    public void setAlias(@NotNull(message = "El Alias es obligatorio") String alias) {
        Alias = alias;
    }

    public @NotNull(message = "El Tipo de Conector es obligatorio") TypeConnector getTipoConector() {
        return TipoConector;
    }

    public void setTipoConector(@NotNull(message = "El Tipo de Conector es obligatorio") TypeConnector tipoConector) {
        TipoConector = tipoConector;
    }

    public @NotNull(message = "El N° de Conector es obligatorio") String getNConector() {
        return NConector;
    }

    public void setNConector(@NotNull(message = "El N° de Conector es obligatorio") String NConector) {
        this.NConector = NConector;
    }

    public @NotNull(message = "El Voltaje Maximo es obligatorio") BigDecimal getVoltajeMaximo() {
        return VoltajeMaximo;
    }

    public void setVoltajeMaximo(@NotNull(message = "El Voltaje Maximo es obligatorio") BigDecimal voltajeMaximo) {
        VoltajeMaximo = voltajeMaximo;
    }

    public ChargingStation getTerminal() {
        return terminal;
    }

    public void setTerminal(ChargingStation terminal) {
        this.terminal = terminal;
    }

    public @NotNull(message = "La Potencia Maxima es obligatoria") BigDecimal getPotenciaMaxima() {
        return PotenciaMaxima;
    }

    public void setPotenciaMaxima(@NotNull(message = "La Potencia Maxima es obligatoria") BigDecimal potenciaMaxima) {
        PotenciaMaxima = potenciaMaxima;
    }

    public @NotNull(message = "La Corriente Maxima es obligatoria") BigDecimal getCorrienteMaxima() {
        return CorrienteMaxima;
    }

    public void setCorrienteMaxima(@NotNull(message = "La Corriente Maxima es obligatoria") BigDecimal corrienteMaxima) {
        CorrienteMaxima = corrienteMaxima;
    }

    public ConnectorStatus getEstadoConector() {
        return EstadoConector;
    }

    public void setEstadoConector(ConnectorStatus estadoConector) {
        EstadoConector = estadoConector;
    }

    public Charger getCargador() {
        return cargador;
    }

    public void setCargador(Charger cargador) {
        cargador = cargador;
    }

    public Set<Reservation> getReservaciones() {
        return Reservaciones;
    }

    public void setReservaciones(Set<Reservation> reservaciones) {
        Reservaciones = reservaciones;
    }

    public Fee getTarifa() {
        return tarifa;
    }

    public void setTarifa(Fee tarifa) {
        this.tarifa = tarifa;
    }
    public void setId(long id) {
        this.id = id;
    }

    public boolean getActivo() {
        return Activo;
    }

    public void setActivo(boolean activo) {
        Activo = activo;
    }

    @Override
    public String toString() {
        return "Connector{" +
                "id=" + id +
                ", Alias='" + Alias + '\'' +
                ", TipoConector=" + TipoConector +
                ", NConector='" + NConector + '\'' +
                ", VoltajeMaximo=" + VoltajeMaximo +
                ", PotenciaMaxima=" + PotenciaMaxima +
                ", CorrienteMaxima=" + CorrienteMaxima +
                ", EstadoConector=" + EstadoConector +
                ", Cargador=" + cargador +
                ", Reservaciones=" + Reservaciones +
                ", Tarifa=" + tarifa +
                '}';
    }
}
