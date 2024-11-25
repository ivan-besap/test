package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests.ClearDisplayMessageRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests.GetDisplayMessagesRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests.NotifyDisplayMessagesRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests.SetDisplayMessageRequest;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que proporciona métodos para la creación de solicitudes del cliente y el manejo
 * de respuestas relacionadas con el bloque funcional de mensajes de pantalla (DisplayMessage).
 */
public class ClientDisplayMessageFunction implements Function {

    private final ClientDisplayMessageEventHandler eventHandler;
    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor para inicializar el manejador de eventos y registrar las características
     * relacionadas con los mensajes de pantalla.
     *
     * @param eventHandler El manejador de eventos para procesar solicitudes relacionadas con mensajes de pantalla.
     */
    public ClientDisplayMessageFunction(ClientDisplayMessageEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        features = new ArrayList<>();
        features.add(new ClearDisplayMessageFeature(this));
        features.add(new GetDisplayMessagesFeature(this));
        features.add(new NotifyDisplayMessagesFeature(null));
        features.add(new SetDisplayMessageFeature(this));
    }

    /**
     * Obtiene la lista de características disponibles para este bloque funcional.
     *
     * @return Un arreglo de {@link FunctionFeature} que contiene las características implementadas.
     */
    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    /**
     * Maneja solicitudes entrantes y las redirige al manejador de eventos correspondiente.
     *
     * @param sessionIndex El identificador de la sesión del cliente que envía la solicitud.
     * @param request La solicitud entrante que será procesada.
     * @return Una instancia de {@link Confirmation} con la respuesta correspondiente.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        if (request instanceof ClearDisplayMessageRequest) {
            return eventHandler.handleClearDisplayMessageRequest((ClearDisplayMessageRequest) request);
        } else if (request instanceof GetDisplayMessagesRequest) {
            return eventHandler.handleGetDisplayMessagesRequest((GetDisplayMessagesRequest) request);
        } else if (request instanceof SetDisplayMessageRequest) {
            return eventHandler.handleSetDisplayMessageRequest((SetDisplayMessageRequest) request);
        }
        return null;
    }

    /**
     * Crea una solicitud {@link NotifyDisplayMessagesRequest} con todos los campos obligatorios.
     *
     * @param requestId El identificador de la solicitud {@link GetDisplayMessagesRequest} que originó este mensaje.
     * @return Una instancia de {@link NotifyDisplayMessagesRequest}.
     */
    public NotifyDisplayMessagesRequest createNotifyDisplayMessagesRequest(Integer requestId) {
        return new NotifyDisplayMessagesRequest(requestId);
    }
}
