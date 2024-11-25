package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation.Get15118EVCertificateResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation.GetCertificateStatusResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.Get15118EVCertificateRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.GetCertificateStatusRequest;

import java.util.UUID;

/**
 * Interfaz para manejar eventos del bloque funcional de gestión de certificados ISO 15118 en el servidor.
 * Esta interfaz define los métodos de devolución de llamada que permiten procesar las solicitudes
 * relacionadas con la gestión de certificados en sesiones específicas.
 */
public interface ServerISO15118CertificateManagementEventHandler {

    /**
     * Maneja una solicitud {@link Get15118EVCertificateRequest} y retorna una respuesta {@link Get15118EVCertificateResponse}.
     *
     * @param sessionIndex Identificador de la sesión en la que se recibió la solicitud.
     * @param request Solicitud entrante de tipo {@link Get15118EVCertificateRequest}.
     * @return Respuesta correspondiente de tipo {@link Get15118EVCertificateResponse}.
     */
    Get15118EVCertificateResponse handleGet15118EVCertificateRequest(
            UUID sessionIndex, Get15118EVCertificateRequest request);

    /**
     * Maneja una solicitud {@link GetCertificateStatusRequest} y retorna una respuesta {@link GetCertificateStatusResponse}.
     *
     * @param sessionIndex Identificador de la sesión en la que se recibió la solicitud.
     * @param request Solicitud entrante de tipo {@link GetCertificateStatusRequest}.
     * @return Respuesta correspondiente de tipo {@link GetCertificateStatusResponse}.
     */
    GetCertificateStatusResponse handleGetCertificateStatusRequest(
            UUID sessionIndex, GetCertificateStatusRequest request);
}
