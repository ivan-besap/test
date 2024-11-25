package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud de Actualización de Costos
 *
 * <p>Representa una solicitud para informar el costo total actualizado de una transacción,
 * incluido el impuesto.</p>
 *
 * <p>OCPP 2.0.1 FINAL</p>
 */
public class CostUpdatedRequest extends RequestWithId {
    /** Datos personalizados adicionales. */
    @Nullable
    private CustomData customData;

    /**
     * Costo total actual, basado en la información conocida por el CSMS, de la transacción incluyendo impuestos.
     * Expresado en la moneda configurada con la Variable de Configuración: [Currency].
     */
    private Double totalCost;

    /** ID de la transacción para la cual se solicita el costo actualizado. */
    private String transactionId;

    /**
     * Constructor de la clase CostUpdatedRequest.
     *
     * @param totalCost Costo total actual de la transacción incluyendo impuestos.
     * @param transactionId ID de la transacción correspondiente.
     */
    public CostUpdatedRequest(Double totalCost, String transactionId) {
        setTotalCost(totalCost);
        setTransactionId(transactionId);
    }

    /**
     * Obtiene los datos personalizados.
     *
     * @return Datos personalizados.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados.
     *
     * @param customData Datos personalizados.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Datos personalizados inválidos.");
        }
        this.customData = customData;
    }

    /**
     * Valida los datos personalizados.
     *
     * @param customData Datos personalizados.
     * @return {@code true} si los datos son válidos; de lo contrario, {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia.
     */
    public CostUpdatedRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el costo total actual.
     *
     * @return Costo total actual.
     */
    public Double getTotalCost() {
        return totalCost;
    }

    /**
     * Establece el costo total actual.
     *
     * @param totalCost Costo total actual.
     */
    public void setTotalCost(Double totalCost) {
        if (!isValidTotalCost(totalCost)) {
            throw new PropertyConstraintException(totalCost, "El costo total es inválido.");
        }
        this.totalCost = totalCost;
    }

    /**
     * Verifica si el costo total es válido.
     *
     * @param totalCost Costo total a validar.
     * @return {@code true} si es válido; de lo contrario, {@code false}.
     */
    private boolean isValidTotalCost(Double totalCost) {
        return totalCost != null;
    }

    /**
     * Obtiene el ID de la transacción.
     *
     * @return ID de la transacción.
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Establece el ID de la transacción.
     *
     * @param transactionId ID de la transacción.
     */
    public void setTransactionId(String transactionId) {
        if (!isValidTransactionId(transactionId)) {
            throw new PropertyConstraintException(transactionId, "El ID de la transacción es inválido.");
        }
        this.transactionId = transactionId;
    }

    /**
     * Verifica si el ID de la transacción es válido.
     *
     * @param transactionId ID de la transacción a validar.
     * @return {@code true} si es válido; de lo contrario, {@code false}.
     */
    private boolean isValidTransactionId(String transactionId) {
        return transactionId != null && transactionId.length() <= 36;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidTotalCost(totalCost)
                && isValidTransactionId(transactionId);
    }

    @Override
    public boolean transactionRelated() {
        return true; // Esta solicitud está relacionada con una transacción.
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CostUpdatedRequest that = (CostUpdatedRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(totalCost, that.totalCost)
                && Objects.equals(transactionId, that.transactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, totalCost, transactionId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("totalCost", totalCost)
                .add("transactionId", transactionId)
                .add("isValid", validate())
                .toString();
    }
}
