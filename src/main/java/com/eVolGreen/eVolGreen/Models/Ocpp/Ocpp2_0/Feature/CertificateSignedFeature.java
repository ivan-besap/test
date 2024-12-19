package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation.CertificateSignedResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.CertificateSignedRequest;

/**
 * Característica que representa el mensaje de firma de certificado (CertificateSigned) en OCPP 2.0.1.
 * <p>
 * Este mensaje se utiliza para que el servidor (CSMS) envíe un certificado firmado a un punto de carga
 * para su instalación, como parte del proceso de gestión de certificados de seguridad.
 * </p>
 */
public class CertificateSignedFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad de firma de certificados.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public CertificateSignedFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link CertificateSignedRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return CertificateSignedRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link CertificateSignedResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return CertificateSignedResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "CertificateSigned".
     */
    @Override
    public String getAction() {
        return "CertificateSigned";
    }
}
