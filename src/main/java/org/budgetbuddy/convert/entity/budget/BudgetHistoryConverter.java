package org.budgetbuddy.convert.entity.budget;
//=================================-Imports-==================================
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.budget.BudgetHistory;
@Converter(autoApply = true)
public class BudgetHistoryConverter implements AttributeConverter<BudgetHistory, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public BudgetHistoryConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(BudgetHistory budgetHistory) {
        try {
            // Convert the BudgetHistory to a JSON string
            return this.objectMapper.writeValueAsString(budgetHistory);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting budget history to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public BudgetHistory convertToEntityAttribute(String budgetHistoryJson) {
        try {
            // Convert the JSON string to a BudgetHistory object.
            return this.objectMapper.readValue(budgetHistoryJson, BudgetHistory.class);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting JSON to budget history.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
