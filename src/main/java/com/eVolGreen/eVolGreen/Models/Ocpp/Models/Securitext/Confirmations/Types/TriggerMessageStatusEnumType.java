package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Confirmations.Types;


import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Confirmations.ExtendedTriggerMessageConfirmation;

/**
 * Enumeración que define los posibles estados en respuesta a una solicitud para activar un mensaje.
 * <p>
 * Esta enumeración es utilizada en {@link ExtendedTriggerMessageConfirmation} para indicar el estado
 * de una solicitud de mensaje activado.
 * </p>
 *
 * <p><b>Valores posibles:</b></p>
 * <ul>
 *     <li>{@link #Accepted}: El mensaje solicitado será enviado.</li>
 *     <li>{@link #Rejected}: El mensaje solicitado no será enviado.</li>
 *     <li>{@link #NotImplemented}: El mensaje solicitado no puede ser enviado porque no está implementado o es desconocido.</li>
 * </ul>
 */
public enum TriggerMessageStatusEnumType {

    /**
     * El mensaje solicitado será enviado.
     */
    Accepted,

    /**
     * El mensaje solicitado no será enviado.
     */
    Rejected,

    /**
     * El mensaje solicitado no puede ser enviado porque no está implementado o es desconocido.
     */
    NotImplemented
}
