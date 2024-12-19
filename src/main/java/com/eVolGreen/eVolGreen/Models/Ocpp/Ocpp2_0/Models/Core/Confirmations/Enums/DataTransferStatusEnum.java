package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Enums;

/**
 * Representa el resultado de una transferencia de datos entre una estación de carga y un sistema
 * central.
 *
 * <p>Utilizado en las respuestas del mensaje {@code DataTransfer}.
 *
 * <p>OCPP 2.0.1 FINAL
 */
public enum DataTransferStatusEnum {
    /**
     * La solicitud de transferencia de datos fue aceptada y procesada con éxito.
     */
    Accepted,

    /**
     * La solicitud de transferencia de datos fue rechazada por el sistema receptor.
     */
    Rejected,

    /**
     * El identificador del mensaje proporcionado en la solicitud no es reconocido por el sistema.
     */
    UnknownMessageId,

    /**
     * El identificador del proveedor proporcionado en la solicitud no es reconocido por el sistema.
     */
    UnknownVendorId
}
