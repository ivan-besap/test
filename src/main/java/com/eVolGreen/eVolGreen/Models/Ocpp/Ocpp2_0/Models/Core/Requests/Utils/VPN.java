package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums.VPNEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Representa la configuración de un perfil de VPN (Red Privada Virtual).
 * <p>Incluye detalles como el servidor, usuario, contraseña, tipo de VPN y otros parámetros necesarios
 * para establecer una conexión VPN segura.
 */
public class VPN {
    /** Datos personalizados relacionados con el VPN. */
    @Nullable
    private CustomData customData;

    /** Dirección del servidor VPN. */
    private String server;

    /** Nombre de usuario del VPN. */
    private String user;

    /** Grupo del VPN, si aplica. */
    @Nullable
    private String group;

    /** Contraseña para el acceso al VPN. */
    private String password;

    /** Clave compartida del VPN. */
    private String key;

    /** Tipo de VPN (protocolo utilizado). */
    private VPNEnum type;

    /**
     * Constructor principal para la clase VPN.
     *
     * @param server Dirección del servidor VPN.
     * @param user Nombre de usuario para el acceso al VPN.
     * @param password Contraseña para el acceso al VPN.
     * @param key Clave compartida para la conexión VPN.
     * @param type Tipo de VPN (protocolo utilizado).
     */
    public VPN(String server, String user, String password, String key, VPNEnum type) {
        setServer(server);
        setUser(user);
        setPassword(password);
        setKey(key);
        setType(type);
    }

    /**
     * Obtiene los datos personalizados relacionados con el VPN.
     *
     * @return Objeto {@code CustomData} o {@code null} si no está definido.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece datos personalizados relacionados con el VPN.
     *
     * @param customData Objeto {@code CustomData}.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Datos personalizados inválidos.");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Objeto {@code CustomData} a verificar.
     * @return {@code true} si los datos personalizados son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados relacionados con el VPN.
     *
     * @param customData Datos personalizados.
     * @return La instancia actual.
     */
    public VPN withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene la dirección del servidor VPN.
     *
     * @return Dirección del servidor como {@code String}.
     */
    public String getServer() {
        return server;
    }

    /**
     * Establece la dirección del servidor VPN.
     *
     * @param server Dirección del servidor.
     */
    public void setServer(String server) {
        if (!isValidServer(server)) {
            throw new PropertyConstraintException(server, "La dirección del servidor es inválida.");
        }
        this.server = server;
    }

    private boolean isValidServer(String server) {
        return server != null && server.length() <= 512;
    }

    /**
     * Obtiene el nombre de usuario para el VPN.
     *
     * @return Nombre de usuario como {@code String}.
     */
    public String getUser() {
        return user;
    }

    /**
     * Establece el nombre de usuario para el VPN.
     *
     * @param user Nombre de usuario.
     */
    public void setUser(String user) {
        if (!isValidUser(user)) {
            throw new PropertyConstraintException(user, "El nombre de usuario es inválido.");
        }
        this.user = user;
    }

    private boolean isValidUser(String user) {
        return user != null && user.length() <= 20;
    }

    /**
     * Obtiene el grupo VPN, si aplica.
     *
     * @return Grupo VPN como {@code String} o {@code null}.
     */
    @Nullable
    public String getGroup() {
        return group;
    }

    /**
     * Establece el grupo VPN.
     *
     * @param group Nombre del grupo.
     */
    public void setGroup(@Nullable String group) {
        if (!isValidGroup(group)) {
            throw new PropertyConstraintException(group, "El grupo es inválido.");
        }
        this.group = group;
    }

    private boolean isValidGroup(@Nullable String group) {
        return group == null || group.length() <= 20;
    }

    /**
     * Agrega un grupo VPN.
     *
     * @param group Nombre del grupo.
     * @return La instancia actual.
     */
    public VPN withGroup(@Nullable String group) {
        setGroup(group);
        return this;
    }

    /**
     * Obtiene la contraseña para el acceso al VPN.
     *
     * @return Contraseña como {@code String}.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña para el acceso al VPN.
     *
     * @param password Contraseña para el acceso.
     */
    public void setPassword(String password) {
        if (!isValidPassword(password)) {
            throw new PropertyConstraintException(password, "La contraseña es inválida.");
        }
        this.password = password;
    }

    private boolean isValidPassword(String password) {
        return password != null && password.length() <= 20;
    }

    /**
     * Obtiene la clave compartida del VPN.
     *
     * @return Clave compartida como {@code String}.
     */
    public String getKey() {
        return key;
    }

    /**
     * Establece la clave compartida del VPN.
     *
     * @param key Clave compartida.
     */
    public void setKey(String key) {
        if (!isValidKey(key)) {
            throw new PropertyConstraintException(key, "La clave es inválida.");
        }
        this.key = key;
    }

    private boolean isValidKey(String key) {
        return key != null && key.length() <= 255;
    }

    /**
     * Obtiene el tipo de VPN (protocolo utilizado).
     *
     * @return Tipo de VPN como {@code VPNEnum}.
     */
    public VPNEnum getType() {
        return type;
    }

    /**
     * Establece el tipo de VPN.
     *
     * @param type Tipo de VPN.
     */
    public void setType(VPNEnum type) {
        if (!isValidType(type)) {
            throw new PropertyConstraintException(type, "El tipo de VPN es inválido.");
        }
        this.type = type;
    }

    private boolean isValidType(VPNEnum type) {
        return type != null;
    }

    /**
     * Valida la configuración del VPN.
     *
     * @return {@code true} si todos los parámetros son válidos, {@code false} de lo contrario.
     */
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidServer(server)
                && isValidUser(user)
                && isValidGroup(group)
                && isValidPassword(password)
                && isValidKey(key)
                && isValidType(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VPN that = (VPN) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(server, that.server)
                && Objects.equals(user, that.user)
                && Objects.equals(group, that.group)
                && Objects.equals(password, that.password)
                && Objects.equals(key, that.key)
                && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, server, user, group, password, key, type);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("server", server)
                .add("user", user)
                .add("group", group)
                .add("password", password)
                .add("key", key)
                .add("type", type)
                .add("isValid", validate())
                .toString();
    }
}
