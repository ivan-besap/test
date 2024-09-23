package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON;

import java.io.IOException;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import org.java_websocket.SSLSocketChannel2;
import org.java_websocket.server.DefaultSSLWebSocketServerFactory;

/**
 * Implementación personalizada de {@link DefaultSSLWebSocketServerFactory} para restringir los cifrados habilitados en la conexión SSL.
 * Esta clase sobrescribe el comportamiento por defecto para permitir la selección de un conjunto específico de suites de cifrado
 * durante la creación de canales SSL seguros.
 */
public final class CustomSSLWebSocketServerFactory extends DefaultSSLWebSocketServerFactory {

    private List<String> ciphers;

    /**
     * Constructor que permite definir un contexto SSL y una lista de cifrados permitidos.
     *
     * @param sslContext contexto SSL que define la configuración de seguridad.
     * @param ciphers lista de cifrados permitidos que serán utilizados durante la conexión.
     */
    public CustomSSLWebSocketServerFactory(SSLContext sslContext, List<String> ciphers) {
        super(sslContext);
        this.ciphers = ciphers;
    }

    /**
     * Sobrescribe el método para envolver un canal de socket con SSL, restringiendo los cifrados habilitados.
     *
     * @param channel el canal de socket que será envuelto con SSL.
     * @param key la clave de selección asociada al canal.
     * @return un canal envuelto con SSL.
     * @throws IOException si ocurre algún error al envolver el canal.
     */
    @Override
    public ByteChannel wrapChannel(SocketChannel channel, SelectionKey key) throws IOException {
        SSLEngine sslEngine = sslcontext.createSSLEngine();

        // Obtener las suites de cifrado habilitadas por el motor SSL y filtrarlas según las permitidas.
        List<String> enabledCiphers = new ArrayList<>(Arrays.asList(sslEngine.getEnabledCipherSuites()));
        enabledCiphers.retainAll(ciphers);

        // Establecer las suites de cifrado permitidas y configurar el motor SSL en modo servidor.
        sslEngine.setEnabledCipherSuites(enabledCiphers.toArray(new String[enabledCiphers.size()]));
        sslEngine.setUseClientMode(false);

        // Retornar el canal SSL envuelto.
        return new SSLSocketChannel2(channel, sslEngine, exec, key);
    }
}
