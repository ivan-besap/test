package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities;


import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;

import java.util.function.BiConsumer;

/**
 * Clase que maneja las respuestas de confirmación o errores en las solicitudes enviadas.
 * Implementa la interfaz {@link BiConsumer} para procesar la confirmación o capturar
 * posibles errores durante la ejecución.
 */
class ConfirmationHandler implements BiConsumer<Confirmation, Throwable> {

    private String id;
    private String action;
    private Communicator communicator;

    /**
     * Constructor de la clase ConfirmationHandler.
     *
     * @param id el identificador único de la solicitud.
     * @param action la acción solicitada.
     * @param communicator el objeto {@link Communicator} que gestiona el envío de las respuestas.
     */
    public ConfirmationHandler(String id, String action, Communicator communicator) {
        this.id = id;
        this.action = action;
        this.communicator = communicator;
    }

    /**
     * Método que se ejecuta cuando se recibe una confirmación o un error.
     * Si ocurre un error, envía una respuesta de error. Si la confirmación es nula,
     * también envía un error indicando que la acción no es compatible.
     *
     * @param confirmation el objeto {@link Confirmation} recibido como respuesta.
     * @param throwable el error que puede haber ocurrido durante la solicitud.
     */
    @Override
    public void accept(Confirmation confirmation, Throwable throwable) {
        if (throwable != null) {
            communicator.sendCallError(
                    id,
                    action,
                    "InternalError",
                    "An internal error occurred and the receiver was not able to process the requested Action successfully");
        } else if (confirmation == null) {
            communicator.sendCallError(
                    id,
                    action,
                    "NotSupported",
                    "Requested Action is recognized but not supported by the receiver");
        } else {
            communicator.sendCallResult(id, action, confirmation);
        }
    }
}
