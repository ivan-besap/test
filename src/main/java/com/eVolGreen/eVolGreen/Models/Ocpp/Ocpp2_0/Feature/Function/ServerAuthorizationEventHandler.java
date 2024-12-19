package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.AuthorizeResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.AuthorizeRequest;

import java.util.UUID;

/**
 * Interfaz para manejar eventos de servidor relacionados con el bloque funcional de autorización.
 *
 * <p>Define los métodos necesarios para procesar solicitudes de autorización recibidas por parte
 * del servidor y generar las respuestas correspondientes.</p>
 */
public interface ServerAuthorizationEventHandler {

    /**
     * Procesa una solicitud de autorización {@link AuthorizeRequest} y genera una respuesta {@link AuthorizeResponse}.
     *
     * <p>Este método se invoca cuando el servidor recibe una solicitud de autorización en una sesión específica.
     * Se espera que la implementación valide la solicitud y devuelva una respuesta adecuada.</p>
     *
     * @param sessionIndex El identificador único de la sesión en la que se recibió la solicitud.
     *                     Este identificador permite correlacionar las solicitudes con las sesiones activas.
     * @param request       La solicitud de autorización entrante que debe ser procesada.
     * @return Una instancia de {@link AuthorizeResponse} que contiene el resultado de la operación de autorización.
     */
    AuthorizeResponse handleAuthorizeRequest(UUID sessionIndex, AuthorizeRequest request);
}
