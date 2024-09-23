package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.AuthenticationException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.SessionInformation;

/**
 * Interface que define los eventos que deben ser manejados durante las sesiones de conexión.
 * <p>
 * {@code ListenerEvents} se utiliza para gestionar eventos como la autenticación y la creación
 * de nuevas sesiones en la plataforma OCPP.
 * </p>
 */
public interface ListenerEvents {

    /**
     * Auténtica una sesión basada en la información proporcionada, como nombre de usuario y contraseña.
     * <p>
     * Este método valida la autenticación de la sesión antes de que continúe su procesamiento. Si la autenticación
     * falla, se lanzará una {@link AuthenticationException}.
     * </p>
     *
     * @param information Los detalles de la sesión que se está autenticando.
     * @param username El nombre de usuario proporcionado para la autenticación.
     * @param password La contraseña proporcionada para la autenticación.
     * @throws AuthenticationException Si la autenticación falla.
     */
    void authenticateSession(SessionInformation information, String username, String password)
            throws AuthenticationException;

    /**
     * Crea una nueva sesión cuando se detecta una nueva conexión.
     * <p>
     * Este método es llamado cada vez que se establece una nueva sesión, permitiendo que el sistema gestione
     * la nueva conexión establecida con el servidor.
     * </p>
     *
     * @param session La nueva sesión que se ha creado.
     * @param information Información detallada sobre la sesión que se acaba de crear.
     */
    void newSession(ISession session, SessionInformation information);
}
