package com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile;

import com.eVolGreen.eVolGreen.Models.Ocpp.Models.LocalAuthList.GetLocalListVersionConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.LocalAuthList.GetLocalListVersionRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.LocalAuthList.SendLocalListConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.LocalAuthList.SendLocalListRequest;

/**
 * La interfaz {@code ClientLocalAuthListEventHandler} maneja las solicitudes relacionadas
 * con la lista de autenticación local de un punto de carga.
 * Proporciona métodos para gestionar las solicitudes de obtención de la versión actual
 * de la lista local y el envío de una nueva lista.
 */
public interface ClientLocalAuthListEventHandler {

    /**
     * Maneja una solicitud {@link GetLocalListVersionRequest} para obtener la versión
     * actual de la lista local de autenticación.
     *
     * @param request Solicitud entrante {@link GetLocalListVersionRequest}.
     * @return Respuesta de confirmación {@link GetLocalListVersionConfirmation}.
     */
    GetLocalListVersionConfirmation handleGetLocalListVersionRequest(GetLocalListVersionRequest request);

    /**
     * Maneja una solicitud {@link SendLocalListRequest} para enviar una nueva lista de
     * autenticación local al punto de carga.
     *
     * @param request Solicitud entrante {@link SendLocalListRequest}.
     * @return Respuesta de confirmación {@link SendLocalListConfirmation}.
     */
    SendLocalListConfirmation handleSendLocalListRequest(SendLocalListRequest request);
}
