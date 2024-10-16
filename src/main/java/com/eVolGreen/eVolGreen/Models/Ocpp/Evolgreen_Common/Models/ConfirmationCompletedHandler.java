package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models;

/**
 * Interfaz {@code ConfirmationCompletedHandler} que define una acción a realizar
 * después de enviar una confirmación a la estación de carga o backend OCPP.
 * <p>
 * Esta interfaz es útil cuando se necesita ejecutar alguna tarea adicional después de
 * que se envía la respuesta confirmando que la acción fue procesada correctamente.
 * Ejemplo de uso típico en la comunicación entre una estación de carga y su backend.
 */
public interface ConfirmationCompletedHandler {
    /**
     * Método que será ejecutado cuando la confirmación haya sido completada y enviada.
     * <p>
     * Este método debe ser implementado para realizar cualquier acción posterior
     * al envío de la confirmación, como el registro de logs o la activación de eventos.
     */
    void onConfirmationCompleted();
}
