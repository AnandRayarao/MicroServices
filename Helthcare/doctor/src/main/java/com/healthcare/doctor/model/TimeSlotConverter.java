package com.healthcare.doctor.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import org.apache.kafka.common.protocol.types.Field;

import java.util.Map;

@Converter
public class TimeSlotConverter implements AttributeConverter<Map<String,Boolean>,String> {

    private final ObjectMapper objectMapper = new ObjectMapper();



    @Override
    public String convertToDatabaseColumn(Map<String, Boolean> stringBooleanMap) {
        try {
            return objectMapper.writeValueAsString(stringBooleanMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, Boolean> convertToEntityAttribute(String s) {
        try {
            return objectMapper.readValue(s, new TypeReference<Map<String, Boolean>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
