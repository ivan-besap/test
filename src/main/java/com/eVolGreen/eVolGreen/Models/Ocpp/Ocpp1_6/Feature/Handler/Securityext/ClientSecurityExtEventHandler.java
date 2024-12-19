package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Handler.Securityext;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.*;

/**
 * Interfaz para manejar eventos de seguridad extendidos del cliente.
 * Los métodos definidos aquí se utilizan para procesar solicitudes de certificados, logs, actualizaciones de firmware y mensajes extendidos.
 */
public interface ClientSecurityExtEventHandler {

    /**
     * Maneja una solicitud {@link CertificateSignedRequest} y devuelve una confirmación {@link CertificateSignedConfirmation}.
     *
     * Este método es utilizado para procesar la solicitud de firmar un certificado y generar una respuesta que indica si
     * la operación fue exitosa.
     *
     * @param request la solicitud {@link CertificateSignedRequest} entrante para ser procesada.
     * @return una confirmación {@link CertificateSignedConfirmation} como respuesta.
     *
     * <pre>
     *   Ejemplo:
     *   CertificateSignedRequest request = new CertificateSignedRequest("MIICIjANBgkqhki...");
     *   CertificateSignedConfirmation confirmation = handler.handleCertificateSignedRequest(request);
     * </pre>
     */
    CertificateSignedConfirmation handleCertificateSignedRequest(CertificateSignedRequest request);

    /**
     * Maneja una solicitud {@link DeleteCertificateRequest} y devuelve una confirmación {@link DeleteCertificateConfirmation}.
     *
     * Este método procesa la eliminación de un certificado instalado en el sistema, devolviendo una confirmación sobre el éxito o el fallo de la operación.
     *
     * @param request la solicitud {@link DeleteCertificateRequest} entrante para eliminar un certificado.
     * @return una confirmación {@link DeleteCertificateConfirmation} como respuesta.
     *
     * <pre>
     *   Ejemplo:
     *   DeleteCertificateRequest request = new DeleteCertificateRequest(certificateHashData);
     *   DeleteCertificateConfirmation confirmation = handler.handleDeleteCertificateRequest(request);
     * </pre>
     */
    DeleteCertificateConfirmation handleDeleteCertificateRequest(DeleteCertificateRequest request);

    /**
     * Maneja una solicitud {@link ExtendedTriggerMessageRequest} y devuelve una confirmación {@link ExtendedTriggerMessageConfirmation}.
     *
     * Este método se encarga de procesar una solicitud para desencadenar mensajes adicionales en el sistema.
     *
     * @param request la solicitud {@link ExtendedTriggerMessageRequest} entrante para ser procesada.
     * @return una confirmación {@link ExtendedTriggerMessageConfirmation} como respuesta.
     *
     * <pre>
     *   Ejemplo:
     *   ExtendedTriggerMessageRequest request = new ExtendedTriggerMessageRequest(MessageTriggerEnumType.BootNotification);
     *   ExtendedTriggerMessageConfirmation confirmation = handler.handleExtendedTriggerMessageRequest(request);
     * </pre>
     */
    ExtendedTriggerMessageConfirmation handleExtendedTriggerMessageRequest(ExtendedTriggerMessageRequest request);

    /**
     * Maneja una solicitud {@link GetInstalledCertificateIdsRequest} y devuelve una confirmación {@link GetInstalledCertificateIdsConfirmation}.
     *
     * Este método obtiene los identificadores de los certificados instalados en el sistema.
     *
     * @param request la solicitud {@link GetInstalledCertificateIdsRequest} entrante para obtener los IDs de certificados.
     * @return una confirmación {@link GetInstalledCertificateIdsConfirmation} como respuesta.
     *
     * <pre>
     *   Ejemplo:
     *   GetInstalledCertificateIdsRequest request = new GetInstalledCertificateIdsRequest(CertificateUseEnumType.CentralSystemRootCertificate);
     *   GetInstalledCertificateIdsConfirmation confirmation = handler.handleGetInstalledCertificateIdsRequest(request);
     * </pre>
     */
    GetInstalledCertificateIdsConfirmation handleGetInstalledCertificateIdsRequest(GetInstalledCertificateIdsRequest request);

    /**
     * Maneja una solicitud {@link GetLogRequest} y devuelve una confirmación {@link GetLogConfirmation}.
     *
     * Este método recupera los logs solicitados desde el sistema de carga.
     *
     * @param request la solicitud {@link GetLogRequest} entrante para obtener los logs.
     * @return una confirmación {@link GetLogConfirmation} como respuesta.
     *
     * <pre>
     *   Ejemplo:
     *   GetLogRequest request = new GetLogRequest(LogEnumType.DiagnosticsLog, 12345, logParameters);
     *   GetLogConfirmation confirmation = handler.handleGetLogRequest(request);
     * </pre>
     */
    GetLogConfirmation handleGetLogRequest(GetLogRequest request);

    /**
     * Maneja una solicitud {@link InstallCertificateRequest} y devuelve una confirmación {@link InstallCertificateConfirmation}.
     *
     * Este método instala un certificado en el sistema de carga.
     *
     * @param request la solicitud {@link InstallCertificateRequest} entrante para instalar un certificado.
     * @return una confirmación {@link InstallCertificateConfirmation} como respuesta.
     *
     * <pre>
     *   Ejemplo:
     *   InstallCertificateRequest request = new InstallCertificateRequest(CertificateUseEnumType.ManufacturerRootCertificate, "MIICIjANBgkqhki...");
     *   InstallCertificateConfirmation confirmation = handler.handleInstallCertificateRequest(request);
     * </pre>
     */
    InstallCertificateConfirmation handleInstallCertificateRequest(InstallCertificateRequest request);

    /**
     * Maneja una solicitud {@link SignedUpdateFirmwareRequest} y devuelve una confirmación {@link SignedUpdateFirmwareConfirmation}.
     *
     * Este método procesa la solicitud de actualización de firmware firmado, devolviendo una confirmación sobre el estado de la operación.
     *
     * @param request la solicitud {@link SignedUpdateFirmwareRequest} entrante para actualizar el firmware.
     * @return una confirmación {@link SignedUpdateFirmwareConfirmation} como respuesta.
     *
     * <pre>
     *   Ejemplo:
     *   SignedUpdateFirmwareRequest request = new SignedUpdateFirmwareRequest(12345, firmware);
     *   SignedUpdateFirmwareConfirmation confirmation = handler.handleSignedUpdateFirmwareRequest(request);
     * </pre>
     */
    SignedUpdateFirmwareConfirmation handleSignedUpdateFirmwareRequest(SignedUpdateFirmwareRequest request);
}
