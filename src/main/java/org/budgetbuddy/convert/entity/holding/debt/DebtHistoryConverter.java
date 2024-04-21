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
            return this.objectMapper.writeValueAsString(debtHistory);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting debt history to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public DebtHistory convertToEntityAttribute(String debtHistoryJson) {
        try {
            return this.objectMapper.readValue(debtHistoryJson, DebtHistory.class);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting JSON to debt history.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
