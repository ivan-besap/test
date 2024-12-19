package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Utils.OCSPRequestData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.DeleteCertificateRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.Enums.CertificateActionEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.Get15118EVCertificateRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.GetCertificateStatusRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.GetInstalledCertificateIdsRequest;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que gestiona la creación de solicitudes y el manejo de respuestas para el bloque funcional
 * de gestión de certificados ISO15118.
 */
public class ClientISO15118CertificateManagementFunction implements Function {

    private final ClientISO15118CertificateManagementEventHandler eventHandler;
    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor para inicializar el manejador de eventos y registrar las características (features)
     * relacionadas con la gestión de certificados ISO15118.
     *
     * @param eventHandler El manejador de eventos para procesar solicitudes entrantes.
     */
    public ClientISO15118CertificateManagementFunction(
            ClientISO15118CertificateManagementEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        features = new ArrayList<>();
        features.add(new DeleteCertificateFeature(this));
        features.add(new Get15118EVCertificateFeature(null));
        features.add(new GetCertificateStatusFeature(null));
        features.add(new GetInstalledCertificateIdsFeature(this));
    }

    /**
     * Obtiene la lista de características (features) soportadas por esta función.
     *
     * @return Un arreglo de objetos {@link FunctionFeature} que representan las funcionalidades
     * disponibles.
     */
    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    /**
     * Maneja solicitudes entrantes basadas en su tipo y devuelve una confirmación correspondiente.
     *
     * @param sessionIndex El identificador de la sesión en la que se recibió la solicitud.
     * @param request La solicitud entrante a manejar.
     * @return Una instancia de {@link Confirmation} que responde a la solicitud, o {@code null} si
     * la solicitud no es reconocida.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        if (request instanceof DeleteCertificateRequest) {
            return eventHandler.handleDeleteCertificateRequest((DeleteCertificateRequest) request);
        } else if (request instanceof GetInstalledCertificateIdsRequest) {
            return eventHandler.handleGetInstalledCertificateIdsRequest(
                    (GetInstalledCertificateIdsRequest) request);
        }
        return null;
    }

    /**
     * Crea una solicitud {@link Get15118EVCertificateRequest} con los campos requeridos.
     *
     * @param iso15118SchemaVersion Versión del esquema ISO15118 utilizada para la sesión actual entre
     *                              el EV y la estación de carga. Necesaria para interpretar el flujo EXI.
     * @param action Acción que indica si el certificado debe ser instalado o actualizado.
     * @param exiRequest Solicitud cruda de instalación de certificado enviada por el EV, codificada en Base64.
     * @return Una instancia de {@link Get15118EVCertificateRequest}.
     */
    public Get15118EVCertificateRequest createGet15118EVCertificateRequest(
            String iso15118SchemaVersion, CertificateActionEnum action, String exiRequest) {
        return new Get15118EVCertificateRequest(iso15118SchemaVersion, action, exiRequest);
    }

    /**
     * Crea una solicitud {@link GetCertificateStatusRequest} con los campos requeridos.
     *
     * @param ocspRequestData Datos OCSP para la validación del estado del certificado.
     * @return Una instancia de {@link GetCertificateStatusRequest}.
     */
    public GetCertificateStatusRequest createGetCertificateStatusRequest(
            OCSPRequestData ocspRequestData) {
        return new GetCertificateStatusRequest(ocspRequestData);
    }
}
