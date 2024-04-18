package org.budgetbuddy.convert.entity.debt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.holding.debt.Debt;
import java.time.LocalDateTime;
import java.util.HashMap;

@Converter(autoApply = true)
public class DebtHistoryHashMapConverter implements AttributeConverter<HashMap<Debt, LocalDateTime>, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public DebtHistoryHashMapConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(HashMap<Debt, LocalDateTime> debtHistoryMap) {
        try {
            return this.objectMapper.writeValueAsString(debtHistoryMap);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting debt history map to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public HashMap<Debt, LocalDateTime> convertToEntityAttribute(String debtHistoryMapJson) {
        try {
            if (debtHistoryMapJson == null) {
                return new HashMap<>();
            }
            return this.objectMapper.readValue(debtHistoryMapJson, new TypeReference<>() {});
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting JSON to debt history map.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
