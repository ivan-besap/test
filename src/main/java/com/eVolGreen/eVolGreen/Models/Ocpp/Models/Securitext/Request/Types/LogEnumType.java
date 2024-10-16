package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Request.Types;

import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Request.GetLogRequest;

/**
 * Enumeración que define los tipos de registros (logs) que se pueden solicitar o manejar en el sistema OCPP de eVolGreen.
 *
 * <p>Esta enumeración es utilizada por {@link GetLogRequest} para especificar el tipo de registro
 * que debe ser subido por el punto de carga.</p>
 */
public enum LogEnumType {

    /**
     * Define un archivo de registro de diagnóstico.
     */
    DiagnosticsLog,

    /**
     * Solicita al punto de carga que suba el registro de seguridad.
     */
    SecurityLog
}
