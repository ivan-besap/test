package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.Enums;

/**
 * Enumera los posibles estados del monitoreo solicitado en OCPP.
 * <p>
 * Cada estado indica el resultado de la solicitud de monitoreo de variables
 * y proporciona información sobre la razón en caso de que no se acepte.
 */
public enum SetMonitoringStatusEnum {

    /** El monitoreo solicitado fue aceptado y se configuró exitosamente. */
    Accepted,

    /** El componente especificado en la solicitud es desconocido o no válido. */
    UnknownComponent,

    /** La variable especificada en la solicitud es desconocida o no válida. */
    UnknownVariable,

    /**
     * El tipo de monitor solicitado no es compatible con la estación de carga.
     */
    UnsupportedMonitorType,

    /** La solicitud de monitoreo fue rechazada por la estación de carga. */
    Rejected,

    /**
     * La solicitud contiene un monitor que ya existe o tiene un identificador duplicado.
     */
    Duplicate
}
