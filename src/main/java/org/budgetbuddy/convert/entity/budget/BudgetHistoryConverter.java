package org.budgetbuddy.convert.entity.budget;

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
            return this.objectMapper.writeValueAsString(budgetHistory);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting budget history to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public BudgetHistory convertToEntityAttribute(String budgetHistoryJson) {
        try {
            return this.objectMapper.readValue(budgetHistoryJson, BudgetHistory.class);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting JSON to budget history.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
