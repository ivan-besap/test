package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Feature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;

import java.util.UUID;

/**
 * Clase abstracta que representa una característica dentro de un perfil de función en OCPP.
 * <p>
 * Cada característica está asociada a una función y permite manejar solicitudes específicas,
 * delegando la lógica al {@link Function} propietario.
 * </p>
 */
public abstract class FunctionFeature implements Feature {

    private final Function function;

    /**
     * Crea un enlace con la función propietaria de esta característica.
     *
     * @param ownerFunction la {@link Function} que posee esta característica.
     */
    public FunctionFeature(Function ownerFunction) {
        this.function = ownerFunction;
    }

    /**
     * Maneja una solicitud llamando a la función propietaria.
     *
     * @param sessionIndex el identificador de la sesión que originó la solicitud.
     * @param request la solicitud {@link Request} a ser procesada.
     * @return la confirmación {@link Confirmation} generada como respuesta.
     */
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return function.handleRequest(sessionIndex, request);
    }
}
