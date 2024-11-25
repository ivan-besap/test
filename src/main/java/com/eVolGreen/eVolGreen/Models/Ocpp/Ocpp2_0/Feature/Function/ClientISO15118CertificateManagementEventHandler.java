package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation.DeleteCertificateResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation.GetInstalledCertificateIdsResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.DeleteCertificateRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.GetInstalledCertificateIdsRequest;

/**
 * Interfaz que define el manejador de eventos para el bloque funcional de
 * gestión de certificados ISO15118 (ISO15118CertificateManagement).
 *
 * Proporciona métodos para manejar solicitudes relacionadas con la gestión
 * de certificados ISO15118 en el cliente.
 */
public interface ClientISO15118CertificateManagementEventHandler {

    /**
     * Maneja una solicitud {@link DeleteCertificateRequest} para eliminar un certificado instalado
     * y devuelve una respuesta {@link DeleteCertificateResponse}.
     *
     * @param request La solicitud entrante para eliminar un certificado.
     * @return Una respuesta {@link DeleteCertificateResponse} que confirma el resultado de la operación.
     */
    DeleteCertificateResponse handleDeleteCertificateRequest(DeleteCertificateRequest request);

    /**
     * Maneja una solicitud {@link GetInstalledCertificateIdsRequest} para obtener los identificadores
     * de los certificados instalados y devuelve una respuesta {@link GetInstalledCertificateIdsResponse}.
     *
     * @param request La solicitud entrante para recuperar los identificadores de certificados instalados.
     * @return Una respuesta {@link GetInstalledCertificateIdsResponse} que contiene los identificadores de los certificados instalados.
     */
    GetInstalledCertificateIdsResponse handleGetInstalledCertificateIdsRequest(
            GetInstalledCertificateIdsRequest request);
}

