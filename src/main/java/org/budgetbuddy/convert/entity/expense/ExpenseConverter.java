package org.budgetbuddy.convert.entity.expense;
//=================================-Imports-==================================
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.expense.Expense;

@Converter(autoApply = true)
public class ExpenseConverter implements AttributeConverter<Expense, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public ExpenseConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Methods-===================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(Expense expense) {
        try {
            // Convert the Expense to a JSON string.
            return this.objectMapper.writeValueAsString(expense);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting expense to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public Expense convertToEntityAttribute(String expenseJson) {
        try {
            // Convert the JSON string to an Expense object.
            return this.objectMapper.readValue(expenseJson, Expense.class);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting JSON to expense.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
