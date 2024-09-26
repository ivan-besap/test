package com.eVolGreen.eVolGreen.Models.Ocpp.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Firmware.Confirmations.GetDiagnosticsConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Firmware.Request.GetDiagnosticsRequest;


/**
 * La clase {@code GetDiagnosticsFeature} permite al sistema solicitar un diagnóstico de una estación
 * de carga a través del protocolo OCPP. Esta solicitud genera la recuperación de un informe de diagnóstico,
 * permitiendo a los administradores analizar el estado de la estación.
 * <p>
 * El diagnóstico se utiliza comúnmente para verificar el estado de una estación antes de realizar
 * actualizaciones o para detectar problemas operacionales.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     GetDiagnosticsFeature feature = new GetDiagnosticsFeature(perfilPropietario);
 *     {@code Class<? extends Request> requestType = feature.getRequestType();}
 *     {@code Class<? extends Confirmation> confirmationType = feature.getConfirmationType();}
 *     String action = feature.getAction();
 * </pre>
 */
public class GetDiagnosticsFeature extends ProfileFeature {

    /**
     * Constructor que inicializa la funcionalidad de obtención de diagnóstico con el perfil asociado.
     *
     * @param ownerProfile el perfil propietario de esta funcionalidad.
     */
    public GetDiagnosticsFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * Devuelve la clase que representa el tipo de solicitud para obtener el diagnóstico de una estación de carga.
     *
     * @return la clase {@link GetDiagnosticsRequest} correspondiente al tipo de solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return GetDiagnosticsRequest.class;
    }

    /**
     * Devuelve la clase que representa el tipo de confirmación para la solicitud de diagnóstico.
     *
     * @return la clase {@link GetDiagnosticsConfirmation} correspondiente al tipo de confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetDiagnosticsConfirmation.class;
    }

    /**
     * Devuelve la acción que representa la funcionalidad de solicitud de diagnóstico de la estación de carga.
     * En este caso, la acción es "GetDiagnostics".
     *
     * @return una cadena de texto con el nombre de la acción: "GetDiagnostics".
     */
    @Override
    public String getAction() {
        return "GetDiagnostics";
    }
}
