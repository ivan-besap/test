package com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO;

import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;

import jakarta.validation.constraints.NotNull;


public class NewChargerDTO {

    private String oCPPid;

    private String nombre;

    private String alias;

    private String fabricante;

    private String modelo;

    private Long terminal;

    public NewChargerDTO() { }

    public NewChargerDTO(Charger Charger) {

        oCPPid = Charger.getoCPPid();
        nombre = Charger.getNombre();
        alias = Charger.getAlias();
        fabricante = Charger.getFabricante();
        modelo = Charger.getModelo();
        terminal = Charger.getTerminal().getId();
    }

    public String getoCPPid() {
        return oCPPid;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAlias() {
        return alias;
    }

    public String getFabricante() {
        return fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public Long getTerminal() {
        return terminal;
    }
}
