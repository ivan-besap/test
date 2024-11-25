package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Representa una respuesta de latido (Heartbeat) enviada desde el sistema de gestión de carga
 * (CSMS) a la estación de carga. Esta respuesta incluye la hora actual del CSMS.
 */
public class HeartbeatResponse extends Confirmation {

    /** Datos personalizados asociados a la respuesta. */
    @Nullable
    private CustomData customData;

    /** Hora actual del CSMS. */
    private ZonedDateTime currentTime;

    /**
     * Constructor para la clase HeartbeatResponse.
     *
     * @param currentTime Hora actual del CSMS.
     */
    public HeartbeatResponse(ZonedDateTime currentTime) {
        setCurrentTime(currentTime);
    }

    /**
     * Obtiene los datos personalizados asociados a la respuesta.
     *
     * @return Objeto CustomData o {@code null} si no se han definido.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados para la respuesta.
     *
     * @param customData Objeto CustomData con información adicional o {@code null}.
     * @throws PropertyConstraintException si los datos personalizados no son válidos.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Objeto CustomData a verificar.
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados a la respuesta.
     *
     * @param customData Objeto CustomData con información adicional.
     * @return La instancia actual de HeartbeatResponse.
     */
    public HeartbeatResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene la hora actual del CSMS.
     *
     * @return Hora actual del CSMS como ZonedDateTime.
     */
    public ZonedDateTime getCurrentTime() {
        return currentTime;
    }

    /**
     * Establece la hora actual del CSMS.
     *
     * @param currentTime Hora actual del CSMS.
     * @throws PropertyConstraintException si la hora proporcionada no es válida.
     */
    public void setCurrentTime(ZonedDateTime currentTime) {
        if (!isValidCurrentTime(currentTime)) {
            throw new PropertyConstraintException(currentTime, "La hora actual no es válida.");
        }
        this.currentTime = currentTime;
    }

    /**
     * Verifica si la hora actual es válida.
     *
     * @param currentTime Hora a verificar.
     * @return {@code true} si la hora es válida, {@code false} en caso contrario.
     */
    private boolean isValidCurrentTime(ZonedDateTime currentTime) {
        return currentTime != null;
    }

    /**
     * Valida si esta respuesta cumple con los requisitos establecidos.
     *
     * @return {@code true} si la respuesta es válida, {@code false} en caso contrario.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidCurrentTime(currentTime);
    }

    /**
     * Compara esta instancia con otro objeto.
     *
     * @param o Objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HeartbeatResponse that = (HeartbeatResponse) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(currentTime, that.currentTime);
    }

    /**
     * Calcula el código hash de esta instancia.
     *
     * @return Código hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(customData, currentTime);
    }

    /**
     * Devuelve una representación en texto de esta respuesta.
     *
     * @return Cadena que representa el objeto.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("currentTime", currentTime)
                .add("isValid", validate())
                .toString();
    }
}
