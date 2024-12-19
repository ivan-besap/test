package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums.OCPPInterfaceEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums.OCPPTransportEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums.OCPPVersionEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Perfil de Conexión de Red
 *
 * <p>Define los parámetros funcionales y técnicos para establecer un enlace de comunicación entre
 * la estación de carga y el sistema central (CSMS).
 */
public class NetworkConnectionProfile {
    /** Datos personalizados asociados al perfil de conexión. */
    @Nullable
    private CustomData customData;

    /**
     * Configuración de APN (Access Point Name).
     *
     * <p>Incluye los datos necesarios para establecer una conexión de datos a través de una red
     * móvil.
     */
    @Nullable
    private APN apn;

    /**
     * Versión de OCPP utilizada para esta función de comunicación.
     *
     * <p>Especifica la versión del protocolo OCPP que se empleará.
     */
    private OCPPVersionEnum ocppVersion;

    /**
     * Protocolo de transporte OCPP (SOAP o JSON).
     *
     * <p>Define cómo se realiza la comunicación. Nota: SOAP no es compatible con OCPP 2.0.
     */
    private OCPPTransportEnum ocppTransport;

    /**
     * URL del sistema central (CSMS).
     *
     * <p>Dirección a la cual la estación de carga enviará y recibirá mensajes.
     */
    private String ocppCsmsUrl;

    /**
     * Tiempo de espera en segundos.
     *
     * <p>Define el tiempo máximo que la estación de carga esperará antes de que un mensaje se agote
     * por timeout.
     */
    private Integer messageTimeout;

    /**
     * Perfil de seguridad utilizado.
     *
     * <p>Determina las configuraciones de seguridad para la conexión con el CSMS.
     */
    private Integer securityProfile;

    /**
     * Interfaz de red aplicable.
     *
     * <p>Especifica la interfaz de red que se utilizará.
     */
    private OCPPInterfaceEnum ocppInterface;

    /** Configuración de VPN (Red Privada Virtual). */
    @Nullable
    private VPN vpn;

    /**
     * Constructor para crear un perfil de conexión de red.
     *
     * @param ocppVersion Versión de OCPP a utilizar.
     * @param ocppTransport Protocolo de transporte (SOAP o JSON).
     * @param ocppCsmsUrl URL del sistema central (CSMS).
     * @param messageTimeout Tiempo de espera máximo para los mensajes.
     * @param securityProfile Perfil de seguridad utilizado.
     * @param ocppInterface Interfaz de red aplicable.
     */
    public NetworkConnectionProfile(
            OCPPVersionEnum ocppVersion,
            OCPPTransportEnum ocppTransport,
            String ocppCsmsUrl,
            Integer messageTimeout,
            Integer securityProfile,
            OCPPInterfaceEnum ocppInterface) {
        setOcppVersion(ocppVersion);
        setOcppTransport(ocppTransport);
        setOcppCsmsUrl(ocppCsmsUrl);
        setMessageTimeout(messageTimeout);
        setSecurityProfile(securityProfile);
        setOcppInterface(ocppInterface);
    }

    /**
     * Obtiene los datos personalizados asociados al perfil.
     *
     * @return Datos personalizados, o null si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados para el perfil.
     *
     * @param customData Datos personalizados asociados.
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
     * @param customData Datos personalizados a verificar.
     * @return {@code true} si son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados al perfil de conexión.
     *
     * @param customData Datos personalizados a agregar.
     * @return La instancia actual.
     */
    public NetworkConnectionProfile withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene la configuración de APN (Access Point Name).
     *
     * @return Configuración de APN, o null si no está definida.
     */
    @Nullable
    public APN getApn() {
        return apn;
    }

    /**
     * Establece la configuración de APN.
     *
     * @param apn Configuración de APN para la conexión.
     */
    public void setApn(@Nullable APN apn) {
        if (!isValidApn(apn)) {
            throw new PropertyConstraintException(apn, "La configuración APN no es válida.");
        }
        this.apn = apn;
    }

