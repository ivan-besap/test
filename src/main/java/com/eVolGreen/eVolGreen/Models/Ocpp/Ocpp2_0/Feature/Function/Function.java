package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.FunctionFeature;

import java.util.UUID;

/**
 * Interfaz utilizada para definir perfiles de características en OCPP.
 * <p>
 * Los perfiles de características agrupan las funcionalidades específicas
 * soportadas por un servidor o cliente, permitiendo manejar solicitudes y generar
 * respuestas según las especificaciones del protocolo OCPP.
 * </p>
 */
public interface Function {

    /**
     * Obtiene una lista de las características soportadas por esta función.
     *
     * @return un arreglo de {@link FunctionFeature} que representa las características soportadas.
     */
    FunctionFeature[] getFeatureList();

    /**
     * Maneja una solicitud OCPP y genera una respuesta correspondiente.
     *
     * @param sessionIndex el identificador de la sesión que originó la solicitud.
     * @param request la solicitud {@link Request} a ser procesada.
     * @return la confirmación {@link Confirmation} que será enviada como respuesta.
     */
    Confirmation handleRequest(UUID sessionIndex, Request request);
}
