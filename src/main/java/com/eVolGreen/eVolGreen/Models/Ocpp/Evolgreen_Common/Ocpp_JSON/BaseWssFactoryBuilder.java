package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON;

import java.util.List;
import javax.net.ssl.SSLContext;
import org.java_websocket.WebSocketServerFactory;
import org.java_websocket.server.DefaultSSLWebSocketServerFactory;

/**
 * Implementación base de {@link WssFactoryBuilder} para crear fábricas de servidores WebSocket SSL.
 * Proporciona la capacidad de configurar el contexto SSL y los cifrados personalizados que pueden
 * utilizarse en la comunicación WebSocket segura.
 */
public class BaseWssFactoryBuilder implements WssFactoryBuilder {

    private SSLContext sslContext;
    private List<String> ciphers;

    /**
     * Constructor privado para impedir la creación directa.
     * Utiliza {@link #builder()} para obtener una instancia de la clase.
     */
    private BaseWssFactoryBuilder() {}

    /**
     * Método estático para obtener una instancia de {@link BaseWssFactoryBuilder}.
     * @return Nueva instancia de {@link BaseWssFactoryBuilder}.
     */
    public static BaseWssFactoryBuilder builder() {
        return new BaseWssFactoryBuilder();
    }

    /**
     * Configura la lista de cifrados a utilizar para la conexión SSL.
     * @param ciphers Lista de cifrados permitidos.
     * @return Esta instancia del constructor con los cifrados configurados.
     */
    public BaseWssFactoryBuilder ciphers(List<String> ciphers) {
        this.ciphers = ciphers;
        return this;
    }

    /**
     * Configura el contexto SSL para la conexión segura.
     * @param sslContext Contexto SSL que define las configuraciones de seguridad.
     * @return Esta instancia del constructor con el contexto SSL configurado.
     */
    public BaseWssFactoryBuilder sslContext(SSLContext sslContext) {
        this.sslContext = sslContext;
        return this;
    }

    /**
     * Construye la fábrica de servidores WebSocket basada en las configuraciones proporcionadas.
     * @return Instancia de {@link WebSocketServerFactory} configurada para SSL.
     */
    @Override
    public WebSocketServerFactory build() {
        verify();

        return ciphers == null
                ? new DefaultSSLWebSocketServerFactory(sslContext)
                : new CustomSSLWebSocketServerFactory(sslContext, ciphers);
    }

    /**
     * Verifica que las configuraciones necesarias estén completas antes de construir la fábrica.
     * Lanza una excepción si el contexto SSL no está configurado.
     * @throws IllegalStateException si el contexto SSL no está configurado.
     */
    @Override
    public void verify() {
        if (sslContext == null) {
            throw new IllegalStateException("El contexto SSL debe estar configurado");
        }
    }
}
