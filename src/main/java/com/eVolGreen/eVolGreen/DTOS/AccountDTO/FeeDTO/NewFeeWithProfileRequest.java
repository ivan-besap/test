package com.eVolGreen.eVolGreen.DTOS.AccountDTO.FeeDTO;

import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.PerfilCargaCargador;

import javax.validation.constraints.NotNull;

public class NewFeeWithProfileRequest {

    @NotNull(message = "La tarifa es obligatoria")
    private Fee tarifa;

    @NotNull(message = "El perfil de carga es obligatorio")
    private PerfilCargaCargador perfilCarga;

    // Constructor
    public NewFeeWithProfileRequest() {}

    public NewFeeWithProfileRequest(Fee tarifa, PerfilCargaCargador perfilCarga) {
        this.tarifa = tarifa;
        this.perfilCarga = perfilCarga;
    }

    // Getters y Setters
    public Fee getTarifa() { return tarifa; }

    public void setTarifa(Fee tarifa) { this.tarifa = tarifa; }

    public PerfilCargaCargador getPerfilCarga() { return perfilCarga; }

    public void setPerfilCarga(PerfilCargaCargador perfilCarga) { this.perfilCarga = perfilCarga; }
}