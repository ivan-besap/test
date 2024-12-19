package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.Enums;

/**
 * Representa los posibles estados de carga durante una transacción en OCPP 2.0.1.
 *
 * <p>Estos estados indican el estado actual de la carga o la conexión entre el EV y el EVSE.
 */
public enum ChargingStateEnum {

    /** El vehículo eléctrico (EV) está actualmente en proceso de carga. */
    Charging,

    /** El EV está conectado al EVSE, pero no se está cargando. */
    EVConnected,

    /** La carga está suspendida por el EV. */
    SuspendedEV,

    /** La carga está suspendida por el EVSE. */
    SuspendedEVSE,

    /** El EVSE está en modo inactivo y no hay carga en curso. */
    Idle
}
