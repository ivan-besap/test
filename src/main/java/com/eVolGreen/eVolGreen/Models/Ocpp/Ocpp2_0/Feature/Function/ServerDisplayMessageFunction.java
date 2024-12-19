package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests.ClearDisplayMessageRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests.GetDisplayMessagesRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests.NotifyDisplayMessagesRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests.SetDisplayMessageRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests.Utils.MessageInfo;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que implementa los métodos necesarios para manejar solicitudes y respuestas
 * relacionadas con el bloque funcional de mensajes de visualización en el servidor.
 *
 * <p>Incluye la capacidad de manejar solicitudes de notificación de mensajes y generar
 * solicitudes desde el servidor.</p>
 */
public class ServerDisplayMessageFunction implements Function {

    private final ServerDisplayMessageEventHandler eventHandler;
    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor de la clase que inicializa el manejador de eventos y las características.
     *
     * @param eventHandler Instancia del manejador de eventos para procesar solicitudes
     *                     relacionadas con mensajes de visualización.
     */
    public ServerDisplayMessageFunction(ServerDisplayMessageEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        features = new ArrayList<>();
        features.add(new ClearDisplayMessageFeature(null));
        features.add(new GetDisplayMessagesFeature(null));
        features.add(new NotifyDisplayMessagesFeature(this));
        features.add(new SetDisplayMessageFeature(null));
    }

    /**
     * Obtiene la lista de características soportadas por esta función.
     *
     * @return Un array de {@link FunctionFeature} que representa las características soportadas.
     */
    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    /**
     * Maneja una solicitud recibida desde una estación de carga.
     *
     * @param sessionIndex Identificador único de la sesión en la que se recibió la solicitud.
     * @param request       Instancia de la solicitud {@link Request}.
     * @return Una instancia de {@link Confirmation} que representa la respuesta al cliente,
     *         o {@code null} si la solicitud no es soportada.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        if (request instanceof NotifyDisplayMessagesRequest) {
            return eventHandler.handleNotifyDisplayMessagesRequest(
                    sessionIndex, (NotifyDisplayMessagesRequest) request);
        }
        return null;
    }

    /**
     * Crea una solicitud de borrado de mensajes de visualización desde el servidor.
     *
     * @param id Identificador del mensaje que debe ser eliminado de la estación de carga.
     * @return Una instancia de {@link ClearDisplayMessageRequest}.
     */
    public ClearDisplayMessageRequest createClearDisplayMessageRequest(Integer id) {
        return new ClearDisplayMessageRequest(id);
    }

    /**
     * Crea una solicitud para obtener mensajes de visualización desde el servidor.
     *
     * @param requestId Identificador único de esta solicitud.
     * @return Una instancia de {@link GetDisplayMessagesRequest}.
     */
    public GetDisplayMessagesRequest createGetDisplayMessagesRequest(Integer requestId) {
        return new GetDisplayMessagesRequest(requestId);
    }

    /**
     * Crea una solicitud para establecer un mensaje de visualización desde el servidor.
     *
     * @param message Detalles del mensaje que se debe mostrar en la estación de carga.
     * @return Una instancia de {@link SetDisplayMessageRequest}.
     */
    public SetDisplayMessageRequest createSetDisplayMessageRequest(MessageInfo message) {
        return new SetDisplayMessageRequest(message);
    }
}
