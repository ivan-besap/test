package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Securityext;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Feature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Handler.Securityext.ClientSecurityExtEventHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.ProfileFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Securityext.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.Types.FirmwareStatusEnumType;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.Types.UploadLogStatusEnumType;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Implementación del perfil de seguridad extendido del cliente.
 * Maneja las solicitudes relacionadas con la seguridad y notificaciones para el cliente.
 * Proporciona métodos para crear diferentes tipos de solicitudes de seguridad como certificados, eventos de seguridad y actualizaciones de firmware.
 */
public class ClientSecurityExtProfile implements Profile {

    private final Set<Feature> features;
    private final ClientSecurityExtEventHandler eventHandler;

    /**
     * Constructor para inicializar el perfil de seguridad extendido del cliente con el manejador de eventos proporcionado.
     *
     * @param handler El manejador de eventos de seguridad extendidos.
     */
    public ClientSecurityExtProfile(ClientSecurityExtEventHandler handler) {
        this.features = new HashSet<>();
        this.eventHandler = handler;

        // Agregar las características de seguridad extendidas al conjunto de funciones
        features.add(new CertificateSignedFeature(this));
        features.add(new DeleteCertificateFeature(this));
        features.add(new ExtendedTriggerMessageFeature(this));
        features.add(new GetInstalledCertificateIdsFeature(this));
        features.add(new GetLogFeature(this));
        features.add(new InstallCertificateFeature(this));
        features.add(new LogStatusNotificationFeature(null));
        features.add(new SecurityEventNotificationFeature(null));
        features.add(new SignCertificateFeature(null));
        features.add(new SignedFirmwareStatusNotificationFeature(null));
        features.add(new SignedUpdateFirmwareFeature(this));
    }

    /**
     * Devuelve la lista de características habilitadas para este perfil de seguridad extendido.
     *
     * @return Un array de objetos {@link ProfileFeature} que contiene todas las características habilitadas.
     */
    @Override
    public ProfileFeature[] getFeatureList() {
        return features.toArray(new ProfileFeature[0]);
    }

    /**
     * Maneja las solicitudes entrantes para este perfil de seguridad extendido.
     * Se encarga de determinar el tipo de solicitud y delega el manejo al evento correspondiente.
     *
     * @param sessionIndex El índice de la sesión actual.
     * @param request      La solicitud entrante que debe ser procesada.
     * @return Una confirmación que indica el resultado del manejo de la solicitud.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        Confirmation result = null;

        if (request instanceof CertificateSignedRequest) {
            result = eventHandler.handleCertificateSignedRequest((CertificateSignedRequest) request);
        } else if (request instanceof DeleteCertificateRequest) {
            result = eventHandler.handleDeleteCertificateRequest((DeleteCertificateRequest) request);
        } else if (request instanceof ExtendedTriggerMessageRequest) {
            result = eventHandler.handleExtendedTriggerMessageRequest((ExtendedTriggerMessageRequest) request);
        } else if (request instanceof GetInstalledCertificateIdsRequest) {
            result = eventHandler.handleGetInstalledCertificateIdsRequest((GetInstalledCertificateIdsRequest) request);
        } else if (request instanceof GetLogRequest) {
            result = eventHandler.handleGetLogRequest((GetLogRequest) request);
        } else if (request instanceof InstallCertificateRequest) {
            result = eventHandler.handleInstallCertificateRequest((InstallCertificateRequest) request);
        } else if (request instanceof SignedUpdateFirmwareRequest) {
            result = eventHandler.handleSignedUpdateFirmwareRequest((SignedUpdateFirmwareRequest) request);
        }

        return result;
    }

    /**
     * Crea una solicitud de notificación de estado de registro de cliente {@link LogStatusNotificationRequest}.
     *
     * @param status El estado del proceso de subida del log, representado por {@link UploadLogStatusEnumType}.
     * @return Una instancia de {@link LogStatusNotificationRequest}.
     *
     * <pre>
     *   Ejemplo:
     *   LogStatusNotificationRequest request = profile.createLogStatusNotificationRequest(UploadLogStatusEnumType.Uploading);
     * </pre>
     */
    public LogStatusNotificationRequest createLogStatusNotificationRequest(UploadLogStatusEnumType status) {
        return new LogStatusNotificationRequest(status);
    }

    /**
     * Crea una solicitud de notificación de evento de seguridad de cliente {@link SecurityEventNotificationRequest}.
     *
     * @param type      El tipo de evento de seguridad, representado como un {@link String}.
     * @param timestamp La fecha y hora en que ocurrió el evento, representada por {@link ZonedDateTime}.
     * @return Una instancia de {@link SecurityEventNotificationRequest}.
     *
     * <pre>
     *   Ejemplo:
     *   SecurityEventNotificationRequest request = profile.createSecurityEventNotificationRequest("MalwareDetected", ZonedDateTime.now());
     * </pre>
     */
    public SecurityEventNotificationRequest createSecurityEventNotificationRequest(String type, ZonedDateTime timestamp) {
        return new SecurityEventNotificationRequest(type, timestamp);
    }

    /**
     * Crea una solicitud para firmar el certificado de cliente {@link SignCertificateRequest}.
     *
     * @param csr El CSR (Certificate Signing Request) en formato PEM.
     * @return Una instancia de {@link SignCertificateRequest}.
     *
     * <pre>
     *   Ejemplo:
     *   SignCertificateRequest request = profile.createSignCertificateRequest("MIICIjANBgkqhki...");
     * </pre>
     */
    public SignCertificateRequest createSignCertificateRequest(String csr) {
        return new SignCertificateRequest(csr);
    }

    /**
     * Crea una solicitud de notificación de estado de firmware firmado del cliente {@link SignedFirmwareStatusNotificationRequest}.
     *
     * @param status El estado de la actualización del firmware, representado por {@link FirmwareStatusEnumType}.
     * @return Una instancia de {@link SignedFirmwareStatusNotificationRequest}.
     *
     * <pre>
     *   Ejemplo:
     *   SignedFirmwareStatusNotificationRequest request = profile.createSignedFirmwareStatusNotificationRequest(FirmwareStatusEnumType.Downloaded);
     * </pre>
     */
    public SignedFirmwareStatusNotificationRequest createSignedFirmwareStatusNotificationRequest(FirmwareStatusEnumType status) {
        return new SignedFirmwareStatusNotificationRequest(status);
    }
}
