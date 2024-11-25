package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Securityext;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Feature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Handler.Securityext.ServerSecurityExtEventHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.ProfileFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Securityext.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations.Types.CertificateHashDataType;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.Types.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Perfil de seguridad extendido para el servidor.
 * Este perfil maneja eventos y solicitudes relacionados con la gestión de certificados,
 * actualizaciones de firmware, logs y mensajes extendidos en un entorno OCPP.
 */
public class ServerSecurityExtProfile implements Profile {

    private final Set<Feature> features;
    private final ServerSecurityExtEventHandler handler;

    /**
     * Constructor para el perfil de seguridad extendido del servidor.
     *
     * @param handler Manejador de eventos {@link ServerSecurityExtEventHandler} para procesar las solicitudes.
     */
    public ServerSecurityExtProfile(ServerSecurityExtEventHandler handler) {
        this.features = new HashSet<>();
        this.handler = handler;

        features.add(new CertificateSignedFeature(null));
        features.add(new DeleteCertificateFeature(null));
        features.add(new ExtendedTriggerMessageFeature(null));
        features.add(new GetInstalledCertificateIdsFeature(null));
        features.add(new GetLogFeature(null));
        features.add(new InstallCertificateFeature(null));
        features.add(new LogStatusNotificationFeature(this));
        features.add(new SecurityEventNotificationFeature(this));
        features.add(new SignCertificateFeature(this));
        features.add(new SignedFirmwareStatusNotificationFeature(this));
        features.add(new SignedUpdateFirmwareFeature(null));
    }

    @Override
    public ProfileFeature[] getFeatureList() {
        return features.toArray(new ProfileFeature[0]);
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        Confirmation result = null;

        if (request instanceof LogStatusNotificationRequest) {
            result = handler.handleLogStatusNotificationRequest(sessionIndex, (LogStatusNotificationRequest) request);
        } else if (request instanceof SecurityEventNotificationRequest) {
            result = handler.handleSecurityEventNotificationRequest(sessionIndex, (SecurityEventNotificationRequest) request);
        } else if (request instanceof SignCertificateRequest) {
            result = handler.handleSignCertificateRequest(sessionIndex, (SignCertificateRequest) request);
        } else if (request instanceof SignedFirmwareStatusNotificationRequest) {
            result = handler.handleSignedFirmwareStatusNotificationRequest(sessionIndex, (SignedFirmwareStatusNotificationRequest) request);
        }

        return result;
    }

    /**
     * Crea una solicitud {@link CertificateSignedRequest} con los valores requeridos.
     *
     * @param certificateChain Cadena del certificado que debe ser firmado.
     * @return Instancia de {@link CertificateSignedRequest}.
     * @see CertificateSignedRequest
     * @see CertificateSignedFeature
     *
     * <pre>
     *   Ejemplo:
     *   String certificateChain = "MIICIjANBgkqhkiG...";
     *   CertificateSignedRequest request = profile.createCertificateSignedRequest(certificateChain);
     * </pre>
     */
    public CertificateSignedRequest createCertificateSignedRequest(String certificateChain) {
        return new CertificateSignedRequest(certificateChain);
    }

    /**
     * Crea una solicitud {@link DeleteCertificateRequest} con los valores requeridos.
     *
     * @param certificateHashData Datos hash del certificado a eliminar.
     * @return Instancia de {@link DeleteCertificateRequest}.
     * @see DeleteCertificateRequest
     * @see DeleteCertificateFeature
     *
     * <pre>
     *   Ejemplo:
     *   CertificateHashDataType hashData = new CertificateHashDataType();
     *   DeleteCertificateRequest request = profile.createDeleteCertificateRequest(hashData);
     * </pre>
     */
    public DeleteCertificateRequest createDeleteCertificateRequest(CertificateHashDataType certificateHashData) {
        return new DeleteCertificateRequest(certificateHashData);
    }

