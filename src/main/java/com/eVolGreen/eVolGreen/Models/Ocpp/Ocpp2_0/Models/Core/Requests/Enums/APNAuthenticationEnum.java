package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums;

/**
 * Enumeración que define los métodos de autenticación disponibles para APN.
 * <p>Estos métodos se utilizan para autenticar conexiones a redes de acceso privado.</p>
 */
public enum APNAuthenticationEnum {
    /** Protocolo CHAP (Challenge-Handshake Authentication Protocol). */
    CHAP,

    /** Sin autenticación requerida. */
    NONE,

    /** Protocolo PAP (Password Authentication Protocol). */
    PAP,

    /** Autenticación automática según disponibilidad (CHAP o PAP). */
    AUTO
}
