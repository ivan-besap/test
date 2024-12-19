package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.ResetConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.ResetRequest;

/**
 * La clase {@code ResetFeature} gestiona la funcionalidad de reiniciar una estación de carga
 * dentro del protocolo OCPP. Esto permite al sistema central enviar una solicitud para reiniciar
 * completamente el punto de carga, ya sea de manera suave (soft reset) o forzada (hard reset).
 * <p>
 * Esta funcionalidad es crucial en escenarios donde se requiere un reinicio para resolver errores
 * o simplemente para realizar mantenimientos periódicos en el sistema.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     ResetFeature resetFeature = new ResetFeature(perfilPropietario);
 *     {@code Class<? extends Request> requestType = resetFeature.getRequestType();}
 *     {@code Class<? extends Confirmation> confirmationType = resetFeature.getConfirmationType();}
 *     String action = resetFeature.getAction();
 * </pre>
 */
public class ResetFeature extends ProfileFeature {

    /**
     * Constructor que inicializa la funcionalidad de reinicio con el perfil asociado.
     *
     * @param ownerProfile el perfil propietario de esta funcionalidad.
     */
    public ResetFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * Devuelve la clase que representa el tipo de solicitud para reiniciar una estación de carga.
     *
     * @return la clase {@link ResetRequest} correspondiente al tipo de solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return ResetRequest.class;
    }

    /**
     * Devuelve la clase que representa el tipo de confirmación para la solicitud de reinicio.
     *
     * @return la clase {@link ResetConfirmation} correspondiente al tipo de confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ResetConfirmation.class;
    }

    /**
     * Devuelve la acción que representa la funcionalidad de reinicio.
     * En este caso, la acción es "Reset".
     *
     * @return una cadena de texto con el nombre de la acción: "Reset".
     */
    @Override
    public String getAction() {
        return "Reset";
    }
}
