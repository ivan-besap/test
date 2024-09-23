package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities;

/**
 * Interfaz que define un método para la creación de sesiones.
 * <p>
 * Un {@code ISessionFactory} actúa como una fábrica para crear nuevas instancias de {@link ISession}
 * y establece la comunicación entre las diferentes partes del sistema, como los clientes o servidores OCPP.
 * </p>
 */
public interface ISessionFactory {

    /**
     * Crea una nueva instancia de una sesión utilizando un objeto {@link Communicator}.
     * <p>
     * Este método es responsable de generar una nueva sesión que manejará la comunicación
     * entre los participantes. El objeto {@link Communicator} proporciona la infraestructura
     * necesaria para enviar y recibir mensajes dentro de esa sesión.
     * </p>
     *
     * @param communicator El objeto {@link Communicator} que gestiona las operaciones de comunicación.
     * @return una instancia de {@link ISession} configurada con el comunicador proporcionado.
     */
    ISession createSession(Communicator communicator);
}
