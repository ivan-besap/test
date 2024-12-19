package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests.Enums;

/**
 * Representa la prioridad con la que un mensaje debe mostrarse en la estación de carga.
 *
 * <p>Permite especificar el orden o la importancia de los mensajes mostrados en la interfaz de usuario.
 */
public enum MessagePriorityEnum {

    /**
     * El mensaje debe mostrarse siempre en la parte delantera, con la más alta prioridad.
     */
    AlwaysFront,

    /**
     * El mensaje debe mostrarse en la parte delantera, pero con menos prioridad que "AlwaysFront".
     */
    InFront,

    /**
     * El mensaje debe mostrarse en un ciclo normal, sin prioridad especial.
     */
    NormalCycle
}
