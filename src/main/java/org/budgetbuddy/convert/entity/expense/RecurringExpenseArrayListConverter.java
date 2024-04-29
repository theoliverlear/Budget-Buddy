package org.budgetbuddy.convert.entity.expense;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.expense.RecurringExpense;

import java.util.ArrayList;

@Converter(autoApply = true)
public class RecurringExpenseArrayListConverter implements AttributeConverter<ArrayList<RecurringExpense>, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public RecurringExpenseArrayListConverter() {
        objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(ArrayList<RecurringExpense> recurringExpenses) {
        try {
            // Convert the RecurringExpense ArrayList to a JSON string.
            return objectMapper.writeValueAsString(recurringExpenses);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting recurring expenses to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public ArrayList<RecurringExpense> convertToEntityAttribute(String recurringExpensesJson) {
        try {
            // If the JSON string is null, return an empty list to indicate an
            // empty RecurringExpense list.
            if (recurringExpensesJson == null) {
                return new ArrayList<>();
            }
            // Convert the JSON string to a RecurringExpense ArrayList.
            return objectMapper.readValue(recurringExpensesJson, new TypeReference<>() {});
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting JSON to" +
                                             " recurring expenses ArrayList.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
