package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.Enums;

/**
 * Enumeration representing the type of certificate to be signed.
 *
 * <p>If omitted, the certificate is to be used for both:
 * <ul>
 *   <li>The Charging Station to CSMS connection.</li>
 *   <li>The 15118 connection (if implemented).</li>
 * </ul>
 */
public enum CertificateSigningUseEnum {
    /** Certificate for Charging Station to CSMS communication. */
    ChargingStationCertificate,

    /** Certificate for Vehicle-to-Grid (V2G) communication. */
    V2GCertificate
}
