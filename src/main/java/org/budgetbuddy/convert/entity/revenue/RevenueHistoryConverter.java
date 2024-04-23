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
            return this.objectMapper.writeValueAsString(revenueHistory);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting revenue history to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public RevenueHistory convertToEntityAttribute(String revenueHistoryJson) {
        try {
            return this.objectMapper.readValue(revenueHistoryJson, RevenueHistory.class);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting JSON to revenue history.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}