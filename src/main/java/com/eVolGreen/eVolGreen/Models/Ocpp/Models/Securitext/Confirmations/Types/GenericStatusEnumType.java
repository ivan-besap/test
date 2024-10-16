package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Confirmations.Types;

/**
 * Enumeración que define el estado genérico de una respuesta a una solicitud.
 *
 * <p>Esta enumeración se utiliza para indicar si una solicitud ha sido aceptada o rechazada en el sistema OCPP.
 * Dependiendo del estado, el sistema actuará en consecuencia, ya sea ejecutando la solicitud o descartándola.</p>
 */
public enum GenericStatusEnumType {

    /**
     * La solicitud ha sido aceptada y será ejecutada.
     */
    Accepted,

    /**
     * La solicitud no ha sido aceptada y no será ejecutada.
     */
    Rejected
}
