package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Confirmation.Enums;

/**
 * Enumera los posibles estados del procesamiento de una notificación de necesidades de carga de un vehículo eléctrico.
 *
 * <p>Indica si el sistema de gestión de la estación de carga (CSMS) ha podido procesar
 * exitosamente el mensaje. Esto no implica que las necesidades de carga puedan ser satisfechas
 * con el perfil de carga actual.
 */
public enum NotifyEVChargingNeedsStatusEnum {
    /** El mensaje ha sido aceptado y procesado exitosamente por el CSMS. */
    Accepted,

    /** El mensaje ha sido rechazado por el CSMS. */
    Rejected,

    /** El CSMS está procesando actualmente el mensaje. */
    Processing
}
