package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation.DeleteCertificateResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.DeleteCertificateRequest;

/**
 * Característica que representa el mensaje para eliminar un certificado (DeleteCertificate) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite al servidor (CSMS) o al punto de carga eliminar un certificado específico
 * de su almacenamiento de certificados.
 * </p>
 */
public class DeleteCertificateFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para eliminar certificados.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public DeleteCertificateFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link DeleteCertificateRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return DeleteCertificateRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link DeleteCertificateResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return DeleteCertificateResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "DeleteCertificate".
     */
    @Override
    public String getAction() {
        return "DeleteCertificate";
    }
}

