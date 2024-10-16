package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Request.Types;

import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.BootNotificationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.HeartbeatRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.MeterValuesRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.StatusNotificationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Request.ExtendedTriggerMessageRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Request.LogStatusNotificationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Request.SignCertificateRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Request.SignedFirmwareStatusNotificationRequest;


/**
 * Enumeración que define los diferentes tipos de mensajes que pueden ser desencadenados
 * en un contexto OCPP dentro de eVolGreen.
 *
 * <p>Esta enumeración se utiliza principalmente en {@link ExtendedTriggerMessageRequest} para
 * especificar qué tipo de mensaje debe ser activado por la estación de carga.</p>
 */
public enum MessageTriggerEnumType {

    /**
     * Para desencadenar un {@link BootNotificationRequest}.
     */
    BootNotification,

    /**
     * Para desencadenar un {@link LogStatusNotificationRequest}.
     */
    LogStatusNotification,

    /**
     * Para desencadenar un {@link SignedFirmwareStatusNotificationRequest}.
     */
    FirmwareStatusNotification,

    /**
     * Para desencadenar un {@link HeartbeatRequest}.
     */
    Heartbeat,

    /**
     * Para desencadenar un {@link MeterValuesRequest}.
     */
    MeterValues,

    /**
     * Para desencadenar un {@link SignCertificateRequest} con certificateType: ChargePointCertificate.
     */
    SignChargePointCertificate,

    /**
     * Para desencadenar un {@link StatusNotificationRequest}.
     */
    StatusNotification
}
