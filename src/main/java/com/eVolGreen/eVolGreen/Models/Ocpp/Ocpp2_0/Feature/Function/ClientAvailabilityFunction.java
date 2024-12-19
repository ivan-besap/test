package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.ChangeAvailabilityFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.FunctionFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.NotifyEventFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.StatusNotificationFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.ChangeAvailabilityRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums.ConnectorStatusEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.StatusNotificationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.NotifyEventRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils.EventData;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase con métodos para crear solicitudes y manejar eventos relacionados con la funcionalidad de disponibilidad.
 *
 * <p>`ClientAvailabilityFunction` permite manejar solicitudes entrantes y generar solicitudes salientes
 * relacionadas con el estado de disponibilidad y eventos de estaciones de carga.
 */
public class ClientAvailabilityFunction implements Function {

    private final ClientAvailabilityEventHandler eventHandler;
    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor de la clase `ClientAvailabilityFunction`.
     *
     * @param eventHandler Implementación de la interfaz {@link ClientAvailabilityEventHandler} para manejar eventos de disponibilidad.
     */
    public ClientAvailabilityFunction(ClientAvailabilityEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        features = new ArrayList<>();
        features.add(new ChangeAvailabilityFeature(this));
        features.add(new NotifyEventFeature(null));
        features.add(new StatusNotificationFeature(null));
    }

    /**
     * Devuelve la lista de características relacionadas con la disponibilidad.
     *
     * @return Un arreglo de características de tipo {@link FunctionFeature}.
     */
    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    /**
     * Maneja solicitudes entrantes relacionadas con la disponibilidad.
     *
     * @param sessionIndex Identificador de la sesión activa.
     * @param request Solicitud entrante de tipo {@link Request}.
     * @return Una respuesta de tipo {@link Confirmation}, o null si el tipo de solicitud no es compatible.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        if (request instanceof ChangeAvailabilityRequest) {
            return eventHandler.handleChangeAvailabilityRequest((ChangeAvailabilityRequest) request);
        }
        return null;
    }

    /**
     * Crea una solicitud de tipo {@link NotifyEventRequest} con todos los campos requeridos.
     *
     * @param generatedAt Marca de tiempo que indica el momento en que se generó el mensaje.
     * @param seqNo Número de secuencia del mensaje (el primer mensaje comienza en 0).
     * @param eventData Datos del evento que reporta una notificación para un componente-variable.
     * @return Una instancia de {@link NotifyEventRequest}.
     */
    public NotifyEventRequest createNotifyEventRequest(
            ZonedDateTime generatedAt, Integer seqNo, EventData[] eventData) {
        return new NotifyEventRequest(generatedAt, seqNo, eventData);
    }

    /**
     * Crea una solicitud de tipo {@link StatusNotificationRequest} con todos los campos requeridos.
     *
     * @param timestamp Marca de tiempo que indica el momento en que se reporta el estado.
     * @param connectorStatus Estado actual del conector (por ejemplo, `Available`, `Occupied`).
     * @param evseId Identificador del EVSE al que pertenece el conector.
     * @param connectorId Identificador del conector dentro del EVSE para el cual se reporta el estado.
     * @return Una instancia de {@link StatusNotificationRequest}.
     */
    public StatusNotificationRequest createStatusNotificationRequest(
            ZonedDateTime timestamp,
            ConnectorStatusEnum connectorStatus,
            Integer evseId,
            Integer connectorId) {
        return new StatusNotificationRequest(timestamp, connectorStatus, evseId, connectorId);
    }
}
