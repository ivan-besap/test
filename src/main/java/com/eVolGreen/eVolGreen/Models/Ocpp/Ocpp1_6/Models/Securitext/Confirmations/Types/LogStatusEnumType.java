package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations.Types;


import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations.GetLogConfirmation;

/**
 * Enumeración que define los posibles estados de la carga de un log en el sistema OCPP.
 *
 * <p>Esta enumeración se utiliza en {@link GetLogConfirmation} para indicar el resultado de la solicitud
 * de carga de un archivo de log. Los estados pueden ser aceptación de la solicitud, rechazo, o
 * aceptación con cancelación de una carga en curso.</p>
 */
public enum LogStatusEnumType {

    /**
     * La solicitud de carga de log fue aceptada. Esto no implica que la carga fue exitosa,
     * solo que la estación de carga comenzará a subir el archivo de log.
     */
    Accepted,

    /**
     * La solicitud de actualización del log fue rechazada.
     */
    Rejected,

    /**
     * La solicitud de carga fue aceptada, pero al hacerlo se canceló una carga de log en curso.
     */
    AcceptedCanceled
}
