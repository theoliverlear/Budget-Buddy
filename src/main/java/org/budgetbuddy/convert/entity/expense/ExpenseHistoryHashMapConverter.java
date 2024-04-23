package org.budgetbuddy.convert.entity.expense;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.expense.Expense;

import java.time.LocalDate;
import java.util.HashMap;
@Converter(autoApply = true)
public class ExpenseHistoryHashMapConverter implements AttributeConverter<HashMap<Expense, LocalDate>, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public ExpenseHistoryHashMapConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(HashMap<Expense, LocalDate> expenseHistory) {
        try {
            return this.objectMapper.writeValueAsString(expenseHistory);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting expense history to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public HashMap<Expense, LocalDate> convertToEntityAttribute(String expenseHistoryJson) {
        try {
            if (expenseHistoryJson == null) {
                return new HashMap<>();
            }
            return this.objectMapper.readValue(expenseHistoryJson, new TypeReference<>() {});
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting JSON to expense history.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
