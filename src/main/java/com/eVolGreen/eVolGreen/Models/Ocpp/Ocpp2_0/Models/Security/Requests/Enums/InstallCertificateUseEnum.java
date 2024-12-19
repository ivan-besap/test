package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.Enums;

/**
 * Enumeración que representa los diferentes tipos de certificados que pueden enviarse para ser instalados.
 */
public enum InstallCertificateUseEnum {
    /** Certificado raíz para V2G (Vehicle-to-Grid). */
    V2GRootCertificate,

    /** Certificado raíz para MO (Mobility Operator). */
    MORootCertificate,

    /** Certificado raíz para CSMS (Charging Station Management System). */
    CSMSRootCertificate,

    /** Certificado raíz del fabricante. */
    ManufacturerRootCertificate
}
