package org.budgetbuddy.convert.entity.budget;
//=================================-Imports-==================================
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.budget.Budget;

@Converter(autoApply = true)
public class BudgetConverter implements AttributeConverter<Budget, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public BudgetConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(Budget budget) {
        try {
            // Convert the Budget to a JSON string.
            return this.objectMapper.writeValueAsString(budget);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting budget to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public Budget convertToEntityAttribute(String budgetJson) {
        try {
            // Convert the JSON string to a Budget object.
            return this.objectMapper.readValue(budgetJson, Budget.class);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting JSON to budget.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
