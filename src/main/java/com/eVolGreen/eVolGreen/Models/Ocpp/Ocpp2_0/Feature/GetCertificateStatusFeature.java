package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation.GetCertificateStatusResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.GetCertificateStatusRequest;

/**
 * Característica que representa el mensaje para obtener el estado de un certificado (GetCertificateStatus) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite al punto de carga o al servidor (CSMS) verificar el estado de un certificado
 * específico, asegurando su validez y confiabilidad dentro del sistema de carga.
 * </p>
 */
public class GetCertificateStatusFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para obtener el estado de un certificado.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public GetCertificateStatusFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link GetCertificateStatusRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return GetCertificateStatusRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link GetCertificateStatusResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetCertificateStatusResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "GetCertificateStatus".
     */
    @Override
    public String getAction() {
        return "GetCertificateStatus";
    }
}
