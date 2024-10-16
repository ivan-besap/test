package com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Handler.Securityext;

import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Confirmations.LogStatusNotificationConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Confirmations.SecurityEventNotificationConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Confirmations.SignCertificateConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Confirmations.SignedFirmwareStatusNotificationConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Request.LogStatusNotificationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Request.SecurityEventNotificationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Request.SignCertificateRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Request.SignedFirmwareStatusNotificationRequest;

import java.util.UUID;

/**
 * Interfaz para manejar eventos de seguridad extendidos del servidor.
 * Los métodos definidos aquí procesan las solicitudes de notificaciones de estado de logs,
 * notificaciones de eventos de seguridad, firma de certificados y notificaciones de estado de firmware firmado.
 */
public interface ServerSecurityExtEventHandler {

    /**
     * Maneja una solicitud {@link LogStatusNotificationRequest} y devuelve una confirmación {@link LogStatusNotificationConfirmation}.
     *
     * Este método se encarga de procesar las notificaciones de estado de los logs generados por el sistema de carga.
     *
     * @param sessionIndex El identificador de la sesión de la solicitud.
     * @param request La solicitud {@link LogStatusNotificationRequest} entrante para ser procesada.
     * @return Una confirmación {@link LogStatusNotificationConfirmation} como respuesta.
     *
     * <pre>
     *   Ejemplo:
     *   UUID sessionId = UUID.randomUUID();
     *   LogStatusNotificationRequest request = new LogStatusNotificationRequest(UploadLogStatusEnumType.Uploaded);
     *   LogStatusNotificationConfirmation confirmation = handler.handleLogStatusNotificationRequest(sessionId, request);
     * </pre>
     */
    LogStatusNotificationConfirmation handleLogStatusNotificationRequest(UUID sessionIndex, LogStatusNotificationRequest request);

    /**
     * Maneja una solicitud {@link SecurityEventNotificationRequest} y devuelve una confirmación {@link SecurityEventNotificationConfirmation}.
     *
     * Este método se encarga de procesar las notificaciones de eventos de seguridad que puedan haber ocurrido en el sistema de carga.
     *
     * @param sessionIndex El identificador de la sesión de la solicitud.
     * @param request La solicitud {@link SecurityEventNotificationRequest} entrante para ser procesada.
     * @return Una confirmación {@link SecurityEventNotificationConfirmation} como respuesta.
     *
     * <pre>
     *   Ejemplo:
     *   UUID sessionId = UUID.randomUUID();
     *   ZonedDateTime timestamp = ZonedDateTime.now();
     *   SecurityEventNotificationRequest request = new SecurityEventNotificationRequest("MalwareDetected", timestamp);
     *   SecurityEventNotificationConfirmation confirmation = handler.handleSecurityEventNotificationRequest(sessionId, request);
     * </pre>
     */
    SecurityEventNotificationConfirmation handleSecurityEventNotificationRequest(UUID sessionIndex, SecurityEventNotificationRequest request);

    /**
     * Maneja una solicitud {@link SignCertificateRequest} y devuelve una confirmación {@link SignCertificateConfirmation}.
     *
     * Este método se utiliza para firmar un certificado enviado por el punto de carga y devolver el estado de la operación.
     *
     * @param sessionIndex El identificador de la sesión de la solicitud.
     * @param request La solicitud {@link SignCertificateRequest} entrante para ser procesada.
     * @return Una confirmación {@link SignCertificateConfirmation} como respuesta.
     *
     * <pre>
     *   Ejemplo:
     *   UUID sessionId = UUID.randomUUID();
     *   SignCertificateRequest request = new SignCertificateRequest("MIICIjANBgkqhki...");
     *   SignCertificateConfirmation confirmation = handler.handleSignCertificateRequest(sessionId, request);
     * </pre>
     */
    SignCertificateConfirmation handleSignCertificateRequest(UUID sessionIndex, SignCertificateRequest request);

    /**
     * Maneja una solicitud {@link SignedFirmwareStatusNotificationRequest} y devuelve una confirmación {@link SignedFirmwareStatusNotificationConfirmation}.
     *
     * Este método procesa las notificaciones del estado de la instalación de firmware firmado en el sistema de carga.
     *
     * @param sessionIndex El identificador de la sesión de la solicitud.
     * @param request La solicitud {@link SignedFirmwareStatusNotificationRequest} entrante para ser procesada.
     * @return Una confirmación {@link SignedFirmwareStatusNotificationConfirmation} como respuesta.
     *
     * <pre>
     *   Ejemplo:
     *   UUID sessionId = UUID.randomUUID();
     *   SignedFirmwareStatusNotificationRequest request = new SignedFirmwareStatusNotificationRequest(FirmwareStatusEnumType.Installed);
     *   SignedFirmwareStatusNotificationConfirmation confirmation = handler.handleSignedFirmwareStatusNotificationRequest(sessionId, request);
     * </pre>
     */
    SignedFirmwareStatusNotificationConfirmation handleSignedFirmwareStatusNotificationRequest(UUID sessionIndex, SignedFirmwareStatusNotificationRequest request);
}
