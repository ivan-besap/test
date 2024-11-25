package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Confirmation.CostUpdatedResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Requests.CostUpdatedRequest;

/**
 * Característica que representa el mensaje de actualización de costos (CostUpdated) en OCPP 2.0.1.
 * <p>
 * Este mensaje es enviado por un punto de carga al servidor (CSMS) para notificar un cambio en el costo
 * asociado a la transacción en curso.
 * </p>
 */
public class CostUpdatedFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para manejar la actualización de costos.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public CostUpdatedFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link CostUpdatedRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return CostUpdatedRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link CostUpdatedResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return CostUpdatedResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "CostUpdated".
     */
    @Override
    public String getAction() {
        return "CostUpdated";
    }
}
