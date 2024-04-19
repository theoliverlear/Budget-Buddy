package org.budgetbuddy.convert.entity.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.user.SafePassword;
@Converter(autoApply = true)
public class SafePasswordConverter implements AttributeConverter<SafePassword, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public SafePasswordConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(SafePassword safePassword) {
        try {
            return this.objectMapper.writeValueAsString(safePassword);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting SafePassword to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public SafePassword convertToEntityAttribute(String safePasswordJson) {
        try {
            return this.objectMapper.readValue(safePasswordJson, SafePassword.class);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting JSON to SafePassword.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
