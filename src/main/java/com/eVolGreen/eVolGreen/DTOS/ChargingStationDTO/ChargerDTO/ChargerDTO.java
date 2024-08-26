package com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO;

import com.eVolGreen.eVolGreen.Models.Account.Reservation;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.ChargingUnit;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.Connector;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ChargerDTO {

    private Long id;

    @NotNull(message = "El Cargador es obligatorio")
    private String OCPPid;

    @NotNull(message = "El Nombre es obligatorio")
    private String Nombre;

    @NotNull(message = "El Alias es obligatorio")
    private String Alias;

    @NotNull(message = "El Fabricante es obligatorio")
    private String Fabricante;

    @NotNull(message = "El Modelo es obligatorio")
    private String Modelo;

    private Boolean Activo = false;

    @NotNull(message = "La Fecha de Creacion es obligatoria")
    private LocalDate FechaCreacion;

    @NotNull(message = "El Terminal es obligatorio")
    private long Terminal;

    private Set<ChargingUnit> UnidadCarga = new HashSet<>();

    private Set<Connector> Conectores = new HashSet<>();

    private Set<Reservation> Reservacion = new HashSet<>();

    public ChargerDTO(Charger Cargador) {

        id = Cargador.getId();
        OCPPid = Cargador.getoCPPid();
        Nombre = Cargador.getNombre();
        Alias = Cargador.getAlias();
        Fabricante = Cargador.getFabricante();
        Modelo = Cargador.getModelo();
        Activo = Cargador.getActivo();
        FechaCreacion = Cargador.getFechaCreacion();
        Terminal = Cargador.getTerminal().getId();
        UnidadCarga = Cargador.getUnidadCarga();
        Conectores = Cargador.getConectores();
        Reservacion = Cargador.getReservacion();
    }

    public Long getId() {
        return id;
    }

    public @NotNull(message = "El Cargador es obligatorio") String getOCPPid() {
        return OCPPid;
    }

    public @NotNull(message = "El Nombre es obligatorio") String getNombre() {
        return Nombre;
    }

    public @NotNull(message = "El Alias es obligatorio") String getAlias() {
        return Alias;
    }

    public @NotNull(message = "El Fabricante es obligatorio") String getFabricante() {
        return Fabricante;
    }

    public @NotNull(message = "El Modelo es obligatorio") String getModelo() {
        return Modelo;
    }

    public Boolean getActivo() {
        return Activo;
    }

    public @NotNull(message = "La Fecha de Creacion es obligatoria") LocalDate getFechaCreacion() {
        return FechaCreacion;
    }

    @NotNull(message = "El Terminal es obligatorio")
    public long getTerminal() {
        return Terminal;
    }

    public Set<ChargingUnit> getUnidadCarga() {
        return UnidadCarga;
    }

    public Set<Connector> getConectores() {
        return Conectores;
    }

    public Set<Reservation> getReservacion() {
        return Reservacion;
    }

    @Override
    public String toString() {
        return "ChargerDTO{" +
                "id=" + id +
                ", OCPPid='" + OCPPid + '\'' +
                ", Nombre='" + Nombre + '\'' +
                ", Alias='" + Alias + '\'' +
                ", Fabricante='" + Fabricante + '\'' +
                ", Modelo='" + Modelo + '\'' +
                ", Activo=" + Activo +
                ", FechaCreacion=" + FechaCreacion +
                ", Terminal=" + Terminal +
                ", UnidadCarga=" + UnidadCarga +
                ", Conectores=" + Conectores +
                ", Reservacion=" + Reservacion +
                '}';
    }
}
