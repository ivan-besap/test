package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Confirmation.ClearDisplayMessageResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Confirmation.GetDisplayMessagesResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Confirmation.SetDisplayMessageResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests.ClearDisplayMessageRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests.GetDisplayMessagesRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests.SetDisplayMessageRequest;

/**
 * Interfaz para manejar eventos relacionados con mensajes de pantalla en el cliente OCPP.
 *
 * <p>Define los métodos para procesar solicitudes de mensajes de pantalla, como limpiar,
 * obtener o establecer mensajes en la estación de carga.
 */
public interface ClientDisplayMessageEventHandler {

    /**
     * Maneja una solicitud {@link ClearDisplayMessageRequest} para borrar un mensaje de pantalla.
     *
     * @param request La solicitud entrante para borrar un mensaje de pantalla.
     * @return Una respuesta {@link ClearDisplayMessageResponse} con el resultado de la operación.
     */
    ClearDisplayMessageResponse handleClearDisplayMessageRequest(ClearDisplayMessageRequest request);

    /**
     * Maneja una solicitud {@link GetDisplayMessagesRequest} para obtener mensajes de pantalla.
     *
     * @param request La solicitud entrante para recuperar mensajes de pantalla.
     * @return Una respuesta {@link GetDisplayMessagesResponse} con los mensajes recuperados.
     */
    GetDisplayMessagesResponse handleGetDisplayMessagesRequest(GetDisplayMessagesRequest request);

    /**
     * Maneja una solicitud {@link SetDisplayMessageRequest} para establecer un nuevo mensaje en la pantalla.
     *
     * @param request La solicitud entrante para configurar un mensaje de pantalla.
     * @return Una respuesta {@link SetDisplayMessageResponse} con el resultado de la operación.
     */
    SetDisplayMessageResponse handleSetDisplayMessageRequest(SetDisplayMessageRequest request);
}

