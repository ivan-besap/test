package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation.GetInstalledCertificateIdsResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.GetInstalledCertificateIdsRequest;

/**
 * Característica que representa el mensaje para obtener los IDs de certificados instalados
 * (GetInstalledCertificateIds) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite al servidor (CSMS) solicitar la lista de identificadores de certificados
 * instalados en un punto de carga, facilitando la gestión y supervisión de los certificados.
 * </p>
 */
public class GetInstalledCertificateIdsFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para obtener los IDs de los certificados instalados.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public GetInstalledCertificateIdsFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link GetInstalledCertificateIdsRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return GetInstalledCertificateIdsRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link GetInstalledCertificateIdsResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetInstalledCertificateIdsResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "GetInstalledCertificateIds".
     */
    @Override
    public String getAction() {
        return "GetInstalledCertificateIds";
    }
}

