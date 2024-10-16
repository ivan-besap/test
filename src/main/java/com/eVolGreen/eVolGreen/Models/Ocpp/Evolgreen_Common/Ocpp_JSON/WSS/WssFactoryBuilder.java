package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.WSS;

import org.java_websocket.WebSocketServerFactory;

/**
 * Interfaz para construir fábricas de servidores WebSocket que soporten el esquema WSS (WebSocket Secure).
 * Implementa métodos clave para la creación y verificación de las configuraciones necesarias para
 * conexiones seguras en el protocolo WebSocket.
 */
public interface WssFactoryBuilder {

    /**
     * Crea una instancia de {@link WebSocketServerFactory} configurada para soportar el esquema WSS.
     * Este método debe implementarse para garantizar que la fábrica devuelta permita el uso de conexiones seguras.
     *
     * @return una instancia de {@link WebSocketServerFactory} que soporte conexiones WSS.
     */
    WebSocketServerFactory build();

    /**
     * Verifica que todos los parámetros necesarios para la creación de la fábrica de servidores estén configurados.
     * Este método se utiliza para asegurar una falla rápida si faltan parámetros requeridos en la configuración,
     * evitando así exponer detalles de implementación al cliente.
     *
     * @throws IllegalStateException si la verificación falla debido a la falta de parámetros necesarios.
     */
    void verify();
}
