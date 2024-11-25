package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud de Notificación de Evento de Seguridad
 *
 * <p>Esta clase representa una solicitud enviada por la estación de carga para notificar
 * un evento de seguridad. Incluye información sobre el tipo de evento, la fecha y hora
 * del mismo, y detalles técnicos adicionales.
 */
public class SecurityEventNotificationRequest extends RequestWithId {

    /** Datos personalizados relacionados con el evento de seguridad. */
    @Nullable
    private CustomData customData;

    /**
     * Tipo de evento de seguridad.
     *
     * <p>Este valor debe coincidir con uno de los valores predefinidos en la lista de eventos
     * de seguridad.
     */
    private String type;

    /** Fecha y hora en la que ocurrió el evento de seguridad. */
    private ZonedDateTime timestamp;

    /** Información técnica adicional sobre el evento de seguridad. */
    @Nullable private String techInfo;

    /**
     * Constructor para la clase SecurityEventNotificationRequest.
     *
     * @param type Tipo de evento de seguridad.
     * @param timestamp Fecha y hora en la que ocurrió el evento de seguridad.
     */
    public SecurityEventNotificationRequest(String type, ZonedDateTime timestamp) {
        setType(type);
        setTimestamp(timestamp);
    }

    /**
     * Obtiene los datos personalizados relacionados con el evento de seguridad.
     *
     * @return Datos personalizados relacionados con el evento de seguridad.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados relacionados con el evento de seguridad.
     *
     * @param customData Datos personalizados.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados son inválidos.");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a verificar.
     * @return {@code true} si los datos son válidos, {@code false} de lo contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados al evento de seguridad.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia actualizada.
     */
    public SecurityEventNotificationRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el tipo de evento de seguridad.
     *
     * @return Tipo de evento de seguridad.
     */
    public String getType() {
        return type;
    }

    /**
     * Establece el tipo de evento de seguridad.
     *
     * @param type Tipo de evento de seguridad.
     */
    public void setType(String type) {
        if (!isValidType(type)) {
            throw new PropertyConstraintException(type, "El tipo de evento es inválido.");
        }
        this.type = type;
    }

    /**
     * Verifica si el tipo de evento es válido.
     *
     * @param type Tipo de evento a verificar.
     * @return {@code true} si el tipo es válido, {@code false} de lo contrario.
     */
    private boolean isValidType(String type) {
        return type != null && type.length() <= 50;
    }

    /**
     * Obtiene la fecha y hora en la que ocurrió el evento.
     *
     * @return Fecha y hora del evento.
     */
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Establece la fecha y hora en la que ocurrió el evento.
     *
     * @param timestamp Fecha y hora del evento.
     */
    public void setTimestamp(ZonedDateTime timestamp) {
        if (!isValidTimestamp(timestamp)) {
            throw new PropertyConstraintException(timestamp, "La fecha y hora son inválidas.");
        }
        this.timestamp = timestamp;
    }

    /**
     * Verifica si la fecha y hora son válidas.
     *
     * @param timestamp Fecha y hora a verificar.
     * @return {@code true} si la fecha y hora son válidas, {@code false} de lo contrario.
     */
    private boolean isValidTimestamp(ZonedDateTime timestamp) {
        return timestamp != null;
    }

    /**
     * Obtiene la información técnica adicional del evento.
     *
     * @return Información técnica adicional.
     */
    @Nullable
    public String getTechInfo() {
        return techInfo;
    }

    /**
     * Establece información técnica adicional sobre el evento.
     *
     * @param techInfo Información técnica adicional.
     */
    public void setTechInfo(@Nullable String techInfo) {
        if (!isValidTechInfo(techInfo)) {
            throw new PropertyConstraintException(techInfo, "La información técnica es inválida.");
        }
        this.techInfo = techInfo;
    }

    /**
     * Verifica si la información técnica adicional es válida.
     *
     * @param techInfo Información técnica a verificar.
     * @return {@code true} si la información técnica es válida, {@code false} de lo contrario.
     */
    private boolean isValidTechInfo(@Nullable String techInfo) {
        return techInfo == null || techInfo.length() <= 255;
    }

    /**
     * Agrega información técnica adicional al evento de seguridad.
     *
     * @param techInfo Información técnica adicional.
     * @return Esta instancia actualizada.
     */
    public SecurityEventNotificationRequest withTechInfo(@Nullable String techInfo) {
        setTechInfo(techInfo);
        return this;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidType(type)
                && isValidTimestamp(timestamp)
                && isValidTechInfo(techInfo);
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SecurityEventNotificationRequest that = (SecurityEventNotificationRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(type, that.type)
                && Objects.equals(timestamp, that.timestamp)
                && Objects.equals(techInfo, that.techInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, type, timestamp, techInfo);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("type", type)
                .add("timestamp", timestamp)
                .add("techInfo", techInfo)
                .add("isValid", validate())
                .toString();
    }
}
