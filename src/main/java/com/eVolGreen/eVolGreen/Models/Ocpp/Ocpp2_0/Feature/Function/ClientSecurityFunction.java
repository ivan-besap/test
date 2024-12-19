package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.CertificateSignedRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.InstallCertificateRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.SecurityEventNotificationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.SignCertificateRequest;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que gestiona solicitudes y crea peticiones relacionadas con el bloque funcional de Seguridad.
 * <p>
 * Proporciona métodos para manejar solicitudes de eventos de seguridad, instalación de certificados
 * y otros relacionados con la gestión de seguridad.
 */
public class ClientSecurityFunction implements Function {

    private final ClientSecurityEventHandler eventHandler;
    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor para inicializar la función de seguridad del cliente.
     *
     * @param eventHandler Manejador de eventos para solicitudes de seguridad.
     */
    public ClientSecurityFunction(ClientSecurityEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        features = new ArrayList<>();
        features.add(new CertificateSignedFeature(this));
        features.add(new InstallCertificateFeature(this));
        features.add(new SecurityEventNotificationFeature(null));
        features.add(new SignCertificateFeature(null));
    }

    /**
     * Devuelve una lista de las características de la función relacionadas con la seguridad.
     *
     * @return Un arreglo de las características de la función.
     */
    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    /**
     * Maneja solicitudes entrantes relacionadas con la seguridad.
     *
     * @param sessionIndex Índice de la sesión actual.
     * @param request      Solicitud entrante.
     * @return Una confirmación correspondiente a la solicitud, o null si la solicitud no es manejada.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        if (request instanceof CertificateSignedRequest) {
            return eventHandler.handleCertificateSignedRequest((CertificateSignedRequest) request);
        } else if (request instanceof InstallCertificateRequest) {
            return eventHandler.handleInstallCertificateRequest((InstallCertificateRequest) request);
        }
        return null;
    }

    /**
     * Crea una solicitud {@link SecurityEventNotificationRequest} con todos los campos obligatorios.
     *
     * @param type      Tipo del evento de seguridad. Debe provenir de la lista de eventos de seguridad definidos.
     * @param timestamp Fecha y hora en que ocurrió el evento.
     * @return Una instancia de {@link SecurityEventNotificationRequest}.
     */
    public SecurityEventNotificationRequest createSecurityEventNotificationRequest(
            String type, ZonedDateTime timestamp) {
        return new SecurityEventNotificationRequest(type, timestamp);
    }

    /**
     * Crea una solicitud {@link SignCertificateRequest} con todos los campos obligatorios.
     *
     * @param csr El cliente debe enviar la clave pública en forma de una Solicitud de Firma de Certificado
     *            (CSR, por sus siglas en inglés) según lo descrito en RFC 2986 y codificada en formato PEM.
     * @return Una instancia de {@link SignCertificateRequest}.
     */
    public SignCertificateRequest createSignCertificateRequest(String csr) {
        return new SignCertificateRequest(csr);
    }
}
