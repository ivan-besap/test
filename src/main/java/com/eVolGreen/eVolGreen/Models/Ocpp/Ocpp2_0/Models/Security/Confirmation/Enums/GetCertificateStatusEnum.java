package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation.Enums;

/**
 * Enumera los posibles resultados al intentar recuperar el estado de un certificado OCSP.
 *
 * <p>Indica si la estación de carga pudo obtener el estado del certificado utilizando el protocolo
 * OCSP (Online Certificate Status Protocol).
 */
public enum GetCertificateStatusEnum {

    /**
     * La estación de carga pudo recuperar satisfactoriamente el estado del certificado OCSP.
     */
    Accepted,

    /**
     * La estación de carga no pudo recuperar el estado del certificado OCSP debido a un error.
     */
    Failed
}
