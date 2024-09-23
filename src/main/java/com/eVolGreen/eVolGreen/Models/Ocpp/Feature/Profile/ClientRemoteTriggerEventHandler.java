package com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile;

import com.eVolGreen.eVolGreen.Models.Ocpp.Models.RemoteTrigger.TriggerMessageConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.RemoteTrigger.TriggerMessageRequest;

/**
 * La interfaz {@code ClientRemoteTriggerEventHandler} maneja los eventos relacionados con el envío
 * de solicitudes remotas para desencadenar acciones específicas en un punto de carga.
 * <p>
 * Este controlador es responsable de procesar las solicitudes entrantes de {@link TriggerMessageRequest}
 * y devolver las respuestas correspondientes utilizando {@link TriggerMessageConfirmation}.
 * <p>
 * Este es un componente clave en el manejo de solicitudes remotas dentro del protocolo OCPP.
 */
public interface ClientRemoteTriggerEventHandler {

    /**
     * Maneja una solicitud de mensaje disparador ({@link TriggerMessageRequest}) y devuelve una
     * confirmación de mensaje disparador ({@link TriggerMessageConfirmation}).
     *
     * @param request La solicitud de mensaje disparador recibida ({@link TriggerMessageRequest}).
     * @return La confirmación de mensaje disparador a enviar como respuesta ({@link TriggerMessageConfirmation}).
     */
    TriggerMessageConfirmation handleTriggerMessageRequest(TriggerMessageRequest request);
}
