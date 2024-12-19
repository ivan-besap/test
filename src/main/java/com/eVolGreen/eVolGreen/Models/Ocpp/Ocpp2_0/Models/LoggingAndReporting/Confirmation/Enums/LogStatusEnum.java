package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Confirmation.Enums;

/**
 * Enumeración que indica si la estación de carga pudo aceptar la solicitud de logs.
 *
 * <p>Incluye los siguientes estados:
 * <ul>
 *   <li><b>Accepted:</b> La solicitud fue aceptada y procesada con éxito.</li>
 *   <li><b>Rejected:</b> La solicitud fue rechazada por la estación de carga.</li>
 *   <li><b>AcceptedCanceled:</b> La solicitud fue aceptada inicialmente, pero luego fue cancelada.</li>
 * </ul>
 */
public enum LogStatusEnum {
    /** La solicitud fue aceptada y procesada con éxito. */
    Accepted,
    /** La solicitud fue rechazada por la estación de carga. */
    Rejected,
    /** La solicitud fue aceptada inicialmente, pero luego fue cancelada. */
    AcceptedCanceled
}
