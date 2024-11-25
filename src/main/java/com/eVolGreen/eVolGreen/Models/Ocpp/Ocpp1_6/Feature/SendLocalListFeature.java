package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.LocalAuthList.Confirmations.SendLocalListConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.LocalAuthList.Request.SendLocalListRequest;


/**
 * La clase {@code SendLocalListFeature} gestiona la funcionalidad de envío de listas locales 
 * de autorizaciones en el protocolo OCPP. Permite que el sistema central actualice o envíe una 
 * lista de autenticación al punto de carga, lo que facilita el manejo de usuarios autorizados 
 * para utilizar los puntos de carga.
 * <p>
 * Este feature es esencial en escenarios donde se requiere gestionar la lista de autenticación local,
 * especialmente para estaciones de carga fuera de línea o con conectividad limitada.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     SendLocalListFeature sendLocalListFeature = new SendLocalListFeature(perfilPropietario);
 *     {@code Class<? extends Request> requestType = sendLocalListFeature.getRequestType();}
 *     {@code Class<? extends Confirmation> confirmationType = sendLocalListFeature.getConfirmationType();}
 *     String action = sendLocalListFeature.getAction();
 * </pre>
 */
public class SendLocalListFeature extends ProfileFeature {

    /**
     * Constructor que inicializa la funcionalidad de envío de lista local con el perfil asociado.
     *
     * @param ownerProfile el perfil propietario de esta funcionalidad.
     */
    public SendLocalListFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * Devuelve la clase que representa el tipo de solicitud para enviar la lista local.
     *
     * @return la clase {@link SendLocalListRequest} correspondiente al tipo de solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return SendLocalListRequest.class;
    }

    /**
     * Devuelve la clase que representa el tipo de confirmación para el envío de la lista local.
     *
     * @return la clase {@link SendLocalListConfirmation} correspondiente al tipo de confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return SendLocalListConfirmation.class;
    }

    /**
     * Devuelve la acción que representa la funcionalidad de envío de lista local.
     * En este caso, la acción es "SendLocalList".
     *
     * @return una cadena de texto con el nombre de la acción: "SendLocalList".
     */
    @Override
    public String getAction() {
        return "SendLocalList";
    }
}