    /**
     * Verifica si la configuración de APN es válida.
     *
     * @param apn Configuración de APN a verificar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidApn(@Nullable APN apn) {
        return apn == null || apn.validate();
    }

    /**
     * Agrega una configuración de APN al perfil de conexión.
     *
     * @param apn Configuración de APN a agregar.
     * @return La instancia actual.
     */
    public NetworkConnectionProfile withApn(@Nullable APN apn) {
        setApn(apn);
        return this;
    }

    /**
     * Obtiene la versión de OCPP utilizada.
     *
     * @return Versión de OCPP.
     */
    public OCPPVersionEnum getOcppVersion() {
        return ocppVersion;
    }

    /**
     * Establece la versión de OCPP utilizada.
     *
     * @param ocppVersion Versión de OCPP.
     */
    public void setOcppVersion(OCPPVersionEnum ocppVersion) {
        if (!isValidOcppVersion(ocppVersion)) {
            throw new PropertyConstraintException(null, "La versión de OCPP no puede ser nula.");
        }
        this.ocppVersion = ocppVersion;
    }

    /**
     * Verifica si la versión de OCPP es válida.
     *
     * @param ocppVersion Versión de OCPP a verificar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidOcppVersion(OCPPVersionEnum ocppVersion) {
        return ocppVersion != null;
    }

    /**
     * Obtiene el protocolo de transporte OCPP.
     *
     * @return Protocolo de transporte.
     */
    public OCPPTransportEnum getOcppTransport() {
        return ocppTransport;
    }

    /**
     * Establece el protocolo de transporte OCPP.
     *
     * @param ocppTransport Protocolo de transporte.
     */
    public void setOcppTransport(OCPPTransportEnum ocppTransport) {
        if (!isValidOcppTransport(ocppTransport)) {
            throw new PropertyConstraintException(null, "El protocolo de transporte no puede ser nulo.");
        }
        this.ocppTransport = ocppTransport;
    }

    /**
     * Verifica si el protocolo de transporte OCPP es válido.
     *
     * @param ocppTransport Protocolo de transporte a verificar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidOcppTransport(OCPPTransportEnum ocppTransport) {
        return ocppTransport != null;
    }

    /**
     * Obtiene la URL del sistema central (CSMS).
     *
     * @return URL del CSMS.
     */
    public String getOcppCsmsUrl() {
        return ocppCsmsUrl;
    }

    /**
     * Establece la URL del sistema central (CSMS).
     *
     * @param ocppCsmsUrl URL del CSMS.
     */
    public void setOcppCsmsUrl(String ocppCsmsUrl) {
        if (!isValidOcppCsmsUrl(ocppCsmsUrl)) {
            throw new PropertyConstraintException(ocppCsmsUrl, "La URL del CSMS no es válida.");
        }
        this.ocppCsmsUrl = ocppCsmsUrl;
    }

    /**
     * Verifica si la URL del sistema central (CSMS) es válida.
     *
     * @param ocppCsmsUrl URL del CSMS a verificar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidOcppCsmsUrl(String ocppCsmsUrl) {
        return ocppCsmsUrl != null && ocppCsmsUrl.length() <= 512;
    }

    /**
     * Obtiene el tiempo de espera máximo para los mensajes.
     *
     * @return Tiempo de espera en segundos.
     */
    public Integer getMessageTimeout() {
        return messageTimeout;
    }

    /**
     * Establece el tiempo de espera máximo para los mensajes.
     *
     * @param messageTimeout Tiempo de espera en segundos.
     */
    public void setMessageTimeout(Integer messageTimeout) {
        if (!isValidMessageTimeout(messageTimeout)) {
            throw new PropertyConstraintException(messageTimeout, "messageTimeout is invalid");
        }
        this.messageTimeout = messageTimeout;
    }

    /**
     * Verifica si el tiempo de espera máximo para los mensajes es válido.
     *
     * @param messageTimeout Tiempo de espera en segundos a verificar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidMessageTimeout(Integer messageTimeout) {
        return messageTimeout != null;
    }

    /**
     * Obtiene el perfil de seguridad utilizado.
     *
     * @return Perfil de seguridad.
     */
    public Integer getSecurityProfile() {
        return securityProfile;
    }

