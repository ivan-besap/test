package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.Enums;

/**
 * Enum que define los posibles códigos de error del punto de carga.
 */
public enum ChargePointErrorCode {
    ConnectorLockFailure,
    EVCommunicationError,
    GroundFailure,
    HighTemperature,
    InternalError,
    LocalListConflict,
    NoError,
    OtherError,
    OverCurrentFailure,
    OverVoltage,
    PowerMeterFailure,
    PowerSwitchFailure,
    ReaderFailure,
    ResetFailure,
    UnderVoltage,
    WeakSignal
}
