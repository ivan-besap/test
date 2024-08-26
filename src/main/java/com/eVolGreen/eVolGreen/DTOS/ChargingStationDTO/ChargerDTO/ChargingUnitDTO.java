package com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO;

import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.ChargingUnit;
import jakarta.validation.constraints.NotNull;

public class ChargingUnitDTO {

    private long id;

    @NotNull(message = "La Unidad de Carga es obligatoria")
    private String UnidadCargas;

    private long Cargador;

    public ChargingUnitDTO(ChargingUnit UnidadCarga) {

        id = UnidadCarga.getId();
        UnidadCargas = UnidadCarga.getUnidadCarga();
        Cargador = UnidadCarga.getCargador().getId();
    }

    public long getId() {
        return id;
    }

    public @NotNull(message = "La Unidad de Carga es obligatoria") String getUnidadCargas() {
        return UnidadCargas;
    }

    public long getCargador() {
        return Cargador;
    }

    @Override
    public String toString() {
        return "ChargingUnitDTO{" +
                "id=" + id +
                ", UnidadCargas='" + UnidadCargas + '\'' +
                ", Cargador=" + Cargador +
                '}';
    }
}