    /**
     * Establece el perfil de seguridad utilizado.
     *
     * @param securityProfile Perfil de seguridad.
     */
    public void setSecurityProfile(Integer securityProfile) {
        if (!isValidSecurityProfile(securityProfile)) {
            throw new PropertyConstraintException(securityProfile, "securityProfile is invalid");
        }
        this.securityProfile = securityProfile;
    }

    /**
     * Verifica si el perfil de seguridad es válido.
     *
     * @param securityProfile Perfil de seguridad a verificar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidSecurityProfile(Integer securityProfile) {
        return securityProfile != null;
    }

    /**
     * Obtiene la interfaz de red aplicable.
     *
     * @return Interfaz de red.
     */
    public OCPPInterfaceEnum getOcppInterface() {
        return ocppInterface;
    }

    /**
     * Establece la interfaz de red aplicable.
     *
     * @param ocppInterface Interfaz de red.
     */
    public void setOcppInterface(OCPPInterfaceEnum ocppInterface) {
        if (!isValidOcppInterface(ocppInterface)) {
            throw new PropertyConstraintException(ocppInterface, "ocppInterface is invalid");
        }
        this.ocppInterface = ocppInterface;
    }

    /**
     * Verifica si la interfaz de red es válida.
     *
     * @param ocppInterface Interfaz de red a verificar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidOcppInterface(OCPPInterfaceEnum ocppInterface) {
        return ocppInterface != null;
    }

    /**
     * Obtiene la configuración de VPN (Red Privada Virtual).
     *
     * @return Configuración de VPN, o null si no está definida.
     */
    @Nullable
    public VPN getVpn() {
        return vpn;
    }

    /**
     * Establece la configuración de VPN (Red Privada Virtual).
     *
     * @param vpn Configuración de VPN.
     */
    public void setVpn(@Nullable VPN vpn) {
        if (!isValidVpn(vpn)) {
            throw new PropertyConstraintException(vpn, "vpn is invalid");
        }
        this.vpn = vpn;
    }

    /**
     * Verifica si la configuración de VPN es válida.
     *
     * @param vpn Configuración de VPN a verificar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidVpn(@Nullable VPN vpn) {
        return vpn == null || vpn.validate();
    }

    /**
     * Agrega una configuración de VPN al perfil de conexión.
     *
     * @param vpn Configuración de VPN a agregar.
     * @return La instancia actual.
     */
    public NetworkConnectionProfile withVpn(@Nullable VPN vpn) {
        setVpn(vpn);
        return this;
    }

    public boolean validate() {
        return isValidCustomData(customData)
                && isValidApn(apn)
                && isValidOcppVersion(ocppVersion)
                && isValidOcppTransport(ocppTransport)
                && isValidOcppCsmsUrl(ocppCsmsUrl)
                && isValidMessageTimeout(messageTimeout)
                && isValidSecurityProfile(securityProfile)
                && isValidOcppInterface(ocppInterface)
                && isValidVpn(vpn);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NetworkConnectionProfile that = (NetworkConnectionProfile) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(apn, that.apn)
                && Objects.equals(ocppVersion, that.ocppVersion)
                && Objects.equals(ocppTransport, that.ocppTransport)
                && Objects.equals(ocppCsmsUrl, that.ocppCsmsUrl)
                && Objects.equals(messageTimeout, that.messageTimeout)
                && Objects.equals(securityProfile, that.securityProfile)
                && Objects.equals(ocppInterface, that.ocppInterface)
                && Objects.equals(vpn, that.vpn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData,
                apn,
                ocppVersion,
                ocppTransport,
                ocppCsmsUrl,
                messageTimeout,
                securityProfile,
                ocppInterface,
                vpn);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("apn", apn)
                .add("ocppVersion", ocppVersion)
                .add("ocppTransport", ocppTransport)
                .add("ocppCsmsUrl", ocppCsmsUrl)
                .add("messageTimeout", messageTimeout)
                .add("securityProfile", securityProfile)
                .add("ocppInterface", ocppInterface)
                .add("vpn", vpn)
                .add("isValid", validate())
                .toString();
    }
}
