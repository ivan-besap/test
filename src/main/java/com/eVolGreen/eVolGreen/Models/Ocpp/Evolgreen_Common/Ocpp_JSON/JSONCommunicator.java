package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.CommunicatorEvents;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.NotConnectedException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.CallErrorMessage;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.CallMessage;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.CallResultMessage;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Message;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Communicator;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Radio;

import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

/**
 * Manejador de la comunicación JSON para el protocolo OCPP en eVolGreen.
 * Gestiona los mensajes JSON entre el cliente y el servidor.
 * <p>
 * Esta clase utiliza Gson para serializar y deserializar objetos a JSON y viceversa.
 * También maneja la estructura de mensajes OCPP, como CallMessage, CallResultMessage y CallErrorMessage.
 * </p>
 *
 * Ejemplo de uso:
 * <pre>
 * {@code
 * Radio radio = new Radio();
 * JSONCommunicator communicator = new JSONCommunicator(radio);
 * CallMessage message = new CallMessage();
 * String payload = communicator.packPayload(message);
 * }
 * </pre>
 */
public abstract class JSONCommunicator extends Communicator {

    private static final Logger logger = LoggerFactory.getLogger(JSONCommunicator.class);

    // Constantes para la estructura de los mensajes JSON
    public static final int INDEX_MESSAGEID = 0;
    public static final int TYPENUMBER_CALL = 2;
    public static final int INDEX_CALL_ACTION = 2;
    public static final int INDEX_CALL_PAYLOAD = 3;
    public static final int TYPENUMBER_CALLRESULT = 3;
    public static final int INDEX_CALLRESULT_PAYLOAD = 2;
    public static final int TYPENUMBER_CALLERROR = 4;
    public static final int INDEX_CALLERROR_ERRORCODE = 2;
    public static final int INDEX_CALLERROR_DESCRIPTION = 3;
    public static final int INDEX_CALLERROR_PAYLOAD = 4;
    public static final int INDEX_UNIQUEID = 1;

    // Formatos de mensajes
    public static final String CALL_FORMAT = "[2,\"%s\",\"%s\",%s]";
    public static final String CALLRESULT_FORMAT = "[3,\"%s\",%s]";
    public static final String CALLERROR_FORMAT = "[4,\"%s\",\"%s\",\"%s\",%s]";

    // Formatos de fecha
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final String DATE_FORMAT_WITH_MS = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final int DATE_FORMAT_WITH_MS_LENGTH = 24;

    private boolean hasLongDateFormat = false;

    /**
     * Constructor para manejar la comunicación JSON.
     *
     * @param radio instancia del {@link Radio} para transmisión de mensajes.
     */
    public JSONCommunicator(Radio radio) {
        super(radio);
    }

    /**
     * Constructor que permite habilitar la cola de transacciones.
     *
     * @param radio instancia del {@link Radio}.
     * @param enableTransactionQueue true para habilitar la cola de transacciones.
     */
    public JSONCommunicator(Radio radio, boolean enableTransactionQueue) {
        super(radio, enableTransactionQueue);
    }

    @Override
    public void connect(String uri, CommunicatorEvents events) {

    }

    public abstract void receivedMessage(UUID sessionId, Object message);

    /**
     * Anotación personalizada que excluye ciertos campos de la serialización JSON.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Exclude {}

    /**
     * Serializador y Deserializador para manejar objetos {@link ZonedDateTime} en JSON.
     * <p>
     * Convierte objetos de tipo {@link ZonedDateTime} a su representación ISO 8601.
     * </p>
     *
     * Ejemplo:
     * <pre>
     * {@code
     * ZonedDateTimeSerializer serializer = new ZonedDateTimeSerializer();
     * ZonedDateTime dateTime = ZonedDateTime.now();
     * JsonElement json = serializer.serialize(dateTime, ZonedDateTime.class, new JsonSerializationContext());
     * }
     * </pre>
     */
    private static class ZonedDateTimeSerializer implements JsonSerializer<ZonedDateTime>, JsonDeserializer<ZonedDateTime> {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.nnnnnnnnnX");

        @Override
        public JsonElement serialize(ZonedDateTime zonedDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(zonedDateTime.format(formatter));
        }

