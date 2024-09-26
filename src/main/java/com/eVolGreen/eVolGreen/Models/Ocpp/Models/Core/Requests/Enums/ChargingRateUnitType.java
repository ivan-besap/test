package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.Enums;

import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.Utils.ChargingSchedule;

/**
 * Unidades de medida aceptadas para los límites de la carga.
 * Estas unidades son usadas en {@link ChargingSchedule}.
 */
public enum ChargingRateUnitType {

    /**
     * Unidad de medida en vatios.
     */
    W,

    /**
     * Unidad de medida en amperios.
     */
    A
}
