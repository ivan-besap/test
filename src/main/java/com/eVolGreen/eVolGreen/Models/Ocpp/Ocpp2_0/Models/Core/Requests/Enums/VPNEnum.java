package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums;

/**
 * Enumeración que define los tipos de VPN disponibles.
 * <p>Incluye los protocolos más utilizados para establecer conexiones seguras mediante VPN.</p>
 */
public enum VPNEnum {
    /** Protocolo IKEv2 (Internet Key Exchange version 2). */
    IKEv2,

    /** Protocolo IPSec (Internet Protocol Security). */
    IPSec,

    /** Protocolo L2TP (Layer 2 Tunneling Protocol). */
    L2TP,

    /** Protocolo PPTP (Point-to-Point Tunneling Protocol). */
    PPTP
}
