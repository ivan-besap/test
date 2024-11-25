package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation.SignCertificateResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.SignCertificateRequest;

/**
 * Funcionalidad: Firma de Certificados.
 *
 * <p>La clase `SignCertificateFeature` implementa la funcionalidad del protocolo OCPP 2.0.1
 * que permite a las estaciones de carga solicitar al sistema central la firma de un certificado
 * de seguridad. Esto es crucial para establecer conexiones seguras y autenticadas entre las estaciones
 * y el sistema central.
 */
public class SignCertificateFeature extends FunctionFeature {

    /**
     * Constructor de la clase SignCertificateFeature.
     *
     * @param ownerFunction La función propietaria que contiene esta característica.
     */
    public SignCertificateFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado a esta característica.
     *
     * @return La clase {@link SignCertificateRequest} que representa la solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return SignCertificateRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado a esta característica.
     *
     * @return La clase {@link SignCertificateResponse} que representa la respuesta.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return SignCertificateResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada a esta característica.
     *
     * @return Una cadena que contiene el nombre de la acción ("SignCertificate").
     */
    @Override
    public String getAction() {
        return "SignCertificate";
    }
}
