package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Securityext;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.ProfileFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations.GetLogConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.GetLogRequest;

/**
 * La clase {@code GetLogFeature} implementa la funcionalidad para manejar solicitudes
 * y confirmaciones relacionadas con la obtención de logs del sistema.
 *
 * <p>Esta característica es utilizada para solicitar logs, como logs de diagnóstico
 * o logs de seguridad, desde el sistema de carga.</p>
 *
 * <pre>
 * Ejemplo de uso:
 *
 * // Crear el perfil y la característica
 * Profile ownerProfile = new ServerSecurityExtProfile(eventHandler);
 * GetLogFeature feature = new GetLogFeature(ownerProfile);
 *
 * // Crear una solicitud para obtener logs
 * GetLogRequest request = new GetLogRequest(LogEnumType.DiagnosticsLog, 12345, logParameters);
 *
 * // Manejar la solicitud
 * GetLogConfirmation confirmation = (GetLogConfirmation) feature.getOwnerProfile()
 *      .handleRequest(sessionId, request);
 * </pre>
 */
public class GetLogFeature extends ProfileFeature {

    /**
     * Constructor para inicializar la característica "GetLog".
     *
     * @param ownerProfile El perfil al que pertenece esta característica.
     */
    public GetLogFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la clase {@link GetLogRequest} que representa el tipo de solicitud
     * que maneja esta característica.</p>
     *
     * @return La clase del tipo de solicitud {@code GetLogRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return GetLogRequest.class;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la clase {@link GetLogConfirmation} que representa el tipo de confirmación
     * que maneja esta característica.</p>
     *
     * @return La clase del tipo de confirmación {@code GetLogConfirmation}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetLogConfirmation.class;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la acción asociada con esta característica. En este caso, la acción es "GetLog".</p>
     *
     * @return La acción de esta característica, que es "GetLog".
     */
    @Override
    public String getAction() {
        return "GetLog";
    }
}
