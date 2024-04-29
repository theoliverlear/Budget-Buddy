package org.budgetbuddy.convert.entity.holding.debt;
//=================================-Imports-==================================
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.holding.debt.DebtHistory;

@Converter(autoApply = true)
public class DebtHistoryConverter implements AttributeConverter<DebtHistory, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public DebtHistoryConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(DebtHistory debtHistory) {
        try {
            // Convert the DebtHistory to a JSON string.
            return this.objectMapper.writeValueAsString(debtHistory);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting debt history to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public DebtHistory convertToEntityAttribute(String debtHistoryJson) {
        try {
            // Convert the JSON string to a DebtHistory object.
            return this.objectMapper.readValue(debtHistoryJson, DebtHistory.class);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting JSON to debt history.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
