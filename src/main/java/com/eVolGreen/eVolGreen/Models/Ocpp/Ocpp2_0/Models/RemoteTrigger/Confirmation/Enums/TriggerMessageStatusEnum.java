package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.RemoteTrigger.Confirmation.Enums;

/**
 * Estado del mensaje TriggerMessage
 *
 * <p>Indica si la estación de carga enviará o no la notificación solicitada en respuesta a un mensaje TriggerMessage.
 */
public enum TriggerMessageStatusEnum {
    /**
     * La estación de carga enviará la notificación solicitada.
     */
    Accepted,

    /**
     * La estación de carga no enviará la notificación solicitada.
     */
    Rejected,

    /**
     * La funcionalidad solicitada no está implementada en la estación de carga.
     */
    NotImplemented
}
