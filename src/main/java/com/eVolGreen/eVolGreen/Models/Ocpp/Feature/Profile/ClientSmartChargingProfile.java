package com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.SmartCharging.*;
import java.util.ArrayList;
import java.util.UUID;

/**
 * La clase {@code ClientSmartChargingProfile} gestiona las funciones del perfil de carga inteligente
 * en una estación de carga. Este perfil permite que el backend gestione eventos relacionados
 * con la carga, incluyendo la limpieza y configuración de perfiles de carga, así como la obtención
 * de horarios compuestos de carga.
 */
public class ClientSmartChargingProfile implements Profile {

    private ClientSmartChargingEventHandler eventHandler;
    private ArrayList<Feature> features;

    /**
     * Constructor que inicializa el perfil de carga inteligente con su manejador de eventos asociado.
     *
     * @param handler el manejador de eventos {@link ClientSmartChargingEventHandler}.
     */
    public ClientSmartChargingProfile(ClientSmartChargingEventHandler handler) {
        features = new ArrayList<>();
        eventHandler = handler;

        features.add(new ClearChargingProfileFeature(this));
        features.add(new GetCompositeScheduleFeature(this));
        features.add(new SetChargingProfileFeature(this));
    }

    /**
     * Devuelve una lista de las características (features) soportadas por este perfil.
     *
     * @return un array de {@link ProfileFeature} con las funcionalidades disponibles.
     */
    @Override
    public ProfileFeature[] getFeatureList() {
        return features.toArray(new ProfileFeature[0]);
    }

    /**
     * Maneja las solicitudes entrantes y delega el procesamiento a los manejadores de eventos
     * adecuados.
     *
     * @param sessionIndex el identificador de la sesión.
     * @param request la solicitud entrante.
     * @return una instancia de {@link Confirmation} como respuesta a la solicitud.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        Confirmation result = null;

        if (request instanceof ClearChargingProfileRequest) {
            result = eventHandler.handleClearChargingProfileRequest((ClearChargingProfileRequest) request);
        } else if (request instanceof GetCompositeScheduleRequest) {
            result = eventHandler.handleGetCompositeScheduleRequest((GetCompositeScheduleRequest) request);
        } else if (request instanceof SetChargingProfileRequest) {
            result = eventHandler.handleSetChargingProfileRequest((SetChargingProfileRequest) request);
        }

        return result;
    }
}
