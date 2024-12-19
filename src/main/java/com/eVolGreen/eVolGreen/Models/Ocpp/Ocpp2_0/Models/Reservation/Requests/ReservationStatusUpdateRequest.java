package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Requests.Enums.ReservationUpdateStatusEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud para actualizar el estado de una reserva en la estación de carga.
 *
 * <p>Esta solicitud informa a la estación de carga sobre un cambio en el estado de una reserva específica.
 * <p>Versión: OCPP 2.0.1 FINAL
 */
public class ReservationStatusUpdateRequest extends RequestWithId {

    /** Datos personalizados opcionales relacionados con la solicitud. */
    @Nullable
    private CustomData customData;

    /** Identificador único de la reserva que se está actualizando. */
    private Integer reservationId;

    /** Estado actualizado de la reserva. */
    private ReservationUpdateStatusEnum reservationUpdateStatus;

    /**
     * Constructor para la clase ReservationStatusUpdateRequest.
     *
     * @param reservationId Identificador único de la reserva que se está actualizando.
     * @param reservationUpdateStatus Estado actualizado de la reserva.
     */
    public ReservationStatusUpdateRequest(
            Integer reservationId, ReservationUpdateStatusEnum reservationUpdateStatus) {
        setReservationId(reservationId);
        setReservationUpdateStatus(reservationUpdateStatus);
    }

    /**
     * Obtiene los datos personalizados relacionados con la solicitud.
     *
     * @return Los datos personalizados o null si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados relacionados con la solicitud.
     *
     * @param customData Los datos personalizados que se desean establecer.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados proporcionados son válidos.
     *
     * @param customData Los datos personalizados a validar.
     * @return true si son válidos, de lo contrario false.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Asigna datos personalizados y devuelve la instancia actual.
     *
     * @param customData Los datos personalizados que se desean establecer.
     * @return La instancia actual de ReservationStatusUpdateRequest.
     */
    public ReservationStatusUpdateRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el identificador único de la reserva que se está actualizando.
     *
     * @return El identificador de la reserva.
     */
    public Integer getReservationId() {
        return reservationId;
    }

    /**
     * Establece el identificador único de la reserva que se está actualizando.
     *
     * @param reservationId El identificador de la reserva.
     */
    public void setReservationId(Integer reservationId) {
        if (!isValidReservationId(reservationId)) {
            throw new PropertyConstraintException(reservationId, "El ID de la reserva no es válido");
        }
        this.reservationId = reservationId;
    }

    /**
     * Valida si el identificador de la reserva proporcionado es válido.
     *
     * @param reservationId El identificador de la reserva a validar.
     * @return true si es válido, de lo contrario false.
     */
    private boolean isValidReservationId(Integer reservationId) {
        return reservationId != null;
    }

    /**
     * Obtiene el estado actualizado de la reserva.
     *
     * @return El estado actualizado de la reserva.
     */
    public ReservationUpdateStatusEnum getReservationUpdateStatus() {
        return reservationUpdateStatus;
    }

    /**
     * Establece el estado actualizado de la reserva.
     *
     * @param reservationUpdateStatus El estado actualizado de la reserva.
     */
    public void setReservationUpdateStatus(ReservationUpdateStatusEnum reservationUpdateStatus) {
        if (!isValidReservationUpdateStatus(reservationUpdateStatus)) {
            throw new PropertyConstraintException(
                    reservationUpdateStatus, "El estado de la reserva no es válido");
        }
        this.reservationUpdateStatus = reservationUpdateStatus;
    }

    /**
     * Valida si el estado actualizado de la reserva proporcionado es válido.
     *
     * @param reservationUpdateStatus El estado de la reserva a validar.
     * @return true si es válido, de lo contrario false.
     */
    private boolean isValidReservationUpdateStatus(
            ReservationUpdateStatusEnum reservationUpdateStatus) {
        return reservationUpdateStatus != null;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidReservationId(reservationId)
                && isValidReservationUpdateStatus(reservationUpdateStatus);
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
        ReservationStatusUpdateRequest that = (ReservationStatusUpdateRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(reservationId, that.reservationId)
                && Objects.equals(reservationUpdateStatus, that.reservationUpdateStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, reservationId, reservationUpdateStatus);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("reservationId", reservationId)
                .add("reservationUpdateStatus", reservationUpdateStatus)
                .add("isValid", validate())
                .toString();
    }
}
