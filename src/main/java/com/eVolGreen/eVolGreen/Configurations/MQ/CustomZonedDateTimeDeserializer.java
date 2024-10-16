package com.eVolGreen.eVolGreen.Configurations.MQ;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class CustomZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {
    @Override
    public ZonedDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String date = p.getText();
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                .optionalStart()
                .appendFraction(java.time.temporal.ChronoField.NANO_OF_SECOND, 0, 9, true)
                .optionalEnd()
                .optionalStart()
                .appendLiteral('[')
                .appendZoneId()
                .appendLiteral(']')
                .optionalEnd()
                .toFormatter();

        try {
            return ZonedDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new IOException("Error deserializando ZonedDateTime con nanosegundos", e);
        }
    }
}



