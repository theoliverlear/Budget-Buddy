package org.budgetbuddy.convert.entity.time;
//=================================-Imports-==================================
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
            // Convert the TimeInterval to a JSON string.
            return this.objectMapper.writeValueAsString(timeInterval);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting TimeInterval to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public TimeInterval convertToEntityAttribute(String timeIntervalJson) {
        try {
            // Convert the JSON string to a TimeInterval object.
            return this.objectMapper.readValue(timeIntervalJson, TimeInterval.class);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting JSON to TimeInterval.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}