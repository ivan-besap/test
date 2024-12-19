package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums.APNAuthenticationEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * APN (Access Point Name)
 *
 * <p>Representa la configuración necesaria para establecer una conexión de datos a través de una
 * red móvil. Incluye información como el nombre del punto de acceso, usuario, contraseña y otras
 * configuraciones relacionadas con la autenticación y la red preferida.
 */
public class APN {
    /** Datos personalizados relacionados con la configuración de APN. */
    @Nullable
    private CustomData customData;

    /** Nombre del Punto de Acceso (APN) representado como una URL. */
    private String apn;

    /** Nombre de usuario para autenticación del APN (opcional). */
    @Nullable
    private String apnUserName;

    /** Contraseña para autenticación del APN (opcional). */
    @Nullable
    private String apnPassword;

    /** Código PIN de la tarjeta SIM (opcional). */
    @Nullable
    private Integer simPin;

    /** Red preferida escrita como MCC y MNC concatenados (opcional). */
    @Nullable
    private String preferredNetwork;

    /** Indicador para usar únicamente la red preferida (por defecto: false). */
    @Nullable
    private Boolean useOnlyPreferredNetwork;

    /** Método de autenticación utilizado para el APN. */
    private APNAuthenticationEnum apnAuthentication;

    /**
     * Constructor para inicializar la configuración básica del APN.
     *
     * @param apn Nombre del Punto de Acceso (APN) representado como una URL.
     * @param apnAuthentication Método de autenticación utilizado para el APN.
     */
    public APN(String apn, APNAuthenticationEnum apnAuthentication) {
        setApn(apn);
        setApnAuthentication(apnAuthentication);
    }

    /**
     * Obtiene los datos personalizados relacionados con la configuración del APN.
     *
     * @return Datos personalizados o null si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados relacionados con la configuración del APN.
     *
     * @param customData Datos personalizados.
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
     * @param customData Datos personalizados.
     * @return {@code true} si son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Crea una nueva instancia de APN con los datos personalizados proporcionados.
     *
     * @param customData Datos personalizados.
     * @return Nueva instancia de APN.
     */
    public APN withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el nombre del Punto de Acceso (APN) como URL.
     *
     * @return Nombre del APN.
     */
    public String getApn() {
        return apn;
    }

    /**
     * Establece el nombre del Punto de Acceso (APN) como URL.
     *
     * @param apn Nombre del APN.
     */
    public void setApn(String apn) {
        if (!isValidApn(apn)) {
            throw new PropertyConstraintException(apn, "El nombre del APN no es válido.");
        }
        this.apn = apn;
    }

    /**
     * Verifica si el nombre del Punto de Acceso (APN) es válido.
     *
     * @param apn Nombre del APN.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidApn(String apn) {
        return apn != null && apn.length() <= 512;
    }

    /**
     * Obtiene el nombre de usuario para la autenticación del APN.
     *
     * @return Nombre de usuario o null si no está definido.
     */
    @Nullable
    public String getApnUserName() {
        return apnUserName;
    }

    /**
     * Establece el nombre de usuario para la autenticación del APN.
     *
     * @param apnUserName Nombre de usuario.
     */
    public void setApnUserName(@Nullable String apnUserName) {
        if (!isValidApnUserName(apnUserName)) {
            throw new PropertyConstraintException(apnUserName, "El nombre de usuario no es válido.");
        }
        this.apnUserName = apnUserName;
    }

    /**
     * Verifica si el nombre de usuario para la autenticación del APN es válido.
     *
     * @param apnUserName Nombre de usuario.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidApnUserName(@Nullable String apnUserName) {
        return apnUserName == null || apnUserName.length() <= 20;
    }

    /**
     * Crea una nueva instancia de APN con el nombre de usuario proporcionado.
     *
     * @param apnUserName Nombre de usuario.
     * @return Nueva instancia de APN.
     */
    public APN withApnUserName(@Nullable String apnUserName) {
        setApnUserName(apnUserName);
        return this;
    }

    /**
     * Obtiene la contraseña para la autenticación del APN.
     *
     * @return Contraseña o null si no está definida.
     */
    @Nullable
    public String getApnPassword() {
        return apnPassword;
    }

    /**
     * Establece la contraseña para la autenticación del APN.
     *
     * @param apnPassword Contraseña.
     */
    public void setApnPassword(@Nullable String apnPassword) {
        if (!isValidApnPassword(apnPassword)) {
            throw new PropertyConstraintException(apnPassword, "La contraseña no es válida.");
        }
        this.apnPassword = apnPassword;
    }

    /**
     * Verifica si la contraseña para la autenticación del APN es válida.
     *
     * @param apnPassword Contraseña.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidApnPassword(@Nullable String apnPassword) {
        return apnPassword == null || apnPassword.length() <= 20;
    }

    /**
     * Crea una nueva instancia de APN con la contraseña proporcionada.
     *
     * @param apnPassword Contraseña.
     * @return Nueva instancia de APN.
     */
    public APN withApnPassword(@Nullable String apnPassword) {
        setApnPassword(apnPassword);
        return this;
    }


