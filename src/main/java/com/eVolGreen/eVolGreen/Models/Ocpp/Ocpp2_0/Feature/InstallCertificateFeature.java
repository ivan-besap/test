package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation.InstallCertificateResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.InstallCertificateRequest;

/**
 * Característica que representa el mensaje para instalar un certificado (InstallCertificate) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite al servidor (CSMS) solicitar la instalación de un nuevo certificado en el punto de carga.
 * Es utilizado para gestionar la seguridad y autenticación del punto de carga mediante certificados.
 * </p>
 */
public class InstallCertificateFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para instalar un certificado.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public InstallCertificateFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link InstallCertificateRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return InstallCertificateRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link InstallCertificateResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return InstallCertificateResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "InstallCertificate".
     */
    @Override
    public String getAction() {
        return "InstallCertificate";
    }
}

