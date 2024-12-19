package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.CertificateSignedRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.Enums.InstallCertificateUseEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.InstallCertificateRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.SecurityEventNotificationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.SignCertificateRequest;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que gestiona las solicitudes y respuestas relacionadas con el bloque funcional de Seguridad.
 * Este bloque funcional abarca funcionalidades como la instalación de certificados,
 * notificaciones de eventos de seguridad y firma de certificados.
 */
public class ServerSecurityFunction implements Function {

    private final ServerSecurityEventHandler eventHandler;
    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor que inicializa el manejador de eventos y las características asociadas al bloque de Seguridad.
     *
     * @param eventHandler Manejador de eventos para procesar solicitudes relacionadas con seguridad.
     */
    public ServerSecurityFunction(ServerSecurityEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        features = new ArrayList<>();
        features.add(new CertificateSignedFeature(null));
        features.add(new InstallCertificateFeature(null));
        features.add(new SecurityEventNotificationFeature(this));
        features.add(new SignCertificateFeature(this));
    }

    /**
     * Devuelve la lista de características soportadas por este bloque funcional.
     *
     * @return Un arreglo de objetos {@link FunctionFeature}.
     */
    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    /**
     * Maneja las solicitudes recibidas para este bloque funcional.
     *
     * @param sessionIndex Identificador de la sesión donde se recibió la solicitud.
     * @param request Solicitud entrante que necesita ser procesada.
     * @return Confirmación como respuesta a la solicitud procesada, o null si no se reconoce la solicitud.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        if (request instanceof SecurityEventNotificationRequest) {
            return eventHandler.handleSecurityEventNotificationRequest(
                    sessionIndex, (SecurityEventNotificationRequest) request);
        } else if (request instanceof SignCertificateRequest) {
            return eventHandler.handleSignCertificateRequest(
                    sessionIndex, (SignCertificateRequest) request);
        }
        return null;
    }

    /**
     * Crea una solicitud de tipo {@link CertificateSignedRequest}.
     *
     * @param certificateChain Cadena de certificados codificada en formato PEM. Puede contener
     *                         subcertificados CA necesarios. El orden debe seguir la cadena de certificados
     *                         comenzando desde el certificado raíz.
     * @return Una instancia de {@link CertificateSignedRequest}.
     */
    public CertificateSignedRequest createCertificateSignedRequest(String certificateChain) {
        return new CertificateSignedRequest(certificateChain);
    }

    /**
     * Crea una solicitud de tipo {@link InstallCertificateRequest}.
     *
     * @param certificateType Tipo de certificado que se enviará (uso específico).
     * @param certificate Certificado X.509 codificado en formato PEM.
     * @return Una instancia de {@link InstallCertificateRequest}.
     */
    public InstallCertificateRequest createInstallCertificateRequest(
            InstallCertificateUseEnum certificateType, String certificate) {
        return new InstallCertificateRequest(certificateType, certificate);
    }
}
