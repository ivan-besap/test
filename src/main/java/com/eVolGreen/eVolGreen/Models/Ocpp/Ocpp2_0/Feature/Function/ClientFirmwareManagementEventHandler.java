package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Confirmation.PublishFirmwareResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Confirmation.UnpublishFirmwareResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Confirmation.UpdateFirmwareResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Requests.PublishFirmwareRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Requests.UnpublishFirmwareRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Requests.UpdateFirmwareRequest;

/**
 * Interfaz para manejar eventos del cliente relacionados con la gestión de firmware
 * dentro del bloque funcional de FirmwareManagement.
 */
public interface ClientFirmwareManagementEventHandler {

    /**
     * Maneja una solicitud {@link PublishFirmwareRequest} y devuelve una respuesta {@link PublishFirmwareResponse}.
     *
     * @param request La solicitud entrante para publicar un firmware.
     * @return La respuesta saliente indicando el estado de la publicación del firmware.
     */
    PublishFirmwareResponse handlePublishFirmwareRequest(PublishFirmwareRequest request);

    /**
     * Maneja una solicitud {@link UnpublishFirmwareRequest} y devuelve una respuesta {@link UnpublishFirmwareResponse}.
     *
     * @param request La solicitud entrante para despublicar un firmware.
     * @return La respuesta saliente indicando el estado de la operación de despublicación.
     */
    UnpublishFirmwareResponse handleUnpublishFirmwareRequest(UnpublishFirmwareRequest request);

    /**
     * Maneja una solicitud {@link UpdateFirmwareRequest} y devuelve una respuesta {@link UpdateFirmwareResponse}.
     *
     * @param request La solicitud entrante para actualizar el firmware.
     * @return La respuesta saliente indicando el estado de la operación de actualización del firmware.
     */
    UpdateFirmwareResponse handleUpdateFirmwareRequest(UpdateFirmwareRequest request);
}
