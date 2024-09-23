
package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.ProtocolVersion;

import java.net.InetSocketAddress;

/**
 * La clase {@code SessionInformation} representa la información relacionada con una sesión
 * de comunicación en OCPP (Open Charge Point Protocol), incluyendo detalles como la dirección
 * de red, el identificador de la sesión y la versión del protocolo OCPP utilizada.
 * <p>
 * Esta clase permite obtener la información clave para gestionar las sesiones en un entorno OCPP,
 * así como construir instancias usando el patrón de diseño Builder.
 * </p>
 */
public class SessionInformation {

    private String identifier;
    private InetSocketAddress address;
    private String SOAPtoURL;
    private String proxiedAddress;
    private ProtocolVersion protocolVersion;

    /**
     * Devuelve el identificador único de la sesión.
     *
     * @return el identificador de la sesión.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Devuelve la dirección de red asociada a la sesión.
     *
     * @return la dirección de red como {@link InetSocketAddress}.
     */
    public InetSocketAddress getAddress() {
        return address;
    }

    /**
     * Devuelve la URL SOAP asociada a la sesión.
     *
     * @return la URL SOAP como cadena.
     */
    public String getSOAPtoURL() {
        return SOAPtoURL;
    }

    /**
     * Devuelve la dirección del proxy si la sesión está proxied.
     *
     * @return la dirección proxied como cadena.
     */
    public String getProxiedAddress() {
        return proxiedAddress;
    }

    /**
     * Devuelve la versión del protocolo OCPP utilizada en la sesión.
     *
     * @return la versión del protocolo como {@link ProtocolVersion}.
     */
    public ProtocolVersion getProtocolVersion() {
        return protocolVersion;
    }

    /**
     * Clase interna {@code Builder} que facilita la creación de instancias de {@link SessionInformation}.
     * <p>
     * Utiliza el patrón de diseño Builder para construir objetos de manera flexible.
     * </p>
     */
    public static class Builder {

        private String identifier;
        private InetSocketAddress address;
        private String SOAPtoURL;
        private String proxiedAddress;
        private ProtocolVersion protocolVersion = ProtocolVersion.OCPP1_6;

        /**
         * Establece el identificador de la sesión.
         *
         * @param identifier el identificador de la sesión.
         * @return la instancia del {@code Builder}.
         */
        public Builder Identifier(String identifier) {
            this.identifier = identifier;
            return this;
        }

        /**
         * Establece la dirección de red de la sesión.
         *
         * @param address la dirección de red como {@link InetSocketAddress}.
         * @return la instancia del {@code Builder}.
         */
        public Builder InternetAddress(InetSocketAddress address) {
            this.address = address;
            return this;
        }

        /**
         * Establece la dirección del proxy para la sesión.
         *
         * @param proxiedAddress la dirección del proxy.
         * @return la instancia del {@code Builder}.
         */
        public Builder ProxiedAddress(String proxiedAddress) {
            this.proxiedAddress = proxiedAddress;
            return this;
        }

        /**
         * Establece la versión del protocolo OCPP para la sesión.
         *
         * @param protocolVersion la versión del protocolo como {@link ProtocolVersion}.
         * @return la instancia del {@code Builder}.
         */
        public Builder ProtocolVersion(ProtocolVersion protocolVersion) {
            this.protocolVersion = protocolVersion;
            return this;
        }

        /**
         * Establece la URL SOAP de destino asociada a la sesión.
         *
         * @param toUrl la URL SOAP de destino.
         * @return la instancia del {@code Builder}.
         */
        public Builder SOAPtoURL(String toUrl) {
            this.SOAPtoURL = toUrl;
            return this;
        }

        /**
         * Construye y devuelve una nueva instancia de {@link SessionInformation} con los valores
         * proporcionados en el {@code Builder}.
         *
         * @return la instancia de {@link SessionInformation}.
         */
        public SessionInformation build() {
            SessionInformation sessionInformation = new SessionInformation();
            sessionInformation.identifier = this.identifier;
            sessionInformation.address = this.address;
            sessionInformation.SOAPtoURL = this.SOAPtoURL;
            sessionInformation.proxiedAddress = this.proxiedAddress;
            sessionInformation.protocolVersion = protocolVersion;
            return sessionInformation;
        }
    }
}
