package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Confirmation.GetLocalListVersionResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests.GetLocalListVersionRequest;

/**
 * Característica que representa el mensaje para obtener la versión de la lista local (GetLocalListVersion) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite al servidor (CSMS) consultar la versión de la lista local de autorizaciones
 * almacenada en un punto de carga, para garantizar la sincronización de datos entre ambos sistemas.
 * </p>
 */
public class GetLocalListVersionFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para obtener la versión de la lista local.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public GetLocalListVersionFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link GetLocalListVersionRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return GetLocalListVersionRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link GetLocalListVersionResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetLocalListVersionResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "GetLocalListVersion".
     */
    @Override
    public String getAction() {
        return "GetLocalListVersion";
    }
}

