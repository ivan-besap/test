package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums;

/**
 * Representa el tipo de reinicio que debe realizar una estación de carga o un EVSE específico.
 *
 * <p>Este enumerado define los posibles estados para realizar un reinicio en el contexto de OCPP 2.0.1.
 */
public enum ResetEnum {
    /**
     * Reinicio inmediato.
     *
     * <p>La estación de carga o el EVSE debe realizar un reinicio inmediatamente después de recibir
     * la solicitud.
     */
    Immediate,

    /**
     * Reinicio en estado inactivo.
     *
     * <p>La estación de carga o el EVSE realizará el reinicio únicamente cuando no esté en uso y se
     * encuentre en un estado inactivo.
     */
    OnIdle
}
