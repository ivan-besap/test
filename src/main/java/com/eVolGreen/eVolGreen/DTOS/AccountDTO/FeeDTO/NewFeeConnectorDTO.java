package com.eVolGreen.eVolGreen.DTOS.AccountDTO.FeeDTO;

import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;
import com.eVolGreen.eVolGreen.Models.Account.Fee.FeeConnector;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.Connector;

import java.time.LocalDateTime;

public class NewFeeConnectorDTO {

    private Long tarifa;

    private Long conector;

    private LocalDateTime fechaActivacion;

    private Boolean activo = false;

    public NewFeeConnectorDTO () {}

    public NewFeeConnectorDTO (FeeConnector TarifaConector) {

        tarifa = TarifaConector.getTarifa().getId();
        conector = TarifaConector.getConector().getId();
        fechaActivacion = TarifaConector.getFechaActivacion();
        activo = TarifaConector.getActivo();
    }

    public Long getTarifa() {
        return tarifa;
    }

    public Long getConector() {
        return conector;
    }

    public LocalDateTime getFechaActivacion() {
        return fechaActivacion;
    }

    public Boolean getActivo() {
        return activo;
    }
}
