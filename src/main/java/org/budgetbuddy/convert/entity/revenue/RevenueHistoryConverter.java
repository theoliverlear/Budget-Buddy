package org.budgetbuddy.convert.entity.revenue;

//=================================-Imports-==================================
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.revenue.RevenueHistory;

@Converter(autoApply = true)
public class RevenueHistoryConverter implements AttributeConverter<RevenueHistory, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public RevenueHistoryConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(RevenueHistory revenueHistory) {
        try {
            // Convert the RevenueHistory to a JSON string.
            return this.objectMapper.writeValueAsString(revenueHistory);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting revenue history to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public RevenueHistory convertToEntityAttribute(String revenueHistoryJson) {
        try {
            // Convert the JSON string to a RevenueHistory object.
            return this.objectMapper.readValue(revenueHistoryJson, RevenueHistory.class);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting JSON to revenue history.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}