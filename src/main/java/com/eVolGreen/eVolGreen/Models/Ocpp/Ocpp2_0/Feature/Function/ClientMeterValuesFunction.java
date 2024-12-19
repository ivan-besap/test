package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.FunctionFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.MeterValuesFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.MeterValuesRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Utils.MeterValue;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que proporciona métodos para crear solicitudes y manejar respuestas
 * relacionadas con el envío de valores de medición del medidor (MeterValues).
 *
 * Implementa las funcionalidades del bloque funcional "MeterValues" definido en OCPP 2.0.1.
 */
public class ClientMeterValuesFunction implements Function {

    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor de la clase.
     *
     * Inicializa la lista de características asociadas al bloque funcional
     * MeterValues.
     */
    public ClientMeterValuesFunction() {
        features = new ArrayList<>();
        features.add(new MeterValuesFeature(null));
    }

    /**
     * Devuelve la lista de características (features) soportadas por esta función.
     *
     * @return Un arreglo de objetos {@link FunctionFeature} que representan las
     *         características soportadas.
     */
    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    /**
     * Maneja las solicitudes entrantes para este bloque funcional.
     *
     * Actualmente, no se implementa ninguna lógica para manejar solicitudes en
     * esta clase.
     *
     * @param sessionIndex El identificador de la sesión en la que se recibió la
     *                     solicitud.
     * @param request       La solicitud que se debe manejar.
     * @return Siempre retorna {@code null}, ya que no se manejan solicitudes.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return null;
    }

    /**
     * Crea una solicitud de tipo {@link MeterValuesRequest} con los campos requeridos.
     *
     * Esta solicitud se utiliza para enviar uno o más valores de medición del
     * medidor relacionados con una estación de carga específica.
     *
     * @param evseId     Un identificador que designa un EVSE específico de la
     *                   estación de carga. El valor `0` se utiliza para designar el
     *                   medidor principal.
     * @param meterValue Una colección de uno o más valores muestreados. Todos los
     *                   valores muestreados en un {@link MeterValue} se toman en el
     *                   mismo momento.
     * @return Una instancia de {@link MeterValuesRequest} con los datos
     *         especificados.
     */
    public MeterValuesRequest createMeterValuesRequest(Integer evseId, MeterValue[] meterValue) {
        return new MeterValuesRequest(evseId, meterValue);
    }
}
