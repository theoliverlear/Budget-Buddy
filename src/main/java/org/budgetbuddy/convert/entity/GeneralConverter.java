package org.budgetbuddy.convert.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Deprecated // Does not work with Hibernate
@Converter(autoApply = true)
public class GeneralConverter<T> implements AttributeConverter<T, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public GeneralConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(T generalObject) {
        try {
            return this.objectMapper.writeValueAsString(generalObject);
        } catch (JsonProcessingException ex) {
            final String CLASS_NAME = generalObject.getClass().getSimpleName();
            final String EXCEPTION_MESSAGE = "Error converting %s to JSON.".formatted(CLASS_NAME);
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public T convertToEntityAttribute(String objectJson) {
        try {
            return this.objectMapper.readValue(objectJson, (Class<T>) Object.class);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting JSON to object.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
