package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.ChangeAvailabilityFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.FunctionFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.NotifyEventFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.StatusNotificationFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.ChangeAvailabilityRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums.OperationalStatusEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.StatusNotificationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.NotifyEventRequest;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que gestiona solicitudes del servidor y creadores de solicitudes
 * para el bloque funcional de disponibilidad.
 *
 * <p>Esta clase proporciona la capacidad de manejar solicitudes como la notificación de eventos
 * o notificaciones de estado, y permite la creación de solicitudes desde el servidor.</p>
 */
public class ServerAvailabilityFunction implements Function {

    private final ServerAvailabilityEventHandler eventHandler;
    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor para inicializar el bloque funcional de disponibilidad del servidor.
     *
     * @param eventHandler Implementación del manejador de eventos del servidor para procesar solicitudes relacionadas con disponibilidad.
     */
    public ServerAvailabilityFunction(ServerAvailabilityEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        features = new ArrayList<>();
        features.add(new ChangeAvailabilityFeature(null));
        features.add(new NotifyEventFeature(this));
        features.add(new StatusNotificationFeature(this));
    }

    /**
     * Obtiene la lista de características soportadas por este bloque funcional.
     *
     * @return Un arreglo de características ({@link FunctionFeature}) soportadas.
     */
    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    /**
     * Maneja solicitudes entrantes recibidas desde un cliente OCPP.
     *
     * <p>Este método identifica el tipo de solicitud y la delega al manejador de eventos correspondiente.</p>
     *
     * @param sessionIndex Identificador único de la sesión donde se recibió la solicitud.
     * @param request       Instancia de la solicitud ({@link Request}) enviada por el cliente.
     * @return Una confirmación ({@link Confirmation}) en respuesta a la solicitud procesada.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        if (request instanceof NotifyEventRequest) {
            return eventHandler.handleNotifyEventRequest(sessionIndex, (NotifyEventRequest) request);
        } else if (request instanceof StatusNotificationRequest) {
            return eventHandler.handleStatusNotificationRequest(
                    sessionIndex, (StatusNotificationRequest) request);
        }
        return null;
    }

    /**
     * Crea una solicitud de cambio de disponibilidad ({@link ChangeAvailabilityRequest}) con los campos requeridos.
     *
     * <p>Este método genera una solicitud para cambiar la disponibilidad operacional de la estación de carga.</p>
     *
     * @param operationalStatus Estado operacional deseado para la estación de carga.
     * @return Una instancia de {@link ChangeAvailabilityRequest}.
     */
    public ChangeAvailabilityRequest createChangeAvailabilityRequest(
            OperationalStatusEnum operationalStatus) {
        return new ChangeAvailabilityRequest(operationalStatus);
    }
}
