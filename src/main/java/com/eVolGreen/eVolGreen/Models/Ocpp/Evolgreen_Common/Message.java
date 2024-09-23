package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common;


import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Utils.MoreObjects;

/**
 * La clase {@code Message} actúa como un contenedor para los mensajes intercambiados en la
 * comunicación entre el punto de carga y el servidor. Cada mensaje contiene un identificador
 * único, una acción y un payload asociado.
 * <p>
 * Esta clase permite encapsular la información esencial que se envía y recibe en la
 * comunicación OCPP (Open Charge Point Protocol), con métodos para obtener y modificar
 * sus atributos.
 * </p>
 * <p>Ejemplo de uso:</p>
 * <pre>
 * {@code
 * Message mensaje = new Message();
 * mensaje.setId("123");
 * mensaje.setAction("BootNotification");
 * mensaje.setPayload(new BootNotificationPayload());
 * System.out.println(mensaje.toString());
 * }
 * </pre>
 */
public class Message {
    private String id;
    private Object payload;
    private String action;

    /**
     * Obtiene el identificador único del mensaje.
     *
     * @return el identificador único del mensaje.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador único del mensaje.
     *
     * @param id el identificador único del mensaje.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el payload asociado al mensaje.
     *
     * @return el payload del mensaje.
     */
    public Object getPayload() {
        return payload;
    }

    /**
     * Establece el payload para el mensaje.
     *
     * @param payload el objeto a asociar como payload.
     */
    public void setPayload(Object payload) {
        this.payload = payload;
    }

    /**
     * Obtiene la acción que representa este mensaje.
     *
     * @return la acción en formato de cadena.
     */
    public String getAction() {
        return action;
    }

    /**
     * Establece la acción que representa este mensaje.
     *
     * @param action la acción en formato de cadena.
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Genera una representación en cadena del objeto {@code Message}.
     *
     * @return una cadena que representa el estado del objeto {@code Message}.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("payload", payload)
                .add("action", action)
                .toString();
    }
}
