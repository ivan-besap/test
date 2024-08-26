package com.eVolGreen.eVolGreen.DTOS.AccountDTO.FeeDTO;


import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;
import com.eVolGreen.eVolGreen.Models.Account.Fee.FeeConnector;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.Connector;

import java.time.LocalDateTime;

public class FeeConnectorDTO {

    private long id;

    private Fee Tarifa;

    private Connector Conector;

    private LocalDateTime FechaActivacion;

    public FeeConnectorDTO (FeeConnector TarifaConector) {

        id = TarifaConector.getId();
        Tarifa = TarifaConector.getTarifa();
        Conector = TarifaConector.getConector();
        FechaActivacion = TarifaConector.getFechaActivacion();

    }

    public long getId() {
        return id;
    }

    public Fee getTarifa() {
        return Tarifa;
    }

    public Connector getConector() {
        return Conector;
    }

    public LocalDateTime getFechaActivacion() {
        return FechaActivacion;
    }

    @Override
    public String toString() {
        return "FeeConnectorDTO{" +
                "id=" + id +
                ", Tarifa=" + Tarifa +
                ", Conector=" + Conector +
                ", FechaActivacion=" + FechaActivacion +
                '}';
    }
}
