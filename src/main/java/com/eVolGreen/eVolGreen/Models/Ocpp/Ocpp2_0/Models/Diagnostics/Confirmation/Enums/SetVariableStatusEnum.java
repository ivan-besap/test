package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.Enums;

/**
 * Estado del resultado al establecer una variable en la estación de carga.
 *
 * <p>Este enum describe los diferentes estados posibles que puede devolver la estación de carga al
 * intentar modificar una variable. Proporciona detalles sobre el éxito o los motivos del fallo.
 */
public enum SetVariableStatusEnum {
    /** La solicitud de modificación de la variable ha sido aceptada exitosamente. */
    Accepted,
    /** La solicitud de modificación de la variable ha sido rechazada. */
    Rejected,
    /** El componente especificado no es conocido por la estación de carga. */
    UnknownComponent,
    /** La variable especificada no es conocida por la estación de carga. */
    UnknownVariable,
    /** El tipo de atributo especificado no es soportado. */
    NotSupportedAttributeType,
    /** Se requiere reiniciar la estación de carga para aplicar el cambio. */
    RebootRequired
}
