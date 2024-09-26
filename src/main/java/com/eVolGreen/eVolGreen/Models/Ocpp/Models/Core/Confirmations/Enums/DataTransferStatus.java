package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.Enums;

/**
 * Enum que define los posibles estados de una transferencia de datos en el sistema OCPP.
 */
public enum DataTransferStatus {
    /**
     * Indica que la transferencia de datos fue aceptada.
     */
    Accepted,

    /**
     * Indica que la transferencia de datos fue rechazada.
     */
    Rejected,

    /**
     * Indica que el messageId es desconocido.
     */
    UnknownMessageId,

    /**
     * Indica que el vendorId es desconocido.
     */
    UnknownVendorId
}
