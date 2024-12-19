package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums;

/**
 * Enumeración de protocolos de transporte OCPP.
 *
 * <p>Define los protocolos de transporte disponibles para la comunicación en el protocolo OCPP.
 * Actualmente, se soportan JSON y SOAP.
 *
 * <p>Nota: SOAP no es compatible con OCPP 2.0, pero es utilizado en versiones anteriores.
 */
public enum OCPPTransportEnum {
    /** Transporte basado en JSON. */
    JSON,

    /** Transporte basado en SOAP. */
    SOAP
}
