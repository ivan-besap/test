package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Request;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Validation.Validator;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Validation.ValidatorBuilder;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Validation.StringMaxLengthValidationRule;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud para la notificación de eventos de seguridad en OCPP.
 *
 * <p>Esta solicitud es enviada por la estación de carga al sistema central para notificar
 * un evento de seguridad, proporcionando detalles sobre el tipo de evento y el momento en que ocurrió.</p>
 *
 * <p><b>Ejemplo de uso:</b></p>
 * <pre>
 *     {@code
 *     SecurityEventNotificationRequest request = new SecurityEventNotificationRequest("Breach", ZonedDateTime.now());
 *     request.setTechInfo("Detalles técnicos del evento de seguridad.");
 *     System.out.println(request);
 *     }
 * </pre>
 */
public class SecurityEventNotificationRequest extends RequestWithId {

    // Validadores para los campos de tipo y techInfo
    private static final transient Validator typeValidator =
            new ValidatorBuilder()
                    .addRule(new StringMaxLengthValidationRule(50))
                    .setRequired(true)
                    .build();

    private static final transient Validator techInfoValidator =
            new ValidatorBuilder()
                    .addRule(new StringMaxLengthValidationRule(255))
                    .build();

    // Campos requeridos y opcionales de la solicitud
    private String type;
    private ZonedDateTime timestamp;
    private String techInfo;

    /**
     * Constructor por defecto, necesario para la serialización.
     */
    private SecurityEventNotificationRequest() {
    }

    /**
     * Constructor que maneja los campos obligatorios de la solicitud.
     *
     * @param type      Tipo de evento de seguridad.
     * @param timestamp Fecha y hora en que ocurrió el evento.
     */
    public SecurityEventNotificationRequest(String type, ZonedDateTime timestamp) {
        setType(type);
        setTimestamp(timestamp);
    }

    /**
     * Obtiene el tipo de evento de seguridad.
     *
     * @return String tipo de evento.
     */
    public String getType() {
        return type;
    }

    /**
     * Establece el tipo de evento de seguridad.
     * Campo obligatorio con una longitud máxima de 50 caracteres.
     *
     * @param type Tipo de evento de seguridad.
     */
    public void setType(String type) {
        typeValidator.validate(type);
        this.type = type;
    }

    /**
     * Obtiene la fecha y hora en que ocurrió el evento de seguridad.
     *
     * @return Fecha y hora del evento.
     */
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Establece la fecha y hora en que ocurrió el evento de seguridad.
     * Este campo es obligatorio.
     *
     * @param timestamp Fecha y hora del evento.
     */
    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Obtiene información técnica adicional sobre el evento de seguridad.
     *
     * @return Información técnica adicional.
     */
    public String getTechInfo() {
        return techInfo;
    }

    /**
     * Establece información técnica adicional sobre el evento de seguridad.
     * Campo opcional con una longitud máxima de 255 caracteres.
     *
     * @param techInfo Información técnica adicional.
     */
    public void setTechInfo(String techInfo) {
        techInfoValidator.validate(techInfo);
        this.techInfo = techInfo;
    }

    /**
     * Indica si esta solicitud está relacionada con una transacción.
     *
     * @return {@code false} ya que no está relacionada con una transacción.
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
     * Valida los campos obligatorios de la solicitud.
     *
     * @return {@code true} si los campos obligatorios son válidos, {@code false} en caso contrario.
     */
    @Override
    public boolean validate() {
        return typeValidator.safeValidate(type)
                && timestamp != null
                && techInfoValidator.safeValidate(techInfo);
    }

    /**
     * Compara si dos solicitudes de notificación de eventos de seguridad son equivalentes.
     *
     * @param o Objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, {@code false} de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecurityEventNotificationRequest that = (SecurityEventNotificationRequest) o;
        return Objects.equals(type, that.type)
                && Objects.equals(timestamp, that.timestamp)
                && Objects.equals(techInfo, that.techInfo);
    }

    /**
     * Genera un código hash basado en los campos de la solicitud.
     *
     * @return Código hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(type, timestamp, techInfo);
    }

    /**
     * Devuelve una representación en cadena de la solicitud de notificación de eventos de seguridad.
     *
     * @return Una cadena que representa la solicitud.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("type", type)
                .add("timestamp", timestamp)
                .add("techInfo", techInfo)
                .add("isValid", validate()).toString();
    }
}
