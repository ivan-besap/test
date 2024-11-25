package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Confirmation.Enums;

/**
 * Enum que indica si la estación de carga puede mostrar el mensaje y, en caso negativo, la razón correspondiente.
 */
public enum DisplayMessageStatusEnum {

    /** El mensaje fue aceptado y puede ser mostrado por la estación de carga. */
    Accepted,

    /** El formato del mensaje no es compatible con la estación de carga. */
    NotSupportedMessageFormat,

    /** El mensaje fue rechazado por la estación de carga. */
    Rejected,

    /** La prioridad especificada en el mensaje no es compatible con la estación de carga. */
    NotSupportedPriority,

    /** El estado especificado en el mensaje no es compatible con la estación de carga. */
    NotSupportedState,

    /** La transacción asociada al mensaje es desconocida para la estación de carga. */
    UnknownTransaction
}
