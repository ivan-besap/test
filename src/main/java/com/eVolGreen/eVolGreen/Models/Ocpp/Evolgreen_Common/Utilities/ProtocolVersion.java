package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum que representa las versiones del protocolo OCPP junto con sus subprotocolos registrados para WebSocket.
 *
 * <p>Este enum asocia versiones de OCPP con sus respectivos nombres de subprotocolo WebSocket, permitiendo
 * realizar un mapeo eficiente de estos valores cuando se maneja la comunicación OCPP.</p>
 *
 * <h3>Uso de Protocolo:</h3>
 * <ul>
 *   <li><b>OCPP 1.6:</b> Representado por "ocpp1.6".</li>
 *   <li><b>OCPP 2.0.1:</b> Representado por "ocpp2.0.1".</li>
 * </ul>
 */
public enum ProtocolVersion {

    OCPP1_6("ocpp1.6"),  // Protocolo OCPP versión 1.6
    OCPP2_0_1("ocpp2.0.1");  // Protocolo OCPP versión 2.0.1

    // Mapa para almacenar la relación entre el nombre del subprotocolo y la versión del protocolo
    private static final Map<String, ProtocolVersion> MAP = new HashMap<>();

    // Inicialización estática del mapa con los valores del enum
    static {
        for (ProtocolVersion protocolVersion : ProtocolVersion.values()) {
            MAP.put(protocolVersion.subProtocolName, protocolVersion);
        }
    }

    // Nombre del subprotocolo asociado a cada versión de OCPP
    private final String subProtocolName;

    /**
     * Constructor que asocia el nombre del subprotocolo a la versión del protocolo.
     *
     * @param subProtocolName Nombre del subprotocolo WebSocket.
     */
    ProtocolVersion(String subProtocolName) {
        this.subProtocolName = subProtocolName;
    }

    /**
     * Obtiene la versión del protocolo basada en el nombre del subprotocolo.
     *
     * @param subProtocolName Nombre del subprotocolo WebSocket.
     * @return La versión del protocolo asociada, o {@code null} si no se encuentra.
     */
    public static ProtocolVersion fromSubProtocolName(String subProtocolName) {
        return MAP.get(subProtocolName);
    }

    /**
     * Obtiene el nombre del subprotocolo asociado a la versión del protocolo.
     *
     * @return El nombre del subprotocolo como una cadena.
     */
    public String getSubProtocolName() {
        return subProtocolName;
    }
}
