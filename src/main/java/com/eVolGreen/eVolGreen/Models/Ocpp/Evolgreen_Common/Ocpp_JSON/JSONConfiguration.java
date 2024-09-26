package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON;

import java.util.HashMap;

/**
 * Configuración para el cliente JSON de OCPP.
 * Esta clase utiliza el patrón Singleton para asegurar una única instancia de configuración.
 */
public class JSONConfiguration {

    /**
     * Parámetro para habilitar/deshabilitar TCP_NODELAY.
     */
    public static final String TCP_NO_DELAY_PARAMETER = "TCP_NO_DELAY";

    /**
     * Parámetro para habilitar/deshabilitar la reutilización de direcciones.
     */
    public static final String REUSE_ADDR_PARAMETER = "REUSE_ADDR";

    /**
     * Parámetro para configurar un proxy.
     */
    public static final String PROXY_PARAMETER = "PROXY";

    /**
     * Parámetro para configurar el intervalo de ping.
     */
    public static final String PING_INTERVAL_PARAMETER = "PING_INTERVAL";

    /**
     * Parámetro para configurar el nombre de usuario.
     */
    public static final String USERNAME_PARAMETER = "USERNAME";

    /**
     * Parámetro para configurar la contraseña.
     */
    public static final String PASSWORD_PARAMETER = "PASSWORD";

    /**
     * Parámetro para habilitar/deshabilitar la conexión no bloqueante.
     */
    public static final String CONNECT_NON_BLOCKING_PARAMETER = "CONNECT_NON_BLOCKING";

    /**
     * Parámetro para configurar el tiempo de espera de conexión en milisegundos.
     */
    public static final String CONNECT_TIMEOUT_IN_MS_PARAMETER = "CONNECT_TIMEOUT_IN_MS";

    /**
     * Parámetro para configurar el número de trabajadores WebSocket.
     */
    public static final String WEBSOCKET_WORKER_COUNT = "WEBSOCKET_WORKER_COUNT";

    /**
     * Parámetro para habilitar/deshabilitar la comprobación de salud HTTP.
     */
    public static final String HTTP_HEALTH_CHECK_ENABLED = "HTTP_HEALTH_CHECK_ENABLED";

    /**
     * Parámetro para configurar la longitud mínima de contraseña para eVolGreen CP.
     */
    public static final String EVOLGREEN_CP_MIN_PASSWORD_LENGTH = "EVOLGREEN_CP_MIN_PASSWORD_LENGTH";

    /**
     * Parámetro para configurar la longitud máxima de contraseña para eVolGreen CP.
     */
    public static final String EVOLGREEN_CP_MAX_PASSWORD_LENGTH = "EVOLGREEN_CP_MAX_PASSWORD_LENGTH";

    private final HashMap<String, Object> parameters = new HashMap<>();

    private JSONConfiguration() {}

    private static final JSONConfiguration instance = new JSONConfiguration();

    /**
     * Obtiene la instancia única de JSONConfiguration.
     *
     * @return La instancia de JSONConfiguration.
     */
    public static JSONConfiguration get() {
        return instance;
    }

    /**
     * Establece un parámetro de configuración.
     *
     * @param name El nombre del parámetro.
     * @param value El valor del parámetro.
     * @return La instancia actual de JSONConfiguration para encadenar llamadas.
     */
    public <T> JSONConfiguration setParameter(String name, T value) {
        parameters.put(name, value);
        return this;
    }

    /**
     * Obtiene el valor de un parámetro de configuración.
     *
     * @param name El nombre del parámetro.
     * @return El valor del parámetro, o null si no existe.
     */
    @SuppressWarnings("unchecked") // Suprime la advertencia
    public <T> T getParameter(String name) {
        //noinspection unchecked
        return (T) parameters.get(name);
    }

    /**
     * Obtiene el valor de un parámetro de configuración, con un valor por defecto si no existe.
     *
     * @param name El nombre del parámetro.
     * @param defaultValue El valor por defecto a devolver si el parámetro no existe.
     * @return El valor del parámetro, o el valor por defecto si no existe.
     */
    @SuppressWarnings("unchecked") // Suprime la advertencia
    public <T> T getParameter(String name, T defaultValue) {
        //noinspection unchecked
        T value = (T) parameters.get(name);
        return value != null ? value : defaultValue;
    }
}
