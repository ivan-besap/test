package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Confirmation.Enums;

/**
 * Indica si la estación de carga tiene mensajes de pantalla que coinciden con los criterios
 * solicitados en el mensaje GetDisplayMessagesRequest.
 */
public enum GetDisplayMessagesStatusEnum {
    /** Solicitud aceptada, hay mensajes que coinciden con los criterios. */
    Accepted,

    /** No se encontraron mensajes que coincidan con los criterios. */
    Unknown
}
