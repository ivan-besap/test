package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Confirmation.NotifyCustomerInformationResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Requests.NotifyCustomerInformationRequest;

/**
 * Característica que representa el mensaje para notificar información del cliente
 * (NotifyCustomerInformation) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite que el punto de carga informe al servidor (CSMS) sobre información
 * relevante del cliente, como detalles de contrato, facturación o cualquier dato adicional
 * solicitado previamente por el CSMS.
 * </p>
 */
public class NotifyCustomerInformationFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para manejar notificaciones de información del cliente.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public NotifyCustomerInformationFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link NotifyCustomerInformationRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return NotifyCustomerInformationRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link NotifyCustomerInformationResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return NotifyCustomerInformationResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "NotifyCustomerInformation".
     */
    @Override
    public String getAction() {
        return "NotifyCustomerInformation";
    }
}
