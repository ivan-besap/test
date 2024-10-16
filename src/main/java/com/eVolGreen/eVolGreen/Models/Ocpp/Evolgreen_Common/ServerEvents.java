package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common;


import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.AuthenticationException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.SessionInformation;

import java.util.UUID;

/**
 * Interfaz para manejar eventos relacionados con el servidor en un contexto de OCPP.
 * Estos eventos incluyen la autenticación de sesiones, la creación de nuevas sesiones y la
 * pérdida de sesiones activas.
 */
public interface ServerEvents {

    /**
     * Método llamado cuando se requiere autenticar una nueva sesión.
     *
     * @param information Información de la sesión proporcionada por el cliente.
     * @param username Nombre de usuario enviado por el cliente.
     * @param password Contraseña enviada por el cliente.
     * @throws AuthenticationException si la autenticación falla.
     */
    void authenticateSession(SessionInformation information, String username, String password) throws AuthenticationException;

    /**
     * Método llamado cuando una nueva sesión ha sido establecida exitosamente.
     *
     * @param sessionIndex El identificador único de la sesión.
     * @param information Información detallada sobre la sesión recién creada.
     */
    void newSession(UUID sessionIndex, SessionInformation information);

    /**
     * Método llamado cuando una sesión previamente establecida se ha perdido.
     *
     * @param sessionIndex El identificador único de la sesión perdida.
     */
    void lostSession(UUID sessionIndex);

    void handleError(String uniqueId, String errorCode, String errorDescription, Object payload);

    void handleConfirmation(String uniqueId, Confirmation confirmation);

    Confirmation handleRequest(Request request);
}
