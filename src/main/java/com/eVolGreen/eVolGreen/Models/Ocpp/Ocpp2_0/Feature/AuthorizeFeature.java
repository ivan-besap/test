package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.AuthorizeResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.AuthorizeRequest;

/**
 * Representa la funcionalidad de autorización en OCPP 2.0.1.
 * <p>
 * Este mensaje se utiliza para verificar la autorización de un cliente antes
 * de iniciar una sesión de carga.
 * </p>
 *
 * <p>
 * Define el tipo de solicitud y confirmación asociados, así como la acción correspondiente.
 * </p>
 */
public class AuthorizeFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad de autorización.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public AuthorizeFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link AuthorizeRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return AuthorizeRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link AuthorizeResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return AuthorizeResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "Authorize".
     */
    @Override
    public String getAction() {
        return "Authorize";
    }
}
