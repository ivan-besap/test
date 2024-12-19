package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6;

//import com.evolgreen.ocpp.feature.profile.Profile;
//import com.evolgreen.ocpp.model.Confirmation;
//import com.evolgreen.ocpp.model.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.OccurenceConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.UnsupportedFeatureException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.ClientEvents;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;

import java.util.UUID;
import java.util.concurrent.CompletionStage;

/**
 * Interfaz para definir la API del cliente en eVolGreen, permitiendo manejar perfiles,
 * establecer conexiones, enviar solicitudes y gestionar sesiones.
 */
public interface IClientAPI {

    /**
     * Agrega un perfil de características al cliente.
     *
     * @param profile el perfil a agregar.
     */
    void addFeatureProfile(Profile profile);

    /**
     * Conecta el cliente a un servidor usando la URL proporcionada.
     *
     * @param url la URL del servidor.
     * @param clientEvents eventos asociados a la conexión del cliente.
     */
    void connect(String url, ClientEvents clientEvents) throws InterruptedException;

    /**
     * Envía una solicitud al servidor.
     *
     * @param request la solicitud a enviar.
     * @return una instancia de {@link CompletionStage} con la confirmación del envío.
     * @throws OccurenceConstraintException si hay un error con las restricciones de ocurrencia.
     * @throws UnsupportedFeatureException si la función no es compatible.
     */
    CompletionStage<Confirmation> send(Request request)
            throws OccurenceConstraintException, UnsupportedFeatureException;

    /**
     * Completa una solicitud de forma asíncrona.
     *
     * @param uniqueId el ID único de la solicitud.
     * @param confirmation la confirmación asociada.
     * @return un booleano indicando si la solicitud fue completada.
     * @throws UnsupportedFeatureException si la función no es compatible.
     * @throws OccurenceConstraintException si hay un error con las restricciones de ocurrencia.
     */
    boolean asyncCompleteRequest(String uniqueId, Confirmation confirmation)
            throws UnsupportedFeatureException, OccurenceConstraintException;

    /**
     * Desconecta el cliente del servidor.
     */
    void disconnect();

    /**
     * Verifica si la conexión está cerrada.
     *
     * @return true si la conexión está cerrada, false en caso contrario.
     */
    boolean isClosed();

    /**
     * Obtiene el ID de la sesión actual del cliente.
     *
     * @return el UUID de la sesión.
     */
    UUID getSessionId();
}
