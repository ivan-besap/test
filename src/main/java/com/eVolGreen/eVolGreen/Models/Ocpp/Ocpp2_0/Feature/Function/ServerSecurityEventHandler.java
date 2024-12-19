package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation.SecurityEventNotificationResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation.SignCertificateResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.SecurityEventNotificationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.SignCertificateRequest;

import java.util.UUID;

/**
 * Interfaz para manejar eventos del bloque funcional de Seguridad (Security) en el servidor.
 * Define los métodos para procesar solicitudes específicas de seguridad.
 */
public interface ServerSecurityEventHandler {

    /**
     * Maneja una solicitud de {@link SecurityEventNotificationRequest} y genera una respuesta
     * {@link SecurityEventNotificationResponse}.
     *
     * @param sessionIndex Identificador de la sesión donde se recibió la solicitud.
     * @param request Solicitud entrante {@link SecurityEventNotificationRequest}.
     * @return Respuesta {@link SecurityEventNotificationResponse} generada tras procesar la solicitud.
     */
    SecurityEventNotificationResponse handleSecurityEventNotificationRequest(
            UUID sessionIndex, SecurityEventNotificationRequest request);

    /**
     * Maneja una solicitud de {@link SignCertificateRequest} y genera una respuesta
     * {@link SignCertificateResponse}.
     *
     * @param sessionIndex Identificador de la sesión donde se recibió la solicitud.
     * @param request Solicitud entrante {@link SignCertificateRequest}.
     * @return Respuesta {@link SignCertificateResponse} generada tras procesar la solicitud.
     */
    SignCertificateResponse handleSignCertificateRequest(
            UUID sessionIndex, SignCertificateRequest request);
}
