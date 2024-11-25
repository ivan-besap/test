package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.Enums;

/**
 * Enum para especificar el tipo de certificado solicitado.
 *
 * <p>Representa los posibles tipos de certificados que pueden ser solicitados al sistema de
 * gestión de carga.
 */
public enum GetCertificateIdUseEnum {
    /** Certificado raíz para V2G (Vehicle-to-Grid). */
    V2GRootCertificate,
    /** Certificado raíz para MO (Mobility Operator). */
    MORootCertificate,
    /** Certificado raíz para CSMS (Central System Management Station). */
    CSMSRootCertificate,
    /** Cadena de certificados para V2G (Vehicle-to-Grid). */
    V2GCertificateChain,
    /** Certificado raíz del fabricante del equipo. */
    ManufacturerRootCertificate
}
