package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Enums;

/**
 * Representa el estado actual de un token de identificación en el proceso de autorización.
 *
 * <p>Los valores posibles indican si el token es válido, si tiene restricciones o si ocurrió algún
 * problema durante la autorización.
 */
public enum AuthorizationStatusEnum {

    /** El token fue aceptado y la autorización es válida. */
    Accepted,

    /** El token está bloqueado y no puede ser utilizado para la autorización. */
    Blocked,

    /** Transacción concurrente detectada, no se permite una nueva transacción. */
    ConcurrentTx,

    /** El token ha expirado y ya no es válido. */
    Expired,

    /** El token es inválido. */
    Invalid,

    /** No hay suficiente crédito para autorizar la operación. */
    NoCredit,

    /** El tipo de EVSE no está permitido para este token. */
    NotAllowedTypeEVSE,

    /** El token no es válido en esta ubicación. */
    NotAtThisLocation,

    /** El token no es válido en este momento. */
    NotAtThisTime,

    /** El estado del token es desconocido. */
    Unknown
}
