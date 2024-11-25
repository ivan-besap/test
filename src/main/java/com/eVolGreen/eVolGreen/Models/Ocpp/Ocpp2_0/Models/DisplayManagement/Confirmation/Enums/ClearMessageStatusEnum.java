package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Confirmation.Enums;

/**
 * Estado de la eliminación del mensaje mostrado en la estación de carga.
 *
 * Indica si la estación de carga ha podido eliminar el mensaje solicitado.
 */
public enum ClearMessageStatusEnum {

    /**
     * La estación de carga ha eliminado el mensaje exitosamente.
     */
    Aceptado,

    /**
     * El mensaje no pudo ser identificado o no existe en la estación de carga.
     */
    Desconocido
}
