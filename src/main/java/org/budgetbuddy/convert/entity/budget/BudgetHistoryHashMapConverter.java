package org.budgetbuddy.convert.entity.budget;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.budget.Budget;

import java.time.LocalDateTime;
import java.util.HashMap;

@Converter(autoApply = true)
public class BudgetHistoryHashMapConverter implements AttributeConverter<HashMap<Budget, LocalDateTime>, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public BudgetHistoryHashMapConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(HashMap<Budget, LocalDateTime> budgetHistoryMap) {
        try {
            return this.objectMapper.writeValueAsString(budgetHistoryMap);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting budget history map to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public HashMap<Budget, LocalDateTime> convertToEntityAttribute(String budgetHistoryMapJson) {
        try {
            if (budgetHistoryMapJson == null) {
                return new HashMap<>();
            }
            return this.objectMapper.readValue(budgetHistoryMapJson, new TypeReference<>() {});
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting JSON to budget history map.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
