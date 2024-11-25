package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Utils.MeterValue;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud para enviar valores medidos (Meter Values).
 * <p>
 * Este mensaje se utiliza para transmitir una colección de valores medidos
 * de un medidor eléctrico asociado a un punto de carga.
 */
public class MeterValuesRequest extends RequestWithId {

    /** Datos personalizados asociados a la solicitud. */
    @Nullable
    private CustomData customData;

    /**
     * Identificador del EVSE.
     * <p>
     * Es un número que designa un EVSE específico de la estación de carga.
     * El valor '0' se utiliza para designar el medidor de energía principal.
     */
    private Integer evseId;

    /**
     * Valores medidos.
     * <p>
     * Colección de uno o más valores muestreados. Todos los valores de esta colección
     * corresponden a un único momento en el tiempo.
     */
    private MeterValue[] meterValue;

    /**
     * Constructor de la clase MeterValuesRequest.
     *
     * @param evseId Identificador del EVSE.
     * @param meterValue Colección de valores medidos.
     */
    public MeterValuesRequest(Integer evseId, MeterValue[] meterValue) {
        setEvseId(evseId);
        setMeterValue(meterValue);
    }

    /**
     * Obtiene los datos personalizados asociados a esta solicitud.
     *
     * @return Un objeto {@link CustomData} con los datos personalizados, o {@code null}
     *         si no se han establecido.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados para esta solicitud.
     *
     * @param customData Un objeto {@link CustomData} con los datos personalizados.
     * @throws PropertyConstraintException Si los datos personalizados no son válidos.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados proporcionados son válidos.
     *
     * @param customData Los datos personalizados a validar.
     * @return {@code true} si los datos personalizados son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Asocia datos personalizados a esta solicitud y devuelve la instancia actual.
     *
     * @param customData Un objeto {@link CustomData} con los datos personalizados.
     * @return Esta instancia de {@link MeterValuesRequest}.
     */
    public MeterValuesRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el identificador del EVSE.
     *
     * @return Un número entero que designa el EVSE asociado.
     */
    public Integer getEvseId() {
        return evseId;
    }

    /**
     * Establece el identificador del EVSE.
     *
     * @param evseId Un número entero que designa el EVSE asociado.
     * @throws PropertyConstraintException Si el identificador del EVSE no es válido.
     */
    public void setEvseId(Integer evseId) {
        if (!isValidEvseId(evseId)) {
            throw new PropertyConstraintException(evseId, "El identificador del EVSE no es válido.");
        }
        this.evseId = evseId;
    }

    /**
     * Verifica si el identificador del EVSE proporcionado es válido.
     *
     * @param evseId El identificador del EVSE a validar.
     * @return {@code true} si el identificador del EVSE es válido, {@code false} en caso contrario.
     */
    private boolean isValidEvseId(Integer evseId) {
        return evseId != null;
    }

    /**
     * Obtiene la colección de valores medidos.
     *
     * @return Un arreglo de {@link MeterValue} que contiene los valores medidos.
     */
    public MeterValue[] getMeterValue() {
        return meterValue;
    }

    /**
     * Establece la colección de valores medidos.
     *
     * @param meterValue Un arreglo de {@link MeterValue} con los valores medidos.
     * @throws PropertyConstraintException Si los valores medidos no son válidos.
     */
    public void setMeterValue(MeterValue[] meterValue) {
        if (!isValidMeterValue(meterValue)) {
            throw new PropertyConstraintException(meterValue, "Los valores medidos no son válidos.");
        }
        this.meterValue = meterValue;
    }

    /**
     * Verifica si la colección de valores medidos es válida.
     *
     * @param meterValue La colección de valores medidos a validar.
     * @return {@code true} si la colección es válida, {@code false} en caso contrario.
     */
    private boolean isValidMeterValue(MeterValue[] meterValue) {
        return meterValue != null
                && meterValue.length >= 1
                && Arrays.stream(meterValue).allMatch(item -> item.validate());
    }

    /**
     * Valida la instancia actual de la solicitud.
     *
     * @return {@code true} si la instancia es válida, {@code false} en caso contrario.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidEvseId(evseId) && isValidMeterValue(meterValue);
    }

    /**
     * Determina si esta solicitud está relacionada con una transacción.
     *
     * @return {@code false}, ya que esta solicitud no está directamente relacionada con una transacción.
     */
    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public String getAction() {
        return "";
    }

    @Override
    public UUID getSessionIndex() {
        return null;
    }

    /**
     * Compara esta instancia con otro objeto para determinar si son iguales.
     *
     * @param o El objeto a comparar.
     * @return {@code true} si los objetos son iguales, {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MeterValuesRequest that = (MeterValuesRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(evseId, that.evseId)
                && Arrays.equals(meterValue, that.meterValue);
    }

    /**
     * Calcula el código hash para esta instancia.
     *
     * @return El código hash calculado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(customData, evseId, Arrays.hashCode(meterValue));
    }

    /**
     * Devuelve una representación en forma de cadena de esta instancia.
     *
     * @return Una cadena que representa esta instancia.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("evseId", evseId)
                .add("meterValue", meterValue)
                .add("isValid", validate())
                .toString();
    }
}
