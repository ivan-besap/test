package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Enums;

/**
 * Indica si la estación de carga puede realizar el reinicio solicitado.
 *
 * <p>Esta enumeración define los posibles estados que puede devolver una estación de carga
 * en respuesta a una solicitud de reinicio.
 */
public enum ResetStatusEnum {
    /**
     * La solicitud de reinicio ha sido aceptada y será procesada inmediatamente.
     */
    Accepted,

    /**
     * La solicitud de reinicio ha sido rechazada y no será procesada.
     */
    Rejected,

    /**
     * La solicitud de reinicio ha sido programada para un momento futuro.
     */
    Scheduled
}
