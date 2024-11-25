package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Requests.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Requests.Enums.FirmwareStatusEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Requests.Enums.PublishFirmwareStatusEnum;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que implementa creadores de solicitudes del cliente y manejadores
 * para el bloque funcional de gestión de firmware (FirmwareManagement).
 */
public class ClientFirmwareManagementFunction implements Function {

    private final ClientFirmwareManagementEventHandler eventHandler;
    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor para inicializar el manejador de eventos y registrar las características soportadas.
     *
     * @param eventHandler Manejador de eventos que procesará las solicitudes relacionadas con el firmware.
     */
    public ClientFirmwareManagementFunction(ClientFirmwareManagementEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        features = new ArrayList<>();
        features.add(new FirmwareStatusNotificationFeature(null));
        features.add(new PublishFirmwareFeature(this));
        features.add(new PublishFirmwareStatusNotificationFeature(null));
        features.add(new UnpublishFirmwareFeature(this));
        features.add(new UpdateFirmwareFeature(this));
    }

    /**
     * Obtiene la lista de características soportadas por el bloque funcional.
     *
     * @return Un arreglo de {@link FunctionFeature} representando las características soportadas.
     */
    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    /**
     * Maneja solicitudes entrantes del cliente, delegándolas al manejador de eventos correspondiente.
     *
     * @param sessionIndex Identificador de la sesión en la que se recibió la solicitud.
     * @param request      La solicitud entrante que debe manejarse.
     * @return Una confirmación {@link Confirmation} en respuesta a la solicitud procesada.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        if (request instanceof PublishFirmwareRequest) {
            return eventHandler.handlePublishFirmwareRequest((PublishFirmwareRequest) request);
        } else if (request instanceof UnpublishFirmwareRequest) {
            return eventHandler.handleUnpublishFirmwareRequest((UnpublishFirmwareRequest) request);
        } else if (request instanceof UpdateFirmwareRequest) {
            return eventHandler.handleUpdateFirmwareRequest((UpdateFirmwareRequest) request);
        }
        return null;
    }

    /**
     * Crea una solicitud {@link FirmwareStatusNotificationRequest} con los campos requeridos.
     *
     * @param status El estado del progreso de la instalación del firmware.
     * @return Una instancia de {@link FirmwareStatusNotificationRequest}.
     */
    public FirmwareStatusNotificationRequest createFirmwareStatusNotificationRequest(
            FirmwareStatusEnum status) {
        return new FirmwareStatusNotificationRequest(status);
    }

    /**
     * Crea una solicitud {@link PublishFirmwareStatusNotificationRequest} con los campos requeridos.
     *
     * @param status El estado del progreso de la publicación del firmware.
     * @return Una instancia de {@link PublishFirmwareStatusNotificationRequest}.
     */
    public PublishFirmwareStatusNotificationRequest createPublishFirmwareStatusNotificationRequest(
            PublishFirmwareStatusEnum status) {
        return new PublishFirmwareStatusNotificationRequest(status);
    }
}
