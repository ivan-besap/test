package com.eVolGreen.eVolGreen.Models.Account.Fee;

import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.PerfilCargaCargador;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Fee {

    @Id
    @GenericGenerator(name= "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long id;

    @NotNull
    private String nombreTarifa;

    @NotNull
    private ZonedDateTime fechaInicio;

    private ZonedDateTime fechaFin;

    @ElementCollection
    @CollectionTable(name = "Tarifa_Dias", joinColumns = @JoinColumn(name = "Tarifa_id"))
    @Column(name = "diasDeLaSemana")
    private Set<String> diasDeLaSemana = new HashSet<>();

    private BigDecimal precioTarifa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    @JsonBackReference("Empresa-Fee")
    private Empresa empresa;

    private Boolean activo = false;

    private String consumoDeEnergiaAlarma;

    private String nombreConector;

    @NotNull
    private String nombreCargador;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "perfil_carga_id", referencedColumnName = "id")
    @JsonManagedReference
    private PerfilCargaCargador perfilCarga;

    private static final Logger logger = LoggerFactory.getLogger(Fee.class);

    public Fee() {}

    public Fee(String nombreTarifa, ZonedDateTime fechaInicio, ZonedDateTime fechaFin, Set<String> diasDeLaSemana, BigDecimal precioTarifa, Empresa empresa, Boolean activo, String consumoDeEnergiaAlarma, String nombreConector, String nombreCargador) {
        this.nombreTarifa = nombreTarifa;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.diasDeLaSemana = diasDeLaSemana;
        this.precioTarifa = precioTarifa;
        this.empresa = empresa;
        this.activo = activo;
        this.consumoDeEnergiaAlarma = consumoDeEnergiaAlarma;
        this.nombreConector = nombreConector;
        this.nombreCargador = nombreCargador;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreTarifa() {
        return nombreTarifa;
    }

    public void setNombreTarifa(String nombreTarifa) {
        this.nombreTarifa = nombreTarifa;
    }

    public ZonedDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(ZonedDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public ZonedDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(ZonedDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Set<String> getDiasDeLaSemana() {
        return diasDeLaSemana;
    }

    public void setDiasDeLaSemana(Set<String> diasDeLaSemana) {
        this.diasDeLaSemana = diasDeLaSemana;
    }

    public BigDecimal getPrecioTarifa() {
        return precioTarifa;
    }

    public void setPrecioTarifa(BigDecimal precioTarifa) {
        this.precioTarifa = precioTarifa;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getConsumoDeEnergiaAlarma() {
        return consumoDeEnergiaAlarma;
    }

    public void setConsumoDeEnergiaAlarma(String consumoDeEnergiaAlarma) {
        this.consumoDeEnergiaAlarma = consumoDeEnergiaAlarma;
    }

    public String getNombreConector() {
        return nombreConector;
    }

    public void setNombreConector(String nombreConector) {
        this.nombreConector = nombreConector;
    }

    public String getNombreCargador() {
        return nombreCargador;
    }

    public void setNombreCargador(String nombreCargador) {
        this.nombreCargador = nombreCargador;
    }

    public PerfilCargaCargador getPerfilCarga() {
        return perfilCarga;
    }

    public void setPerfilCarga(PerfilCargaCargador perfilCarga) {
        this.perfilCarga = perfilCarga;
    }

    // Método para verificar si la tarifa está vigente según la hora actual
    public boolean estaVigente(ZonedDateTime ahora) {
        ZonedDateTime ahoraLocal = ahora.withZoneSameInstant(fechaInicio.getZone());
        boolean afterInicio = !ahoraLocal.isBefore(fechaInicio);
        boolean beforeFin = fechaFin == null || !ahoraLocal.isAfter(fechaFin);
        String dayOfWeek = ahoraLocal.getDayOfWeek().toString();
        boolean dayMatches = diasDeLaSemana.contains(dayOfWeek);
        logger.info("Evaluando estaVigente: ahoraLocal=" + ahoraLocal + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", dayOfWeek=" + dayOfWeek + ", afterInicio=" + afterInicio + ", beforeFin=" + beforeFin + ", dayMatches=" + dayMatches);
        return afterInicio && beforeFin && dayMatches;
    }

    @Override
    public String toString() {
        return "Fee{" +
                "id=" + id +
                ", nombreTarifa='" + nombreTarifa + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", diasDeLaSemana=" + diasDeLaSemana +
                ", precioTarifa=" + precioTarifa +
                ", empresa=" + empresa +
                ", activo=" + activo +
                ", consumoDeEnergiaAlarma='" + consumoDeEnergiaAlarma + '\'' +
                ", nombreConector='" + nombreConector + '\'' +
                ", nombreCargador='" + nombreCargador + '\'' +
                ", perfilCarga=" + perfilCarga +
                '}';
    }
}