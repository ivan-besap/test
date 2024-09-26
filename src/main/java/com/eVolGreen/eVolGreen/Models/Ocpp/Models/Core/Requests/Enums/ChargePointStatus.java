package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.Enums;

/**
 * Enum que define los posibles estados del punto de carga.
 */
public enum ChargePointStatus {
    Available,
    Preparing,
    Charging,
    SuspendedEVSE,
    SuspendedEV,
    Finishing,
    Reserved,
    Unavailable,
    Faulted
}
