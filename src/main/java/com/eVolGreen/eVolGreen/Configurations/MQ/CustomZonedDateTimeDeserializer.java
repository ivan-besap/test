package com.eVolGreen.eVolGreen.Configurations.MQ;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CustomZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {

    @Override
    public ZonedDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String dateString = p.getText().trim();
        try {
            // Si contiene una región, extraemos la parte antes de los corchetes y la zona
            if (dateString.contains("[")) {
                String datePart = dateString.substring(0, dateString.indexOf('['));
                String zonePart = dateString.substring(dateString.indexOf('[') + 1, dateString.indexOf(']'));

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSXXX");
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(datePart, formatter);

                // Aplicamos la zona extraída
                return zonedDateTime.withZoneSameInstant(ZoneId.of(zonePart));
            } else {
                // Parsear sin región si no está presente
                return ZonedDateTime.parse(dateString, DateTimeFormatter.ISO_ZONED_DATE_TIME);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse ZonedDateTime: " + dateString, e);
        }
    }
}
