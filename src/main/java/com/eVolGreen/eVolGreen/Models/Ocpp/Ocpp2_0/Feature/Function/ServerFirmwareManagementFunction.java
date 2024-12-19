package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Requests.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Requests.Utils.Firmware;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que implementa creadores de solicitudes y manejadores para los eventos relacionados con
 * el bloque funcional de gestión de firmware (FirmwareManagement) del servidor.
 */
public class ServerFirmwareManagementFunction implements Function {

    private final ServerFirmwareManagementEventHandler eventHandler;
    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor para inicializar el manejador de eventos y registrar las características del bloque funcional.
     *
     * @param eventHandler Manejador de eventos específico para gestionar las solicitudes relacionadas
     *                     con la gestión de firmware.
     */
    public ServerFirmwareManagementFunction(ServerFirmwareManagementEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        features = new ArrayList<>();
        features.add(new FirmwareStatusNotificationFeature(this));
        features.add(new PublishFirmwareFeature(null));
        features.add(new PublishFirmwareStatusNotificationFeature(this));
        features.add(new UnpublishFirmwareFeature(null));
        features.add(new UpdateFirmwareFeature(null));
    }

    /**
     * Obtiene la lista de características soportadas por este bloque funcional.
     *
     * @return Un arreglo de las características ({@link FunctionFeature}) disponibles.
     */
    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    /**
     * Maneja solicitudes entrantes asociadas al bloque funcional de gestión de firmware.
     *
     * @param sessionIndex Identificador de la sesión en la que se recibió la solicitud.
     * @param request Solicitud entrante a procesar.
     * @return La confirmación ({@link Confirmation}) correspondiente a la solicitud procesada.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        if (request instanceof FirmwareStatusNotificationRequest) {
            return eventHandler.handleFirmwareStatusNotificationRequest(
                    sessionIndex, (FirmwareStatusNotificationRequest) request);
        } else if (request instanceof PublishFirmwareStatusNotificationRequest) {
            return eventHandler.handlePublishFirmwareStatusNotificationRequest(
                    sessionIndex, (PublishFirmwareStatusNotificationRequest) request);
        }
        return null;
    }

    /**
     * Crea una solicitud {@link PublishFirmwareRequest} con todos los campos obligatorios.
     *
     * @param location URI que apunta a la ubicación desde donde se puede recuperar el firmware.
     * @param checksum Suma de verificación MD5 del archivo de firmware como una cadena hexadecimal de 32 caracteres.
     * @param requestId Identificador único de la solicitud.
     * @return Una instancia de {@link PublishFirmwareRequest}.
     */
    public PublishFirmwareRequest createPublishFirmwareRequest(
            String location, String checksum, Integer requestId) {
        return new PublishFirmwareRequest(location, checksum, requestId);
    }

    /**
     * Crea una solicitud {@link UnpublishFirmwareRequest} con todos los campos obligatorios.
     *
     * @param checksum Suma de verificación MD5 del archivo de firmware como una cadena hexadecimal de 32 caracteres.
     * @return Una instancia de {@link UnpublishFirmwareRequest}.
     */
    public UnpublishFirmwareRequest createUnpublishFirmwareRequest(String checksum) {
        return new UnpublishFirmwareRequest(checksum);
    }

    /**
     * Crea una solicitud {@link UpdateFirmwareRequest} con todos los campos obligatorios.
     *
     * @param requestId Identificador único de la solicitud.
     * @param firmware Objeto {@link Firmware} que contiene los datos del firmware a actualizar.
     * @return Una instancia de {@link UpdateFirmwareRequest}.
     */
    public UpdateFirmwareRequest createUpdateFirmwareRequest(Integer requestId, Firmware firmware) {
        return new UpdateFirmwareRequest(requestId, firmware);
    }
}
