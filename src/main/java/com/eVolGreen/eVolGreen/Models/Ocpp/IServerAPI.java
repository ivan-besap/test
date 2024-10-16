package com.eVolGreen.eVolGreen.Models.Ocpp;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.NotConnectedException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.OccurenceConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.UnsupportedFeatureException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.ServerEvents;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.Profile;

import java.util.UUID;
import java.util.concurrent.CompletionStage;

/**
 * La interfaz {@code IServerAPI} define los métodos esenciales para gestionar la comunicación entre
 * el servidor y los clientes conectados, permitiendo la gestión de sesiones, el envío de
 * mensajes y la manipulación de perfiles.
 * <p>
 * Esta interfaz ofrece métodos para abrir y cerrar sesiones, verificar el estado de las conexiones
 * y enviar solicitudes asincrónicas entre el servidor y los clientes.
 * <p>
 * Ejemplo de implementación:
 * <pre>
 *     IServerAPI serverAPI = new ServerImplementation();
 *     serverAPI.open("localhost", 8887, serverEvents);
 * </pre>
 */
public interface IServerAPI {

    /**
     * Añade un perfil de características al servidor, permitiendo manejar conjuntos específicos
     * de funcionalidades.
     *
     * @param profile el {@link Profile} que se va a añadir.
     */
    void addFeatureProfile(Profile profile);

    /**
     * Verifica si la sesión con un cliente específico está activa.
     *
     * @param session el {@link UUID} de la sesión a verificar.
     * @return {@code true} si la sesión está abierta, de lo contrario {@code false}.
     */
    boolean isSessionOpen(UUID session);

    /**
     * Cierra una sesión activa con un cliente específico.
     *
     * @param session el {@link UUID} de la sesión a cerrar.
     */
    void closeSession(UUID session);

    /**
     * Abre una conexión de servidor en el host y puerto especificados y comienza a escuchar eventos
     * de los clientes.
     *
     * @param host el nombre del host o dirección IP donde el servidor se abrirá.
     * @param port el puerto en el que el servidor escuchará.
     * @param serverEvents los eventos del servidor implementados a través de {@link ServerEvents}.
     */
    void open(String host, int port, ServerEvents serverEvents);

    /**
     * Cierra la conexión del servidor, cerrando todas las sesiones activas.
     */
    void close();

    /**
     * Verifica si el servidor está cerrado.
     *
     * @return {@code true} si el servidor está cerrado, {@code false} de lo contrario.
     */
    boolean isClosed();

    /**
     * Envía una solicitud a un cliente específico de forma asíncrona.
     *
     * @param sessionIndex el índice de la sesión del cliente.
     * @param request la solicitud {@link Request} que se enviará.
     * @return una instancia de {@link CompletionStage} que contiene la confirmación.
     * @throws OccurenceConstraintException si ocurre una excepción relacionada con las restricciones de la solicitud.
     * @throws UnsupportedFeatureException si la solicitud incluye una característica no soportada.
     * @throws NotConnectedException si no hay una conexión activa con el cliente.
     */
    CompletionStage<Confirmation> send(UUID sessionIndex, Request request)
            throws OccurenceConstraintException, UnsupportedFeatureException, NotConnectedException;

    /**
     * Completa una solicitud de forma asíncrona.
     *
     * @param sessionIndex el índice de la sesión.
     * @param uniqueId el identificador único de la solicitud.
     * @param confirmation la confirmación {@link Confirmation} que se completará.
     * @return {@code true} si la solicitud se completó con éxito, {@code false} de lo contrario.
     * @throws NotConnectedException si no hay una conexión activa con el cliente.
     * @throws UnsupportedFeatureException si la solicitud incluye una característica no soportada.
     * @throws OccurenceConstraintException si ocurre una excepción relacionada con las restricciones de la solicitud.
     */
    boolean asyncCompleteRequest(UUID sessionIndex, String uniqueId, Confirmation confirmation)
            throws NotConnectedException, UnsupportedFeatureException, OccurenceConstraintException;
}
