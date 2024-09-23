package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.CallErrorMessage;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.CallMessage;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.CallResultMessage;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Message;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.Communicator;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.Radio;

import com.nimbusds.jose.shaded.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Manejador de la comunicación JSON para el protocolo OCPP en eVolGreen.
 * Gestiona los mensajes JSON entre el cliente y el servidor.
 */
public class JSONCommunicator extends Communicator {

    private static final Logger logger = LoggerFactory.getLogger(JSONCommunicator.class);

    // Constantes para la estructura de los mensajes JSON
    private static final int INDEX_MESSAGEID = 0;
    private static final int TYPENUMBER_CALL = 2;
    private static final int INDEX_CALL_ACTION = 2;
    private static final int INDEX_CALL_PAYLOAD = 3;
    private static final int TYPENUMBER_CALLRESULT = 3;
    private static final int INDEX_CALLRESULT_PAYLOAD = 2;
    private static final int TYPENUMBER_CALLERROR = 4;
    private static final int INDEX_CALLERROR_ERRORCODE = 2;
    private static final int INDEX_CALLERROR_DESCRIPTION = 3;
    private static final int INDEX_CALLERROR_PAYLOAD = 4;
    private static final int INDEX_UNIQUEID = 1;

    // Formatos de mensajes
    private static final String CALL_FORMAT = "[2,\"%s\",\"%s\",%s]";
    private static final String CALLRESULT_FORMAT = "[3,\"%s\",%s]";
    private static final String CALLERROR_FORMAT = "[4,\"%s\",\"%s\",\"%s\",%s]";

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

    /**
     * Anotación para excluir campos en la serialización.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Exclude {}

    /**
     * Serializador y Deserializador para manejar ZonedDateTime en JSON.
     */
    private static class ZonedDateTimeSerializer implements JsonSerializer<ZonedDateTime>, JsonDeserializer<ZonedDateTime> {
        @Override
        public JsonElement serialize(ZonedDateTime zonedDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(zonedDateTime.format(DateTimeFormatter.ISO_INSTANT));
        }

        @Override
        public ZonedDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return ZonedDateTime.parse(jsonElement.getAsJsonPrimitive().getAsString());
        }
    }

    private static final Gson gson;

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

    @Override
    public <T> T unpackPayload(Object payload, Class<T> type) throws Exception {
        return gson.fromJson(payload.toString(), type);
    }

    @Override
    public Object packPayload(Object payload) {
        return gson.toJson(payload);
    }

    @Override
    protected Object makeCallResult(String uniqueId, String action, Object payload) {
        return String.format(CALLRESULT_FORMAT, uniqueId, payload);
    }

    @Override
    protected Object makeCall(String uniqueId, String action, Object payload) {
        return String.format(CALL_FORMAT, uniqueId, action, payload);
    }

    @Override
    protected Object makeCallError(String uniqueId, String action, String errorCode, String errorDescription) {
        return String.format(CALLERROR_FORMAT, uniqueId, errorCode, errorDescription, "{}");
    }

    @Override
    protected Message parse(Object json) {
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