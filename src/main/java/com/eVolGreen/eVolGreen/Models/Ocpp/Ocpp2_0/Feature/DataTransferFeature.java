package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.DataTransferResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.DataTransferRequest;

/**
 * Característica que representa el mensaje de transferencia de datos (DataTransfer) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite la comunicación de datos específicos entre el servidor (CSMS) y el punto de carga.
 * Se utiliza para transmitir información propietaria o específica que no está cubierta por otros mensajes estándar.
 * </p>
 */
public class DataTransferFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad de transferencia de datos.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public DataTransferFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link DataTransferRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return DataTransferRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link DataTransferResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return DataTransferResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "DataTransfer".
     */
    @Override
    public String getAction() {
        return "DataTransfer";
    }
}

