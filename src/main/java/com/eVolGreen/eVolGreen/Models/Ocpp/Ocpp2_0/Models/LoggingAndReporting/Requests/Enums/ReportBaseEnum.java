package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests.Enums;

/**
 * Enumera los tipos de bases de informes que pueden solicitarse en el contexto de OCPP 2.0.1.
 *
 * <p>Se utiliza para indicar el alcance o nivel de detalle del informe que el punto de carga debe
 * generar y enviar al sistema central.</p>
 */
public enum ReportBaseEnum {

    /**
     * Indica que el informe solicitado debe contener información sobre la configuración
     * actual del punto de carga, incluyendo parámetros específicos y su estado.
     */
    ConfigurationInventory,

    /**
     * Indica que el informe solicitado debe incluir un inventario completo, con todos los detalles
     * relacionados con la configuración y componentes del punto de carga.
     */
    FullInventory,

    /**
     * Indica que el informe solicitado debe ser un resumen del inventario, proporcionando
     * una visión general en lugar de información detallada.
     */
    SummaryInventory
}
