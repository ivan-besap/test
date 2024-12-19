package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.WSS;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.URI;
import java.security.cert.X509Certificate;
import javax.net.ssl.*;

/**
 * Implementación base de WssSocketBuilder para conexiones WSS seguras en eVolGreen.
 */
@Component
public class BaseWssSocketBuilder implements WssSocketBuilder {

    public static final int DEFAULT_WSS_PORT = 443;
    private Proxy proxy = Proxy.NO_PROXY;
    private SocketFactory socketFactory = Socket::new;
    private SSLSocketFactory sslSocketFactory;
    private boolean tcpNoDelay;
    private boolean reuseAddr;
    private boolean autoClose = true;
    private URI uri;
    private InetSocketAddressFactory inetSocketAddressFactory =
            (host, port) -> new InetSocketAddress(host, port);

    // Tiempo de espera de conexión (0 para sin límite)
    private int connectionTimeout = 0;

    // Constructor por defecto
    public BaseWssSocketBuilder() {
        // Configurar un SSLSocketFactory predeterminado que confía en todos los certificados
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                    }
            };
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            this.sslSocketFactory = sslContext.getSocketFactory();
        } catch (Exception e) {
            throw new RuntimeException("Error al configurar SSLContext predeterminado", e);
        }
    }

    // Método estático para obtener una instancia del Builder
    public static BaseWssSocketBuilder builder() {
        return new BaseWssSocketBuilder();
    }

    public BaseWssSocketBuilder proxy(Proxy proxy) {
        this.proxy = proxy;
        return this;
    }

    public BaseWssSocketBuilder sslSocketFactory(SSLSocketFactory sslSocketFactory) {
        this.sslSocketFactory = sslSocketFactory;
        return this;
    }

    public BaseWssSocketBuilder socketFactory(SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
        return this;
    }

    public BaseWssSocketBuilder inetSocketAddressFactory(InetSocketAddressFactory inetSocketAddressFactory) {
        this.inetSocketAddressFactory = inetSocketAddressFactory;
        return this;
    }

    public BaseWssSocketBuilder tcpNoDelay(boolean tcpNoDelay) {
        this.tcpNoDelay = tcpNoDelay;
        return this;
    }

    public BaseWssSocketBuilder reuseAddr(boolean reuseAddr) {
        this.reuseAddr = reuseAddr;
        return this;
    }

    public BaseWssSocketBuilder autoClose(boolean autoClose) {
        this.autoClose = autoClose;
        return this;
    }

    @Override
    public BaseWssSocketBuilder uri(URI uri) {
        this.uri = uri;
        return this;
    }

    public BaseWssSocketBuilder connectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
        return this;
    }

    @Override
    public Socket build() throws IOException {
        verify(true);

        Socket socket = socketFactory.getSocket(proxy);
        socket.setTcpNoDelay(tcpNoDelay);
        socket.setReuseAddress(reuseAddr);

        if (!socket.isBound()) {
            socket.connect(
                    inetSocketAddressFactory.getInetSocketAddress(uri.getHost(), getPort(uri)),
                    connectionTimeout
            );
        }

        return sslSocketFactory.createSocket(socket, uri.getHost(), getPort(uri), autoClose);
    }

    @Override
    public void verify() {
        verify(false);
    }

    public interface SocketFactory {
        Socket getSocket(Proxy proxy);
    }

    public interface InetSocketAddressFactory {
        InetSocketAddress getInetSocketAddress(String host, int port);
    }

    private void verify(boolean complete) {
        if (sslSocketFactory == null) {
            throw new IllegalStateException("sslSocketFactory debe estar configurado");
        }

        if (complete && uri == null) {
            throw new IllegalStateException("URI debe estar configurada");
        }
    }

    private int getPort(URI uri) {
        int port = uri.getPort();
        if (port == -1) {
            String scheme = uri.getScheme();
            if ("wss".equals(scheme)) {
                return DEFAULT_WSS_PORT;
            } else if ("ws".equals(scheme)) {
                throw new IllegalArgumentException("Esquema no soportado: " + scheme);
            } else {
                throw new IllegalArgumentException("Esquema desconocido: " + scheme);
            }
        }
        return port;
    }
}
