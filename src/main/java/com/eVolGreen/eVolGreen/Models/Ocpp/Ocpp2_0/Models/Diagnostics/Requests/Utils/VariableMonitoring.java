package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Enums.MonitorEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Configuración de monitoreo para una variable.
 *
 * <p>Esta clase define los atributos asociados a un monitor que supervisa variables específicas
 * en un componente, incluyendo el tipo de monitor, el umbral o valor, y la severidad asociada.
 */
public class VariableMonitoring {

    /** Datos personalizados adicionales. */
    @Nullable
    private CustomData customData;

    /** Identificador único del monitor. */
    private Integer id;

    /** Indica si el monitor solo estará activo durante una transacción. */
    private Boolean transaction;

    /**
     * Valor asociado al monitoreo.
     * <p>Para monitores periódicos o alineados con un reloj, este valor representa el intervalo en segundos.
     */
    private Double value;

    /** Tipo de monitor, como umbral, delta o periódico. */
    private MonitorEnum type;

    /**
     * Severidad asignada al evento activado por este monitor.
     * <p>La severidad se clasifica en un rango de 0 a 9, donde 0 es la mayor severidad (peligro) y 9 es la menor.
     */
    private Integer severity;

    /**
     * Constructor de la clase VariableMonitoring.
     *
     * @param id Identificador único del monitor.
     * @param transaction Indica si el monitor solo estará activo durante una transacción.
     * @param value Valor asociado al monitoreo.
     * @param type Tipo de monitor, como umbral, delta o periódico.
     * @param severity Severidad asignada al evento activado por este monitor.
     */
    public VariableMonitoring(
            Integer id, Boolean transaction, Double value, MonitorEnum type, Integer severity) {
        setId(id);
        setTransaction(transaction);
        setValue(value);
        setType(type);
        setSeverity(severity);
    }

    /**
     * Obtiene los datos personalizados adicionales.
     *
     * @return Datos personalizados adicionales.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados adicionales.
     *
     * @param customData Datos personalizados adicionales.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados son inválidos.");
        }
        this.customData = customData;
    }

    /**
     * Valida que los datos personalizados sean válidos.
     *
     * @param customData Datos personalizados.
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados adicionales.
     *
     * @param customData Datos personalizados adicionales.
     * @return La instancia actual.
     */
    public VariableMonitoring withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el identificador único del monitor.
     *
     * @return Identificador único del monitor.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único del monitor.
     *
     * @param id Identificador único del monitor.
     */
    public void setId(Integer id) {
        if (!isValidId(id)) {
            throw new PropertyConstraintException(id, "El identificador es inválido.");
        }
        this.id = id;
    }

    /**
     * Valida que el identificador sea válido.
     *
     * @param id Identificador único.
     * @return {@code true} si el identificador es válido, {@code false} en caso contrario.
     */
    private boolean isValidId(Integer id) {
        return id != null;
    }

    /**
     * Obtiene si el monitor solo estará activo durante una transacción.
     *
     * @return {@code true} si el monitor solo estará activo durante una transacción, de lo contrario {@code false}.
     */
    public Boolean getTransaction() {
        return transaction;
    }

    /**
     * Establece si el monitor solo estará activo durante una transacción.
     *
     * @param transaction Indica si el monitor solo estará activo durante una transacción.
     */
    public void setTransaction(Boolean transaction) {
        if (!isValidTransaction(transaction)) {
            throw new PropertyConstraintException(transaction, "El valor de la transacción es inválido.");
        }
        this.transaction = transaction;
    }

    /**
     * Valida si el valor de transacción es válido.
     *
     * @param transaction Valor de transacción.
     * @return {@code true} si el valor es válido, {@code false} en caso contrario.
     */
    private boolean isValidTransaction(Boolean transaction) {
        return transaction != null;
    }


    /**
     * Obtiene el valor asociado al monitoreo.
     *
     * @return Valor asociado al monitoreo.
     */
    public Double getValue() {
        return value;
    }

    /**
     * Establece el valor asociado al monitoreo.
     *
     * @param value Valor asociado al monitoreo.
     */
    public void setValue(Double value) {
        if (!isValidValue(value)) {
            throw new PropertyConstraintException(value, "El valor asociado al monitoreo es inválido.");
        }
        this.value = value;
    }

    /**
     * Valida que el valor sea válido.
     *
     * @param value Valor asociado.
     * @return {@code true} si el valor es válido, {@code false} en caso contrario.
     */
    private boolean isValidValue(Double value) {
        return value != null;
    }

    /**
     * Obtiene el tipo de monitor.
     *
     * @return Tipo de monitor.
     */
    public MonitorEnum getType() {
        return type;
    }

    /**
     * Establece el tipo de monitor.
     *
     * @param type Tipo de monitor.
     */
    public void setType(MonitorEnum type) {
        if (!isValidType(type)) {
            throw new PropertyConstraintException(type, "El tipo de monitor es inválido.");
        }
        this.type = type;
    }
    /**
     * Valida que el tipo de monitor sea válido.
     *
     * @param type Tipo de monitor.
     * @return {@code true} si el tipo es válido, {@code false} en caso contrario.
     */
    private boolean isValidType(MonitorEnum type) {
        return type != null;
    }

    /**
     * Obtiene la severidad asociada al evento activado por este monitor.
     *
     * @return Severidad asociada al evento.
     */
    public Integer getSeverity() {
        return severity;
    }

    /**
     * Establece la severidad asociada al evento activado por este monitor.
     *
     * @param severity Severidad asociada al evento.
     */
    public void setSeverity(Integer severity) {
        if (!isValidSeverity(severity)) {
            throw new PropertyConstraintException(severity, "La severidad es inválida.");
        }
        this.severity = severity;
    }

    /**
     * Valida que la severidad sea válida.
     *
     * @param severity Nivel de severidad.
     * @return {@code true} si la severidad es válida, {@code false} en caso contrario.
     */
    private boolean isValidSeverity(Integer severity) {
        return severity != null;
    }

    /**
     * Valida todos los atributos de esta clase.
     *
     * @return {@code true} si todos los atributos son válidos, {@code false} en caso contrario.
     */
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidId(id)
                && isValidTransaction(transaction)
                && isValidValue(value)
                && isValidType(type)
                && isValidSeverity(severity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VariableMonitoring that = (VariableMonitoring) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(id, that.id)
                && Objects.equals(transaction, that.transaction)
                && Objects.equals(value, that.value)
                && Objects.equals(type, that.type)
                && Objects.equals(severity, that.severity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, id, transaction, value, type, severity);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("id", id)
                .add("transaction", transaction)
                .add("value", value)
                .add("type", type)
                .add("severity", severity)
                .add("isValid", validate())
                .toString();
    }
}
