package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.URI;
import javax.net.ssl.SSLSocketFactory;

/**
 * Implementación base de WssSocketBuilder para conexiones WSS seguras en eVolGreen.
 */
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

    // Constructor privado para usar el patrón Builder
    private BaseWssSocketBuilder() {}

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
