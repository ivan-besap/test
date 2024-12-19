package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.Enums;

/**
 * Estado del resultado al intentar obtener una variable.
 * Representa las posibles respuestas que el sistema puede devolver.
 */
public enum GetVariableStatusEnum {

    /** La solicitud para obtener la variable fue aceptada exitosamente. */
    Accepted,

    /** La solicitud para obtener la variable fue rechazada. */
    Rejected,

    /** El componente solicitado no se encontró. */
    UnknownComponent,

    /** La variable solicitada no se encontró. */
    UnknownVariable,

    /** El tipo de atributo solicitado no es compatible. */
    NotSupportedAttributeType
}
