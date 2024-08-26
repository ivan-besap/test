package com.eVolGreen.eVolGreen.Models.ChargingStation.Charger;

import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.Connector;
import com.eVolGreen.eVolGreen.Models.Account.Reservation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Charger {

    @Id
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private Long id;

    @NotNull(message = "El Cargador es obligatorio")
    private String oCPPid;

    @NotNull(message = "El Nombre es obligatorio")
    private String nombre;

    @NotNull(message = "El Alias es obligatorio")
    private String alias;

    @NotNull(message = "El Fabricante es obligatorio")
    private String fabricante;

    @NotNull(message = "El Modelo es obligatorio")
    private String modelo;

    private Boolean activo = false;

    @NotNull(message = "La Fecha de Creacion es obligatoria")
    @Column(name = "Fecha_Creacion")
    private LocalDate fechaCreacion;

    @NotNull(message = "El Terminal es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Terminal_id")
    @JsonBackReference("Terminal-Cargador")
    private ChargingStation Terminal;

    @OneToMany(mappedBy = "Cargador", fetch = FetchType.LAZY)
    @JsonManagedReference("Cargador-UnidadCarga")
    private Set<ChargingUnit> UnidadCarga = new HashSet<>();

    @OneToMany(mappedBy = "Cargador", fetch = FetchType.LAZY)
    @JsonManagedReference("Cargador-Conector")
    private Set<Connector> Conectores = new HashSet<>();

    @OneToMany(mappedBy = "Cargador", fetch = FetchType.LAZY)
    @JsonManagedReference("Cargador-Reservacion")
    private Set<Reservation> Reservacion = new HashSet<>();

    public Charger() { }

    public Charger(String oCPPid, String nombre, String alias, String fabricante, String modelo, Boolean activo, LocalDate fechaCreacion, ChargingStation Terminal) {
        this.oCPPid = oCPPid;
        this.nombre = nombre;
        this.alias = alias;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
        this.Terminal = Terminal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "El Cargador es obligatorio") String getoCPPid() {
        return oCPPid;
    }

    public void setoCPPid(@NotNull(message = "El Cargador es obligatorio") String oCPPid) {
        this.oCPPid = oCPPid;
    }

    public @NotNull(message = "El Nombre es obligatorio") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotNull(message = "El Nombre es obligatorio") String nombre) {
        this.nombre = nombre;
    }

    public @NotNull(message = "El Alias es obligatorio") String getAlias() {
        return alias;
    }

    public void setAlias(@NotNull(message = "El Alias es obligatorio") String alias) {
        this.alias = alias;
    }

    public @NotNull(message = "El Fabricante es obligatorio") String getFabricante() {
        return fabricante;
    }

    public void setFabricante(@NotNull(message = "El Fabricante es obligatorio") String fabricante) {
        this.fabricante = fabricante;
    }

    public @NotNull(message = "El Modelo es obligatorio") String getModelo() {
        return modelo;
    }

    public void setModelo(@NotNull(message = "El Modelo es obligatorio") String modelo) {
        this.modelo = modelo;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public @NotNull(message = "La Fecha de Creacion es obligatoria") LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(@NotNull(message = "La Fecha de Creacion es obligatoria") LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public @NotNull(message = "El Terminal es obligatorio") ChargingStation getTerminal() {
        return Terminal;
    }

    public void setTerminal(@NotNull(message = "El Terminal es obligatorio") ChargingStation terminal) {
        Terminal = terminal;
    }

    public Set<ChargingUnit> getUnidadCarga() {
        return UnidadCarga;
    }

    public void setUnidadCarga(Set<ChargingUnit> unidadCarga) {
        UnidadCarga = unidadCarga;
    }

    public Set<Connector> getConectores() {
        return Conectores;
    }

    public void setConectores(Set<Connector> conectores) {
        Conectores = conectores;
    }

    public Set<Reservation> getReservacion() {
        return Reservacion;
    }

    public void setReservacion(Set<Reservation> reservacion) {
        Reservacion = reservacion;
    }
}
