package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations;

/**
 * Valores aceptados para la confirmación de {@link RemoteStartTransactionConfirmation}
 * y {@link RemoteStopTransactionConfirmation}.
 */
public enum RemoteStartStopStatus {
    Accepted,  // Indica que la solicitud fue aceptada
    Rejected   // Indica que la solicitud fue rechazada
}