    /**
     * Obtiene el código PIN de la tarjeta SIM.
     *
     * @return Código PIN o null si no está definido.
     */
    @Nullable
    public Integer getSimPin() {
        return simPin;
    }

    /**
     * Establece el código PIN de la tarjeta SIM.
     *
     * @param simPin Código PIN.
     */
    public void setSimPin(@Nullable Integer simPin) {
        this.simPin = simPin;
    }

    /**
     * Crea una nueva instancia de APN con el código PIN proporcionado.
     *
     * @param simPin Código PIN.
     * @return Nueva instancia de APN.
     */
    public APN withSimPin(@Nullable Integer simPin) {
        setSimPin(simPin);
        return this;
    }

    /**
     * Obtiene la red preferida escrita como MCC y MNC concatenados.
     *
     * @return Red preferida o null si no está definida.
     */
    @Nullable
    public String getPreferredNetwork() {
        return preferredNetwork;
    }

    /**
     * Establece la red preferida escrita como MCC y MNC concatenados.
     *
     * @param preferredNetwork Red preferida.
     */
    public void setPreferredNetwork(@Nullable String preferredNetwork) {
        if (!isValidPreferredNetwork(preferredNetwork)) {
            throw new PropertyConstraintException(preferredNetwork, "La red preferida no es válida.");
        }
        this.preferredNetwork = preferredNetwork;
    }

    /**
     * Verifica si la red preferida es válida.
     *
     * @param preferredNetwork Red preferida.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidPreferredNetwork(@Nullable String preferredNetwork) {
        return preferredNetwork == null || preferredNetwork.length() <= 6;
    }

    /**
     * Crea una nueva instancia de APN con la red preferida proporcionada.
     *
     * @param preferredNetwork Red preferida.
     * @return Nueva instancia de APN.
     */
    public APN withPreferredNetwork(@Nullable String preferredNetwork) {
        setPreferredNetwork(preferredNetwork);
        return this;
    }

    /**
     * Obtiene el indicador para usar únicamente la red preferida.
     *
     * @return Indicador (true o false).
     */
    public Boolean getUseOnlyPreferredNetwork() {
        return useOnlyPreferredNetwork != null ? useOnlyPreferredNetwork : false;
    }

    /**
     * Establece el indicador para usar únicamente la red preferida.
     *
     * @param useOnlyPreferredNetwork Indicador.
     */
    public void setUseOnlyPreferredNetwork(@Nullable Boolean useOnlyPreferredNetwork) {
        this.useOnlyPreferredNetwork = useOnlyPreferredNetwork;
    }

    /**
     * Crea una nueva instancia de APN con el indicador de usar únicamente la red preferida.
     *
     * @param useOnlyPreferredNetwork Indicador.
     * @return Nueva instancia de APN.
     */
    public APN withUseOnlyPreferredNetwork(@Nullable Boolean useOnlyPreferredNetwork) {
        setUseOnlyPreferredNetwork(useOnlyPreferredNetwork);
        return this;
    }

    /**
     * Obtiene el método de autenticación utilizado para el APN.
     *
     * @return Método de autenticación.
     */
    public APNAuthenticationEnum getApnAuthentication() {
        return apnAuthentication;
    }

    /**
     * Establece el método de autenticación utilizado para el APN.
     *
     * @param apnAuthentication Método de autenticación.
     */
    public void setApnAuthentication(APNAuthenticationEnum apnAuthentication) {
        if (!isValidApnAuthentication(apnAuthentication)) {
            throw new PropertyConstraintException(
                    apnAuthentication, "El método de autenticación no es válido.");
        }
        this.apnAuthentication = apnAuthentication;
    }

    /**
     * Verifica si el método de autenticación utilizado para el APN es válido.
     *
     * @param apnAuthentication Método de autenticación.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidApnAuthentication(APNAuthenticationEnum apnAuthentication) {
        return apnAuthentication != null;
    }

    public boolean validate() {
        return isValidCustomData(customData)
                && isValidApn(apn)
                && isValidApnUserName(apnUserName)
                && isValidApnPassword(apnPassword)
                && isValidPreferredNetwork(preferredNetwork)
                && isValidApnAuthentication(apnAuthentication);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        APN that = (APN) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(apn, that.apn)
                && Objects.equals(apnUserName, that.apnUserName)
                && Objects.equals(apnPassword, that.apnPassword)
                && Objects.equals(simPin, that.simPin)
                && Objects.equals(preferredNetwork, that.preferredNetwork)
                && Objects.equals(useOnlyPreferredNetwork, that.useOnlyPreferredNetwork)
                && Objects.equals(apnAuthentication, that.apnAuthentication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData,
                apn,
                apnUserName,
                apnPassword,
                simPin,
                preferredNetwork,
                useOnlyPreferredNetwork,
                apnAuthentication);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("apn", apn)
                .add("apnUserName", apnUserName)
                .add("apnPassword", apnPassword)
                .add("simPin", simPin)
                .add("preferredNetwork", preferredNetwork)
                .add("useOnlyPreferredNetwork", useOnlyPreferredNetwork)
                .add("apnAuthentication", apnAuthentication)
                .add("isValid", validate())
                .toString();
    }
}
