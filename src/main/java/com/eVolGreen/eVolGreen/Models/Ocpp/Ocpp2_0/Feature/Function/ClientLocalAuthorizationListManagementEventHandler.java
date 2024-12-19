package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.SendLocalListResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.SendLocalListRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Confirmation.GetLocalListVersionResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests.GetLocalListVersionRequest;

/**
 * Interfaz que define los métodos de manejo de eventos para el bloque funcional de gestión de la
 * lista de autorizaciones locales.
 */
public interface ClientLocalAuthorizationListManagementEventHandler {

    /**
     * Maneja una solicitud entrante de tipo {@link GetLocalListVersionRequest} y devuelve una
     * respuesta de tipo {@link GetLocalListVersionResponse}.
     *
     * @param request La solicitud entrante que solicita la versión actual de la lista de autorizaciones locales.
     * @return Una respuesta {@link GetLocalListVersionResponse} con la versión de la lista.
     */
    GetLocalListVersionResponse handleGetLocalListVersionRequest(GetLocalListVersionRequest request);

    /**
     * Maneja una solicitud entrante de tipo {@link SendLocalListRequest} y devuelve una
     * respuesta de tipo {@link SendLocalListResponse}.
     *
     * @param request La solicitud entrante que envía una lista de autorizaciones locales para ser actualizada o reemplazada.
     * @return Una respuesta {@link SendLocalListResponse} que confirma el resultado del envío de la lista.
     */
    SendLocalListResponse handleSendLocalListRequest(SendLocalListRequest request);
}
