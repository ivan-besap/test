package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.SetVariableMonitoringResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.SetVariableMonitoringRequest;

/**
 * Funcionalidad: Configurar monitoreo de variables.
 *
 * <p>Esta clase implementa la funcionalidad OCPP que permite al sistema central establecer
 * configuraciones de monitoreo en las variables de las estaciones de carga. Esto incluye la
 * habilitación de alarmas, verificaciones de límites y otras acciones de supervisión basadas
 * en las variables configuradas.
 */
public class SetVariableMonitoringFeature extends FunctionFeature {

    /**
     * Constructor de la clase SetVariableMonitoringFeature.
     *
     * @param ownerFunction La función propietaria que contiene esta característica.
     */
    public SetVariableMonitoringFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado a esta característica.
     *
     * @return La clase {@link SetVariableMonitoringRequest} que representa la solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return SetVariableMonitoringRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado a esta característica.
     *
     * @return La clase {@link SetVariableMonitoringResponse} que representa la respuesta.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return SetVariableMonitoringResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada a esta característica.
     *
     * @return Una cadena que contiene el nombre de la acción ("SetVariableMonitoring").
     */
    @Override
    public String getAction() {
        return "SetVariableMonitoring";
    }
}
