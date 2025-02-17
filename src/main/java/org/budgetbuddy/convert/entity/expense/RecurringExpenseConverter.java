package org.budgetbuddy.convert.entity.expense;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.expense.RecurringExpense;

@Converter(autoApply = true)
public class RecurringExpenseConverter implements AttributeConverter<RecurringExpense, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public RecurringExpenseConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(RecurringExpense recurringExpense) {
        try {
            // Convert the RecurringExpense to a JSON string.
            return this.objectMapper.writeValueAsString(recurringExpense);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting recurring expense to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public RecurringExpense convertToEntityAttribute(String recurringExpenseJson) {
        try {
            // Convert the JSON string to a RecurringExpense object.
            return this.objectMapper.readValue(recurringExpenseJson, RecurringExpense.class);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting JSON to recurring expense.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
