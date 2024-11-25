package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Confirmation.CustomerInformationResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Requests.CustomerInformationRequest;

/**
 * Característica que representa el mensaje de información del cliente (CustomerInformation) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite al servidor (CSMS) solicitar o enviar información relacionada con un cliente
 * específico para diversos propósitos, como informes, facturación o gestión de usuarios.
 * </p>
 */
public class CustomerInformationFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad de información del cliente.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public CustomerInformationFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link CustomerInformationRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return CustomerInformationRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link CustomerInformationResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return CustomerInformationResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "CustomerInformation".
     */
    @Override
    public String getAction() {
        return "CustomerInformation";
    }
}
