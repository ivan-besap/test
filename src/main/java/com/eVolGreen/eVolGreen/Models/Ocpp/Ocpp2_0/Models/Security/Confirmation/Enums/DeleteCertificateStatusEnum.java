package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation.Enums;

/**
 * Enumera los posibles estados de la respuesta al intentar eliminar un certificado.
 *
 * <p>La estación de carga utiliza estos valores para indicar si puede procesar o no la solicitud de
 * eliminación del certificado especificado.
 */
public enum DeleteCertificateStatusEnum {

    /**
     * La estación de carga ha aceptado y procesado exitosamente la solicitud de eliminación del
     * certificado.
     */
    Accepted,

    /**
     * La estación de carga no pudo procesar la solicitud de eliminación debido a un error.
     */
    Failed,

    /**
     * La estación de carga no pudo encontrar el certificado especificado en la solicitud.
     */
    NotFound
}
