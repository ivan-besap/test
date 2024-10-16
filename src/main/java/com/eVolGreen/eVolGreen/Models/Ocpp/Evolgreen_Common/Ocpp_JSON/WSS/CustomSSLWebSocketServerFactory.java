package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.WSS;

import java.io.IOException;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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

    private final List<String> ciphers;

    /**
     * Constructor que permite definir un contexto SSL y una lista de cifrados permitidos.
     *
     * @param sslContext contexto SSL que define la configuración de seguridad.
     * @param ciphers lista de cifrados permitidos que serán utilizados durante la conexión.
     *                No debe ser nulo ni vacío.
     */
    public CustomSSLWebSocketServerFactory(SSLContext sslContext, List<String> ciphers) {
        super(sslContext);
        this.ciphers = Objects.requireNonNull(ciphers, "La lista de cifrados no debe ser nula");
        if (ciphers.isEmpty()) {
            throw new IllegalArgumentException("La lista de cifrados no debe estar vacía");
        }
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
        SSLEngine sslEngine = createAndConfigureSSLEngine();
        return new SSLSocketChannel2(channel, sslEngine, exec, key);
    }

    /**
     * Crea y configura un {@link SSLEngine} con las suites de cifrado permitidas.
     *
     * @return un {@link SSLEngine} configurado.
     */
    private SSLEngine createAndConfigureSSLEngine() {
        SSLEngine sslEngine = sslcontext.createSSLEngine();

        // Obtener y filtrar las suites de cifrado habilitadas según las permitidas.
        List<String> enabledCiphers = filterEnabledCiphers(sslEngine.getEnabledCipherSuites());

        // Configurar el motor SSL con las suites de cifrado filtradas.
        sslEngine.setEnabledCipherSuites(enabledCiphers.toArray(new String[0]));
        sslEngine.setUseClientMode(false);  // Configurar en modo servidor.

        return sslEngine;
    }

    /**
     * Filtra las suites de cifrado habilitadas para que solo incluyan las permitidas.
     *
     * @param availableCiphers Las suites de cifrado disponibles.
     * @return Lista filtrada de suites de cifrado permitidas.
     */
    private List<String> filterEnabledCiphers(String[] availableCiphers) {
        List<String> enabledCiphers = new ArrayList<>(Arrays.asList(availableCiphers));
        enabledCiphers.retainAll(ciphers);  // Filtrar solo las permitidas.
        return enabledCiphers;
    }
}
