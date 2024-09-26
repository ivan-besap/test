package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.Enums;

/**
 * Enum para representar las razones para detener la transacción.
 */
public enum StopTransactionReason {

    EmergencyStop,
    EVDisconnected,
    HardReset,
    Local,
    Other,
    PowerLoss,
    Reboot,
    Remote,
    SoftReset,
    UnlockCommand,
    DeAuthorized

}
