package com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO;

import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;

public class NewChargerDTO {

    private String oCPPid;
    private String nombre;
    private String alias;
    private Long manufacturerId;
    private String manufacturerName;
    private Long modeloId;
    private String modeloName;
    private Long terminal;


    public NewChargerDTO() { }

    public NewChargerDTO(Charger charger) {
        this.oCPPid = charger.getoCPPid();
        this.nombre = charger.getNombre();
        this.alias = charger.getAlias();
        this.manufacturerId = charger.getManufacturer().getId() != null ? charger.getManufacturer().getId() : null;
        this.manufacturerName = charger.getManufacturer().getName() != null ? charger.getManufacturer().getName() : null;
        this.modeloId = charger.getModel().getId() != null ? charger.getModel().getId() : null;
        this.modeloName = charger.getModel().getName() != null ? charger.getModel().getName() : null;
        this.terminal = charger.getTerminal().getId();
    }

    public String getoCPPid() {
        return oCPPid;
    }

    public void setoCPPid(String oCPPid) {
        this.oCPPid = oCPPid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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

    public Long getModeloId() {
        return modeloId;
    }

    public void setModeloId(Long modeloId) {
        this.modeloId = modeloId;
    }

    public String getModeloName() {
        return modeloName;
    }

    public void setModeloName(String modeloName) {
        this.modeloName = modeloName;
    }

    public Long getTerminal() {
        return terminal;
    }

    public void setTerminal(Long terminal) {
        this.terminal = terminal;
    }
}