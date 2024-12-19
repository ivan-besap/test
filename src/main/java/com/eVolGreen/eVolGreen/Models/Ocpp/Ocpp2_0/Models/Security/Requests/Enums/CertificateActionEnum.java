package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.Enums;

/**
 * Define las posibles acciones que se pueden realizar sobre un certificado en el contexto de OCPP.
 *
 * <p>Este enumerador es utilizado para especificar si un certificado debe ser instalado o
 * actualizado como parte de una solicitud.</p>
 */
public enum CertificateActionEnum {
    /**
     * Indica que el certificado debe ser instalado.
     * Esta acción se utiliza cuando no existe un certificado previo o cuando se necesita un nuevo certificado.
     */
    Install,

    /**
     * Indica que el certificado existente debe ser actualizado.
     * Se utiliza para reemplazar un certificado existente con uno más reciente o modificado.
     */
    Update
}
