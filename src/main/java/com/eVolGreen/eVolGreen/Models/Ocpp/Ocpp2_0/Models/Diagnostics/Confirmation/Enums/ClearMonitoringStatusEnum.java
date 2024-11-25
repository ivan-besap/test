package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.Enums;

/**
 * Resultado de la solicitud de eliminación para un monitor, identificado por su ID.
 *
 * <p>Indica el estado de la operación al intentar borrar un monitor específico.</p>
 */
public enum ClearMonitoringStatusEnum {
    /** La operación fue aceptada y el monitor fue eliminado correctamente. */
    Aceptado,

    /** La operación fue rechazada. */
    Rechazado,

    /** No se encontró el monitor solicitado. */
    NoEncontrado
}
