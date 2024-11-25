package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation.Get15118EVCertificateResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.Get15118EVCertificateRequest;

/**
 * Característica que representa el mensaje de obtención de certificado 15118 EV (Get15118EVCertificate) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite al punto de carga solicitar al servidor (CSMS) un certificado PKI
 * necesario para la comunicación segura entre el vehículo eléctrico (EV) y el sistema de carga.
 * </p>
 */
public class Get15118EVCertificateFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para obtener un certificado 15118 EV.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public Get15118EVCertificateFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link Get15118EVCertificateRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return Get15118EVCertificateRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link Get15118EVCertificateResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return Get15118EVCertificateResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "Get15118EVCertificate".
     */
    @Override
    public String getAction() {
        return "Get15118EVCertificate";
    }
}
