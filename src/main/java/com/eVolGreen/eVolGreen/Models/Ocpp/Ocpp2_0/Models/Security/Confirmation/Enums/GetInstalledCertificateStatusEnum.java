package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation.Enums;

/**
 * Enum para indicar si la estación de carga puede procesar la solicitud.
 *
 * <p>Representa los posibles estados devueltos por la estación de carga en respuesta
 * a una solicitud para obtener los certificados instalados.
 */
public enum GetInstalledCertificateStatusEnum {
    /**
     * La estación de carga ha aceptado la solicitud y puede procesarla.
     */
    Accepted,

    /**
     * No se encontraron certificados instalados que coincidan con los criterios de la solicitud.
     */
    NotFound
}