    /**
     * Crea una solicitud {@link ExtendedTriggerMessageRequest} con los valores requeridos.
     *
     * @param requestedMessage Mensaje que debe ser desencadenado.
     * @return Instancia de {@link ExtendedTriggerMessageRequest}.
     * @see ExtendedTriggerMessageRequest
     * @see ExtendedTriggerMessageFeature
     *
     * <pre>
     *   Ejemplo:
     *   MessageTriggerEnumType triggerMessage = MessageTriggerEnumType.BootNotification;
     *   ExtendedTriggerMessageRequest request = profile.createExtendedTriggerMessageRequest(triggerMessage);
     * </pre>
     */
    public ExtendedTriggerMessageRequest createExtendedTriggerMessageRequest(MessageTriggerEnumType requestedMessage) {
        return new ExtendedTriggerMessageRequest(requestedMessage);
    }

    /**
     * Crea una solicitud {@link GetInstalledCertificateIdsRequest} con los valores requeridos.
     *
     * @param certificateType Tipo de certificado a consultar.
     * @return Instancia de {@link GetInstalledCertificateIdsRequest}.
     * @see GetInstalledCertificateIdsRequest
     * @see GetInstalledCertificateIdsFeature
     *
     * <pre>
     *   Ejemplo:
     *   CertificateUseEnumType certificateType = CertificateUseEnumType.CentralSystemRootCertificate;
     *   GetInstalledCertificateIdsRequest request = profile.createGetInstalledCertificateIdsRequest(certificateType);
     * </pre>
     */
    public GetInstalledCertificateIdsRequest createGetInstalledCertificateIdsRequest(CertificateUseEnumType certificateType) {
        return new GetInstalledCertificateIdsRequest(certificateType);
    }

    /**
     * Crea una solicitud {@link GetLogRequest} con los valores requeridos.
     *
     * @param logType Tipo de log a obtener.
     * @param requestId ID de la solicitud de log.
     * @param log Parámetros del log a obtener.
     * @return Instancia de {@link GetLogRequest}.
     * @see GetLogRequest
     * @see GetLogFeature
     *
     * <pre>
     *   Ejemplo:
     *   LogEnumType logType = LogEnumType.DiagnosticsLog;
     *   Integer requestId = 12345;
     *   LogParametersType logParameters = new LogParametersType();
     *   GetLogRequest request = profile.createGetLogRequest(logType, requestId, logParameters);
     * </pre>
     */
    public GetLogRequest createGetLogRequest(LogEnumType logType, Integer requestId, LogParametersType log) {
        return new GetLogRequest(logType, requestId, log);
    }

    /**
     * Crea una solicitud {@link InstallCertificateRequest} con los valores requeridos.
     *
     * @param certificateType Tipo de certificado a instalar.
     * @param certificate Certificado en formato PEM.
     * @return Instancia de {@link InstallCertificateRequest}.
     * @see InstallCertificateRequest
     * @see InstallCertificateFeature
     *
     * <pre>
     *   Ejemplo:
     *   CertificateUseEnumType certType = CertificateUseEnumType.CentralSystemRootCertificate;
     *   String cert = "MIICIjANBgkqhkiG...";
     *   InstallCertificateRequest request = profile.createInstallCertificateRequest(certType, cert);
     * </pre>
     */
    public InstallCertificateRequest createInstallCertificateRequest(CertificateUseEnumType certificateType, String certificate) {
        return new InstallCertificateRequest(certificateType, certificate);
    }

    /**
     * Crea una solicitud {@link SignedUpdateFirmwareRequest} con los valores requeridos.
     *
     * @param requestId ID de la solicitud de actualización.
     * @param firmware Tipo de firmware a instalar.
     * @return Instancia de {@link SignedUpdateFirmwareRequest}.
     * @see SignedUpdateFirmwareRequest
     * @see SignedUpdateFirmwareFeature
     *
     * <pre>
     *   Ejemplo:
     *   Integer requestId = 98765;
     *   FirmwareType firmware = new FirmwareType();
     *   SignedUpdateFirmwareRequest request = profile.createSignedUpdateFirmwareRequest(requestId, firmware);
     * </pre>
     */
    public SignedUpdateFirmwareRequest createSignedUpdateFirmwareRequest(Integer requestId, FirmwareType firmware) {
        return new SignedUpdateFirmwareRequest(requestId, firmware);
    }
}
