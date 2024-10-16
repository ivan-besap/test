package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common;

/**
 * Interfaz que define los métodos para manejar la apertura y cierre de conexiones, así como la gestión de eventos asociados.
 * <p>
 * Un {@code Listener} proporciona la infraestructura básica para abrir una conexión de red en un puerto específico,
 * manejar solicitudes entrantes y responder a eventos que ocurren en esa conexión.
 * </p>
 */
public interface Listener {

    /**
     * Abre una conexión en un puerto especificado y comienza a escuchar eventos entrantes.
     * <p>
     * Este método inicia el proceso de escucha en el puerto y hostname proporcionados, permitiendo que el sistema
     * maneje solicitudes entrantes. Los eventos de la conexión serán manejados por la instancia de {@link ListenerEvents}.
     * </p>
     *
     * @param hostname El nombre del host donde se abrirá la conexión.
     * @param port El número de puerto donde el listener comenzará a escuchar.
     * @param listenerEvents La instancia de {@link ListenerEvents} que manejará los eventos de la conexión.
     */
    void open(String hostname, int port, ListenerEvents listenerEvents);

    /**
     * Cierra la conexión actual de forma segura.
     * <p>
     * Este método cierra la conexión que está siendo escuchada, liberando cualquier recurso asociado a la misma.
     * </p>
     */
    void close();

    /**
     * Verifica si la conexión está cerrada.
     * <p>
     * Este método devuelve {@code true} si la conexión está cerrada, de lo contrario, devuelve {@code false}.
     * </p>
     *
     * @return {@code true} si la conexión está cerrada, {@code false} en caso contrario.
     */
    boolean isClosed();

    /**
     * Configura si las solicitudes entrantes deben ser manejadas de manera asíncrona.
     * <p>
     * Si se establece en {@code true}, las solicitudes serán manejadas de manera asíncrona.
     * </p>
     *
     * @param async {@code true} para manejar las solicitudes de forma asíncrona, {@code false} para manejo síncrono.
     */
    void setAsyncRequestHandler(boolean async);
}
