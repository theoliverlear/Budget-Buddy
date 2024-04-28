package org.budgetbuddy.convert.entity.expense;
//=================================-Imports-==================================
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.expense.Expense;

import java.time.LocalDateTime;
import java.util.HashMap;
@Converter(autoApply = true)
public class ExpenseHistoryHashMapConverter implements AttributeConverter<HashMap<Expense, LocalDateTime>, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public ExpenseHistoryHashMapConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(HashMap<Expense, LocalDateTime> expenseHistory) {
        try {
            // Convert the expense history map to a JSON string.
            return this.objectMapper.writeValueAsString(expenseHistory);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting expense history to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public HashMap<Expense, LocalDateTime> convertToEntityAttribute(String expenseHistoryJson) {
        try {
            // If the JSON string is null, return an empty map to indicate an
            // empty expense history.
            if (expenseHistoryJson == null) {
                return new HashMap<>();
            }
            // Convert the JSON string to an expense history map.
            return this.objectMapper.readValue(expenseHistoryJson, new TypeReference<>() {});
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting JSON to expense history.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
