package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests.Enums;

/**
 * Representa los estados de la estación de carga durante los cuales un mensaje debe ser mostrado.
 *
 * <p>Si no se especifica un estado, el mensaje se mostrará en cualquier estado de la estación de carga.
 */
public enum MessageStateEnum {

    /**
     * El mensaje debe mostrarse cuando la estación de carga está en el estado de carga activa.
     */
    Charging,

    /**
     * El mensaje debe mostrarse cuando la estación de carga está en estado de fallo o error.
     */
    Faulted,

    /**
     * El mensaje debe mostrarse cuando la estación de carga está en estado inactivo, sin cargar.
     */
    Idle,

    /**
     * El mensaje debe mostrarse cuando la estación de carga está en estado no disponible para uso.
     */
    Unavailable
}
