package com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ConnectorDTO;
import com.eVolGreen.eVolGreen.Models.Account.Car.Car;
import com.eVolGreen.eVolGreen.Models.Account.Reservation;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.ChargingUnit;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargerStatus;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.Connector;


import javax.validation.constraints.NotNull;
import java.net.ConnectException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ChargerDTO {

    private Long id;

    @NotNull(message = "El Cargador es obligatorio")
    private String OCPPid;

    @NotNull(message = "El Nombre es obligatorio")
    private String Nombre;

    @NotNull(message = "El Alias es obligatorio")
    private String Alias;

    private Boolean Activo = false;

    @NotNull(message = "La Fecha de Creacion es obligatoria")
    private LocalDate FechaCreacion;

    @NotNull(message = "El Terminal es obligatorio")
    private Long terminalId;

    private String terminalName;

    private String ubicacionTerminal;

    private Set<ChargingUnit> UnidadCarga = new HashSet<>();

    private List<ConnectorDTO> Conectores;

    private Set<Reservation> Reservacion = new HashSet<>();

    private Long manufacturerId;
    private String manufacturerName;

    private Long modelId;
    private String modelName;

    private ChargerStatus estadoCargador;

    private Set<MantenimientoDTO> mantenimientos;

    public ChargerDTO(Charger Cargador) {

        id = Cargador.getId();
        OCPPid = Cargador.getoCPPid();
        Nombre = Cargador.getNombre();
        Alias = Cargador.getAlias();
        manufacturerId = Cargador.getManufacturer() != null ? Cargador.getManufacturer().getId() : null;
        manufacturerName = Cargador.getManufacturer() != null ? Cargador.getManufacturer().getName() : null;
        modelId = Cargador.getModel() != null ? Cargador.getModel().getId() : null;
        modelName = Cargador.getModel() != null ? Cargador.getModel().getName() : null;
        Activo = Cargador.getActivo();
        FechaCreacion = Cargador.getFechaCreacion();
        terminalId = Cargador.getTerminal().getId();
        UnidadCarga = Cargador.getUnidadCarga();
        Conectores = Cargador.getConectores().stream()
                .map(ConnectorDTO::new)
                .collect(Collectors.toList());
        Reservacion = Cargador.getReservacion();
        terminalName = Cargador.getTerminal().getNombreTerminal();
        ubicacionTerminal = Cargador.getTerminal().getUbicacionTerminal().getDireccion();
        this.estadoCargador = Cargador.getEstadoCargador();
        this.mantenimientos = Cargador.getMantenimientos().stream()
                .map(MantenimientoDTO::new)  // Ahora usa el constructor que acepta un objeto Mantenimiento
                .collect(Collectors.toSet());
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

    public Boolean getActivo() {
        return Activo;
    }

    public @NotNull(message = "La Fecha de Creacion es obligatoria") LocalDate getFechaCreacion() {
        return FechaCreacion;
    }

    @NotNull(message = "El Terminal es obligatorio")
    public Long getTerminalId() { // Cambiado a terminalId
        return terminalId;
    }

    public String getTerminalName() {  // Nuevo getter para el nombre del terminal
        return terminalName;
    }
    public String getUbicacionTerminal() {
        return ubicacionTerminal;
    }
    public Set<ChargingUnit> getUnidadCarga() {
        return UnidadCarga;
    }

    public List<ConnectorDTO> getConectores() {
        return Conectores;
    }

    public Set<Reservation> getReservacion() {
        return Reservacion;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public ChargerStatus getEstadoCargador() {
        return estadoCargador;
    }

    public void setEstadoCargador(ChargerStatus estadoCargador) {
        this.estadoCargador = estadoCargador;
    }

    public Set<MantenimientoDTO> getMantenimientos() {
        return mantenimientos;
    }

    @Override
    public String toString() {
        return "ChargerDTO{" +
                "id=" + id +
                ", OCPPid='" + OCPPid + '\'' +
                ", Nombre='" + Nombre + '\'' +
                ", Alias='" + Alias + '\'' +
                ", Fabricante='" + manufacturerName + '\'' +
                ", Modelo='" + modelName + '\'' +
                ", Activo=" + Activo +
                ", FechaCreacion=" + FechaCreacion +
                ", TerminalId=" + terminalId +
                ", TerminalName=" + terminalName +
                ", UbicacionTerminal=" + ubicacionTerminal +
                ", UnidadCarga=" + UnidadCarga +
                ", Conectores=" + Conectores +
                ", Reservacion=" + Reservacion +
                ", EstadoCargador=" + estadoCargador +
                ", Mantenimiento=" + mantenimientos +
                '}';
    }
}
