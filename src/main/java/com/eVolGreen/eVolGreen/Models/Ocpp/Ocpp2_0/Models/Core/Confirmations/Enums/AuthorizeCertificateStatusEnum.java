package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Enums;

/**
 * Representa el estado de un certificado utilizado en el proceso de autorización.
 *
 * <p>Los valores posibles indican si el certificado es válido o si ocurrió algún problema
 * durante la verificación.
 *
 * <pre>
 * - Si todos los certificados son válidos: devuelve 'Accepted'.
 * - Si uno de los certificados fue revocado: devuelve 'CertificateRevoked'.
 * </pre>
 */
public enum AuthorizeCertificateStatusEnum {

    /** El certificado es válido. */
    Accepted,

    /** Error en la firma del certificado. */
    SignatureError,

    /** El certificado ha expirado. */
    CertificateExpired,

    /** El certificado ha sido revocado. */
    CertificateRevoked,

    /** No hay un certificado disponible. */
    NoCertificateAvailable,

    /** Error en la cadena de certificación. */
    CertChainError,

    /** El contrato asociado al certificado ha sido cancelado. */
    ContractCancelled
}
