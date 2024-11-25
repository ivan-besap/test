package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud de latido (Heartbeat) enviada por la estación de carga al sistema.
 * Este mensaje se utiliza para indicar que la estación sigue activa y conectada.
 */
public class HeartbeatRequest extends RequestWithId {

    /** Datos personalizados para la solicitud. */
    @Nullable
    private CustomData customData;

    /** Constructor para la clase HeartbeatRequest. */
    public HeartbeatRequest() {}

    /**
     * Obtiene los datos personalizados asociados a la solicitud.
     *
     * @return Objeto CustomData o {@code null} si no se ha definido.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados para la solicitud.
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
     * @return {@code true} si los datos personalizados son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados a la solicitud.
     *
     * @param customData Objeto CustomData con información adicional.
     * @return La instancia actual de HeartbeatRequest.
     */
    public HeartbeatRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Valida si la solicitud es correcta.
     *
     * @return {@code true} si la solicitud es válida, {@code false} en caso contrario.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData);
    }

    /**
     * Indica si esta solicitud está relacionada con una transacción.
     *
     * @return {@code false}, ya que el latido no está relacionado con transacciones.
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
     * Verifica la igualdad entre esta instancia y otro objeto.
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
        HeartbeatRequest that = (HeartbeatRequest) o;
        return Objects.equals(customData, that.customData);
    }

    /**
     * Calcula el código hash de esta instancia.
     *
     * @return Código hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(customData);
    }

    /**
     * Devuelve una representación en texto de esta solicitud.
     *
     * @return Cadena que representa el objeto.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("isValid", validate())
                .toString();
    }
}
