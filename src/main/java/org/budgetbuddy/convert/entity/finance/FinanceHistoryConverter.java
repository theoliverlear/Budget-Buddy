package org.budgetbuddy.convert.entity.finance;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.finance.FinanceHistory;

@Converter(autoApply = true)
public class FinanceHistoryConverter implements AttributeConverter<FinanceHistory, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public FinanceHistoryConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(FinanceHistory financeHistory) {
        try {
            // Convert the FinanceHistory to a JSON string.
            return this.objectMapper.writeValueAsString(financeHistory);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting finance history to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public FinanceHistory convertToEntityAttribute(String financeHistoryJson) {
        try {
            // Convert the JSON string to a FinanceHistory object.
            return this.objectMapper.readValue(financeHistoryJson, FinanceHistory.class);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting JSON to finance history.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
