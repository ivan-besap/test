package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Utils.NetworkConnectionProfile;
import jakarta.annotation.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud para configurar un perfil de red en la estación de carga.
 *
 * <p>Permite definir los parámetros funcionales y técnicos de un enlace de comunicación.
 */
public class SetNetworkProfileRequest extends RequestWithId {
    /** Datos personalizados para extender la funcionalidad de la solicitud. */
    @Nullable
    private CustomData customData;

    /** Número de ranura en la que se debe almacenar la configuración de red. */
    private Integer configurationSlot;

    /**
     * Perfil de conexión de red.
     *
     * <p>Define los parámetros funcionales y técnicos de un enlace de comunicación.
     */
    private NetworkConnectionProfile connectionData;

    /**
     * Constructor de la clase SetNetworkProfileRequest.
     *
     * @param configurationSlot Número de ranura en la que se debe almacenar la configuración de red.
     * @param connectionData Datos que definen los parámetros funcionales y técnicos del enlace de
     *     comunicación.
     */
    public SetNetworkProfileRequest(
            Integer configurationSlot, NetworkConnectionProfile connectionData) {
        setConfigurationSlot(configurationSlot);
        setConnectionData(connectionData);
    }

    /**
     * Obtiene los datos personalizados asociados a esta solicitud.
     *
     * @return Datos personalizados, o null si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados para esta solicitud.
     *
     * @param customData Datos personalizados que extienden la funcionalidad.
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
     * @param customData Los datos personalizados a validar.
     * @return true si son válidos; false en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Establece y devuelve esta solicitud con los datos personalizados especificados.
     *
     * @param customData Datos personalizados para agregar.
     * @return Esta instancia de SetNetworkProfileRequest.
     */
    public SetNetworkProfileRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene la ranura de configuración en la que se debe almacenar el perfil de red.
     *
     * @return Número de la ranura de configuración.
     */
    public Integer getConfigurationSlot() {
        return configurationSlot;
    }

    /**
     * Establece la ranura de configuración en la que se debe almacenar el perfil de red.
     *
     * @param configurationSlot Número de ranura para la configuración.
     */
    public void setConfigurationSlot(Integer configurationSlot) {
        if (!isValidConfigurationSlot(configurationSlot)) {
            throw new PropertyConstraintException(
                    configurationSlot, "La ranura de configuración no es válida");
        }
        this.configurationSlot = configurationSlot;
    }

    /**
     * Verifica si el número de ranura de configuración es válido.
     *
     * @param configurationSlot Número de ranura a validar.
     * @return true si es válido; false en caso contrario.
     */
    private boolean isValidConfigurationSlot(Integer configurationSlot) {
        return configurationSlot != null;
    }

    /**
     * Obtiene los datos del perfil de conexión de red.
     *
     * @return Perfil de conexión de red.
     */
    public NetworkConnectionProfile getConnectionData() {
        return connectionData;
    }

    /**
     * Establece los datos del perfil de conexión de red.
     *
     * @param connectionData Perfil de conexión con los parámetros funcionales y técnicos.
     */
    public void setConnectionData(NetworkConnectionProfile connectionData) {
        if (!isValidConnectionData(connectionData)) {
            throw new PropertyConstraintException(
                    connectionData, "Los datos de conexión no son válidos");
        }
        this.connectionData = connectionData;
    }

    /**
     * Verifica si los datos del perfil de conexión son válidos.
     *
     * @param connectionData Datos del perfil de conexión a validar.
     * @return true si son válidos; false en caso contrario.
     */
    private boolean isValidConnectionData(NetworkConnectionProfile connectionData) {
        return connectionData != null && connectionData.validate();
    }

    /**
     * Valida la solicitud actual verificando todos los campos.
     *
     * @return true si la solicitud es válida; false en caso contrario.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidConfigurationSlot(configurationSlot)
                && isValidConnectionData(connectionData);
    }

    /**
     * Indica si esta solicitud está relacionada con una transacción.
     *
     * @return false ya que no está relacionada con transacciones.
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
        SetNetworkProfileRequest that = (SetNetworkProfileRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(configurationSlot, that.configurationSlot)
                && Objects.equals(connectionData, that.connectionData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, configurationSlot, connectionData);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("configurationSlot", configurationSlot)
                .add("connectionData", connectionData)
                .add("isValid", validate())
                .toString();
    }
}
