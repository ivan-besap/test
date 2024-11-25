package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation.Enums.GenericStatusEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation.Utils.CompositeSchedule;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta al mensaje GetCompositeScheduleRequest.
 *
 * <p>Indica si la estación de carga pudo procesar la solicitud y proporciona información adicional,
 * incluyendo el horario compuesto si corresponde.
 */
public class GetCompositeScheduleResponse extends Confirmation {

    @Nullable
    private CustomData customData;

    private GenericStatusEnum status;

    @Nullable
    private StatusInfo statusInfo;

    @Nullable
    private CompositeSchedule schedule;

    /**
     * Constructor de la clase GetCompositeScheduleResponse.
     *
     * @param status Estado indicando si la estación de carga pudo procesar la solicitud.
     */
    public GetCompositeScheduleResponse(GenericStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene los datos personalizados asociados a la respuesta.
     *
     * @return Objeto de tipo {@link CustomData}, o {@code null} si no se estableció.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados para la respuesta.
     *
     * @param customData Objeto de tipo {@link CustomData} que contiene datos personalizados.
     * @throws PropertyConstraintException Si los datos personalizados no son válidos.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData no es válido");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Objeto de tipo {@link CustomData} a validar.
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Establece los datos personalizados y devuelve la instancia actual.
     *
     * @param customData Objeto de tipo {@link CustomData} que contiene datos personalizados.
     * @return La instancia actual de {@link GetCompositeScheduleResponse}.
     */
    public GetCompositeScheduleResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado de la respuesta.
     *
     * @return Objeto de tipo {@link GenericStatusEnum} que indica el estado.
     */
    public GenericStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado de la respuesta.
     *
     * @param status Objeto de tipo {@link GenericStatusEnum} que indica el estado.
     * @throws PropertyConstraintException Si el estado es {@code null}.
     */
    public void setStatus(GenericStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "status no es válido");
        }
        this.status = status;
    }

    /**
     * Verifica si el estado es válido.
     *
     * @param status Objeto de tipo {@link GenericStatusEnum} a validar.
     * @return {@code true} si el estado es válido, {@code false} en caso contrario.
     */
    private boolean isValidStatus(GenericStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene información adicional sobre el estado de la respuesta.
     *
     * @return Objeto de tipo {@link StatusInfo}, o {@code null} si no se estableció.
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece información adicional sobre el estado de la respuesta.
     *
     * @param statusInfo Objeto de tipo {@link StatusInfo} que contiene información adicional.
     * @throws PropertyConstraintException Si la información de estado no es válida.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "statusInfo no es válido");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Verifica si la información de estado es válida.
     *
     * @param statusInfo Objeto de tipo {@link StatusInfo} a validar.
     * @return {@code true} si la información es válida, {@code false} en caso contrario.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Establece la información de estado y devuelve la instancia actual.
     *
     * @param statusInfo Objeto de tipo {@link StatusInfo} que contiene información adicional.
     * @return La instancia actual de {@link GetCompositeScheduleResponse}.
     */
    public GetCompositeScheduleResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
        setStatusInfo(statusInfo);
        return this;
    }

    /**
     * Obtiene el horario compuesto asociado a la respuesta.
     *
     * @return Objeto de tipo {@link CompositeSchedule}, o {@code null} si no se estableció.
     */
    @Nullable
    public CompositeSchedule getSchedule() {
        return schedule;
    }

    /**
     * Establece el horario compuesto para la respuesta.
     *
     * @param schedule Objeto de tipo {@link CompositeSchedule} que contiene el horario compuesto.
     * @throws PropertyConstraintException Si el horario no es válido.
     */
    public void setSchedule(@Nullable CompositeSchedule schedule) {
        if (!isValidSchedule(schedule)) {
            throw new PropertyConstraintException(schedule, "schedule no es válido");
        }
        this.schedule = schedule;
    }

    /**
     * Verifica si el horario compuesto es válido.
     *
     * @param schedule Objeto de tipo {@link CompositeSchedule} a validar.
     * @return {@code true} si el horario es válido, {@code false} en caso contrario.
     */
    private boolean isValidSchedule(@Nullable CompositeSchedule schedule) {
        return schedule == null || schedule.validate();
    }

    /**
     * Establece el horario compuesto y devuelve la instancia actual.
     *
     * @param schedule Objeto de tipo {@link CompositeSchedule} que contiene el horario compuesto.
     * @return La instancia actual de {@link GetCompositeScheduleResponse}.
     */
    public GetCompositeScheduleResponse withSchedule(@Nullable CompositeSchedule schedule) {
        setSchedule(schedule);
        return this;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidStatus(status)
                && isValidStatusInfo(statusInfo)
                && isValidSchedule(schedule);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetCompositeScheduleResponse that = (GetCompositeScheduleResponse) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(status, that.status)
                && Objects.equals(statusInfo, that.statusInfo)
                && Objects.equals(schedule, that.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, status, statusInfo, schedule);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("status", status)
                .add("statusInfo", statusInfo)
                .add("schedule", schedule)
                .add("isValid", validate())
                .toString();
    }
}
