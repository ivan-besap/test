package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.Enums;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.RemoteStartTransactionConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.RemoteStopTransactionConfirmation;

/**
 * Valores aceptados para la confirmación de {@link RemoteStartTransactionConfirmation}
 * y {@link RemoteStopTransactionConfirmation}.
 */
public enum RemoteStartStopStatus {
    Accepted,  // Indica que la solicitud fue aceptada
    Rejected   // Indica que la solicitud fue rechazada
}
