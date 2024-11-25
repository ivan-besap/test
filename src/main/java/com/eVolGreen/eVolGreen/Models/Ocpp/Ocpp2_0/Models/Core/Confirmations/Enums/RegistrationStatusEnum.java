package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Enums;

/**
 * Enumera los estados de registro de una estación de carga en el CSMS (Sistema Central de Gestión
 * de Carga).
 */
public enum RegistrationStatusEnum {

    /** El registro de la estación de carga ha sido aceptado. */
    Accepted,

    /** El registro de la estación de carga está pendiente de aprobación. */
    Pending,

    /** El registro de la estación de carga ha sido rechazado. */
    Rejected
}
