package org.budgetbuddy.convert.entity.time;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.time.TimeInterval;

@Converter(autoApply = true)
public class TimeIntervalConverter implements AttributeConverter<TimeInterval, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public TimeIntervalConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(TimeInterval timeInterval) {
        try {
            return this.objectMapper.writeValueAsString(timeInterval);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting TimeInterval to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public TimeInterval convertToEntityAttribute(String timeIntervalJson) {
        try {
            return this.objectMapper.readValue(timeIntervalJson, TimeInterval.class);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting JSON to TimeInterval.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
