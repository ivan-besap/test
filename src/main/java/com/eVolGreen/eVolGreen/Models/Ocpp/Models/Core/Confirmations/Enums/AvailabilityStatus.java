package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.Enums;

import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.ChangeAvailabilityConfirmation;

/**
 * Enum que define los posibles estados de la confirmación de cambio de disponibilidad en OCPP.
 * Estos estados son utilizados en la respuesta {@link ChangeAvailabilityConfirmation}.
 */
public enum AvailabilityStatus {

    /**
     * Indica que el cambio de disponibilidad ha sido aceptado por la estación de carga.
     */
    Accepted,

    /**
     * Indica que el cambio de disponibilidad ha sido rechazado por la estación de carga.
     */
    Rejected,

    /**
     * Indica que el cambio de disponibilidad ha sido programado para un momento posterior.
     */
    Scheduled
}