        @Override
        public ZonedDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            try {
                // Intentar con el nuevo patrón para precisión de nanosegundos
                return ZonedDateTime.parse(jsonElement.getAsString(), formatter);
            } catch (DateTimeParseException e) {
                // Manejo alternativo en caso de un error de formato
                throw new JsonParseException("Formato de fecha no soportado: " + jsonElement.getAsString(), e);
            }
        }
    }


    // Gson con un custom serializer para ZonedDateTime y estrategia de exclusión para campos anotados con @Exclude
    public static final Gson gson;

    static {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeSerializer());
        builder.addSerializationExclusionStrategy(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }

            @Override
            public boolean shouldSkipField(FieldAttributes field) {
                return field.getAnnotation(Exclude.class) != null;
            }
        });

        gson = builder.disableHtmlEscaping().create();
    }

    /**
     * Desempaqueta un payload JSON y lo convierte en un objeto del tipo especificado.
     *
     * @param payload El objeto JSON a desempaquetar.
     * @param type    El tipo de clase al que se desea convertir el payload.
     * @param <T>     El tipo genérico del objeto de retorno.
     * @return El objeto deserializado.
     * @throws Exception Si ocurre un error durante la deserialización.
     *
     * Ejemplo:
     * <pre>
     * {@code
     * String payload = "{\"name\":\"example\"}";
     * MyObject obj = communicator.unpackPayload(payload, MyObject.class);
     * }
     * </pre>
     */
    @Override
    public <T> T unpackPayload(Object payload, Class<T> type) throws Exception {
        return gson.fromJson(payload.toString(), type);
    }

    /**
     * Empaqueta un objeto como una cadena JSON.
     *
     * @param payload El objeto a convertir en JSON.
     * @return El objeto convertido en una cadena JSON.
     *
     * Ejemplo:
     * <pre>
     * {@code
     * MyObject obj = new MyObject();
     * String json = communicator.packPayload(obj);
     * }
     * </pre>
     */
    @Override
    public Object packPayload(Object payload) {
        return payload;
    }

    /**
     * Genera un mensaje CallResult OCPP con un payload dado.
     *
     * @param uniqueId El ID único del mensaje.
     * @param action   La acción del mensaje.
     * @param payload  El contenido del payload.
     * @return El mensaje CallResult como cadena JSON.
     *
     * Ejemplo:
     * <pre>
     * {@code
     * String callResult = communicator.makeCallResult("12345", "SomeAction", new MyPayload());
     * }
     * </pre>
     */
    @Override
    protected Object makeCallResult(String uniqueId, String action, Object payload) {
        return String.format(CALLRESULT_FORMAT, uniqueId, payload);
    }

    /**
     * Genera un mensaje Call OCPP con un payload dado.
     *
     * @param uniqueId El ID único del mensaje.
     * @param action   La acción del mensaje.
     * @param payload  El contenido del payload.
     * @return El mensaje Call como cadena JSON.
     *
     * Ejemplo:
     * <pre>
     * {@code
     * String call = communicator.makeCall("12345", "SomeAction", new MyPayload());
     * }
     * </pre>
     */
    @Override
    protected Object makeCall(String uniqueId, String action, Object payload) {
        return String.format(CALL_FORMAT, uniqueId, action, payload);
    }

    /**
     * Genera un mensaje CallError OCPP.
     *
     * @param uniqueId        El ID único del mensaje.
     * @param action          La acción del mensaje.
     * @param errorCode       El código de error.
     * @param errorDescription La descripción del error.
     * @return El mensaje CallError como cadena JSON.
     *
     * Ejemplo:
     * <pre>
     * {@code
     * String callError = communicator.makeCallError("12345", "SomeAction", "ErrorCode", "ErrorDescription");
     * }
     * </pre>
     */
    @Override
    protected Object makeCallError(String uniqueId, String action, String errorCode, String errorDescription) {
        return String.format(CALLERROR_FORMAT, uniqueId, errorCode, errorDescription, "{}");
    }

    @Override
    public boolean isClosed() {
        return false;
    }

    /**
     * Analiza una cadena JSON y la convierte en un objeto de tipo {@link Message}.
     *
     * @param json La cadena JSON que contiene el mensaje.
     * @return El objeto {@link Message} representando el mensaje analizado.
     *
     * Ejemplo:
     * <pre>
     * {@code
     * String json = "{\"messageId\":\"12345\", \"action\":\"SomeAction\"}";
     * Message message = communicator.parse(json);
     * }
     * </pre>
     */
    @Override
    public Message parse(Object json) {
        Message message;
        JsonArray array;
        String messageId = "-1";

        try {
            array = JsonParser.parseString(json.toString()).getAsJsonArray();
            messageId = array.get(INDEX_UNIQUEID).getAsString();

            int messageType = array.get(INDEX_MESSAGEID).getAsInt();
            switch (messageType) {
                case TYPENUMBER_CALL:
                    message = new CallMessage();
                    message.setAction(array.get(INDEX_CALL_ACTION).getAsString());
                    message.setPayload(array.get(INDEX_CALL_PAYLOAD).toString());
                    break;
                case TYPENUMBER_CALLRESULT:
                    message = new CallResultMessage();
                    message.setPayload(array.get(INDEX_CALLRESULT_PAYLOAD).toString());
                    break;
                case TYPENUMBER_CALLERROR:
                    message = new CallErrorMessage();
                    ((CallErrorMessage) message).setErrorCode(array.get(INDEX_CALLERROR_ERRORCODE).getAsString());
                    ((CallErrorMessage) message).setErrorDescription(array.get(INDEX_CALLERROR_DESCRIPTION).getAsString());
                    ((CallErrorMessage) message).setRawPayload(array.get(INDEX_CALLERROR_PAYLOAD).toString());
                    break;
                default:
                    logger.error("Tipo de mensaje desconocido: {}. Contenido del mensaje: {}", messageType, json);
                    sendCallError(messageId, null, "MessageTypeNotSupported", "Tipo de mensaje no soportado: " + messageType);
                    return null;
            }
        } catch (JsonSyntaxException e) {
            logger.error("Error de sintaxis JSON al analizar el mensaje: {}. Error: {}", json, e.getMessage());
            sendCallError(messageId, null, "RpcFrameworkError", "Error de sintaxis JSON: " + e.getMessage());
            return null;
        } catch (Exception e) {
            logger.error("Error inesperado al analizar el mensaje: {}. Error: {}", json, e.getMessage());
            sendCallError(messageId, null, "RpcFrameworkError", "Error inesperado: " + e.getMessage());
            return null;
        }

        message.setId(messageId);
        return message;
    }
}
