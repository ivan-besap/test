package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums.BootReasonEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Utils.ChargingStation;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud de notificación de arranque en el protocolo OCPP 2.0.1.
 *
 * <p>Este mensaje es enviado por la estación de carga al CSMS (Sistema Central de Gestión de Carga)
 * para notificar el estado actual y la información básica del sistema de carga.
 */
public class BootNotificationRequest extends RequestWithId {

    /** Datos personalizados asociados a la solicitud. */
    @Nullable
    private CustomData customData;

    /**
     * Estación de carga.
     *
     * <p>El sistema físico donde se puede cargar un vehículo eléctrico (EV).
     */
    private ChargingStation chargingStation;

    /**
     * Motivo del envío del mensaje al CSMS.
     *
     * <p>Especifica la razón por la que se envía esta notificación.
     */
    private BootReasonEnum reason;

    /**
     * Constructor de la clase BootNotificationRequest.
     *
     * @param chargingStation El sistema físico donde se puede cargar un vehículo eléctrico.
     * @param reason Motivo del envío del mensaje al CSMS.
     */
    public BootNotificationRequest(ChargingStation chargingStation, BootReasonEnum reason) {
        setChargingStation(chargingStation);
        setReason(reason);
    }

    /** @return Datos personalizados asociados a la solicitud. */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece datos personalizados asociados a la solicitud.
     *
     * @param customData Datos personalizados.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos a validar.
     * @return {@code true} si son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados asociados a la solicitud.
     *
     * @param customData Datos personalizados.
     * @return La instancia actualizada de BootNotificationRequest.
     */
    public BootNotificationRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /** @return El sistema físico donde se puede cargar un vehículo eléctrico. */
    public ChargingStation getChargingStation() {
        return chargingStation;
    }

    /**
     * Establece el sistema físico donde se puede cargar un vehículo eléctrico.
     *
     * @param chargingStation Estación de carga.
     */
    public void setChargingStation(ChargingStation chargingStation) {
        if (!isValidChargingStation(chargingStation)) {
            throw new PropertyConstraintException(chargingStation, "La estación de carga no es válida");
        }
        this.chargingStation = chargingStation;
    }

    /**
     * Verifica si la estación de carga es válida.
     *
     * @param chargingStation Estación de carga a validar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidChargingStation(ChargingStation chargingStation) {
        return chargingStation != null && chargingStation.validate();
    }

    /** @return Motivo del envío del mensaje al CSMS. */
    public BootReasonEnum getReason() {
        return reason;
    }

    /**
     * Establece el motivo del envío del mensaje al CSMS.
     *
     * @param reason Motivo del mensaje.
     */
    public void setReason(BootReasonEnum reason) {
        if (!isValidReason(reason)) {
            throw new PropertyConstraintException(reason, "El motivo no es válido");
        }
        this.reason = reason;
    }

    /**
     * Verifica si el motivo del mensaje es válido.
     *
     * @param reason Motivo a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidReason(BootReasonEnum reason) {
        return reason != null;
    }

    /**
     * Valida si los datos de la solicitud son correctos.
     *
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidChargingStation(chargingStation)
                && isValidReason(reason);
    }

    /**
     * Indica si esta solicitud está relacionada con una transacción.
     *
     * @return {@code false}, ya que esta solicitud no está relacionada con transacciones.
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BootNotificationRequest that = (BootNotificationRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(chargingStation, that.chargingStation)
                && Objects.equals(reason, that.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, chargingStation, reason);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("chargingStation", chargingStation)
                .add("reason", reason)
                .add("isValid", validate())
                .toString();
    }
}
