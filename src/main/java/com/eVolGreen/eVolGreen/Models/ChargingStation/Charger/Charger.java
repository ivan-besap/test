package com.eVolGreen.eVolGreen.Models.ChargingStation.Charger;

import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargerStatus;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.Connector;
import com.eVolGreen.eVolGreen.Models.Account.Reservation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import javax.validation.constraints.NotNull;
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

    private Boolean activo = false;

    @NotNull(message = "La Fecha de Creacion es obligatoria")
    @Column(name = "Fecha_Creacion")
    private LocalDate fechaCreacion;

    @NotNull(message = "El estado del cargador no puede ser nulo.")
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_cargador")
    private ChargerStatus estadoCargador = ChargerStatus.ACTIVE;

    @NotNull(message = "El Terminal es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terminal_id")
    @JsonBackReference("Terminal-Cargador")
    private ChargingStation terminal;

    @OneToMany(mappedBy = "Cargador", fetch = FetchType.LAZY)
    @JsonManagedReference("Cargador-UnidadCarga")
    private Set<ChargingUnit> UnidadCarga = new HashSet<>();

    @OneToMany(mappedBy = "cargador", fetch = FetchType.LAZY)
    @JsonManagedReference("Cargador-Conector")
    private Set<Connector> Conectores = new HashSet<>();

    @OneToMany(mappedBy = "cargador", fetch = FetchType.LAZY)
    @JsonManagedReference("Cargador-Reservacion")
    private Set<Reservation> Reservacion = new HashSet<>();

    @NotNull(message = "El Fabricante es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fabricante_id")
    private ChargerManufacturer fabricante;

    @NotNull(message = "El Modelo es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modelo_id")
    private ChargerModel modelo;

    @OneToMany(mappedBy = "cargador", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("Charger-Mantenimiento")
    private Set<Mantenimiento> mantenimientos = new HashSet<>();

    public Charger() { }

    public Charger(String oCPPid, String nombre, String alias, ChargerModel model, Boolean activo, LocalDate fechaCreacion, ChargingStation Terminal, ChargerManufacturer manufacturer) {
        this.oCPPid = oCPPid;
        this.nombre = nombre;
        this.alias = alias;
        this.modelo = model;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
        this.terminal = Terminal;
        this.fabricante = manufacturer;
        this.estadoCargador = ChargerStatus.ACTIVE;
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

//    public @NotNull(message = "El Fabricante es obligatorio") String getFabricante() {
//        return fabricante;
//    }

    /*public void setFabricante(@NotNull(message = "El Fabricante es obligatorio") String fabricante) {
        this.fabricante = fabricante;
    }*/


    // Método para establecer el modelo
    public ChargerModel getModel() {
        return modelo;
    }

    public void setModel(ChargerModel model) {
        this.modelo = model;
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
        return terminal;
    }

    public void setTerminal(@NotNull(message = "El Terminal es obligatorio") ChargingStation terminal) {
        this.terminal = terminal;
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

    public ChargerManufacturer getManufacturer() {
        return fabricante;
    }

    public void setManufacturer(ChargerManufacturer manufacturer) {
        this.fabricante = manufacturer;
    }

    public ChargerStatus getEstadoCargador() {
        return estadoCargador;
    }

    public void setEstadoCargador(ChargerStatus estadoCargador) {
        this.estadoCargador = estadoCargador;
    }

    public Set<Mantenimiento> getMantenimientos() {
        return mantenimientos;
    }

    public void setMantenimientos(Set<Mantenimiento> mantenimientos) {
        this.mantenimientos = mantenimientos;
    }
}
