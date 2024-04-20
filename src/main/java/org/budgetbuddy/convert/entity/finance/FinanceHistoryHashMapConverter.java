package org.budgetbuddy.convert.entity.finance;

//=================================-Imports-==================================
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.finance.Finance;

import java.time.LocalDateTime;
import java.util.HashMap;

@Converter(autoApply = true)
public class FinanceHistoryHashMapConverter implements AttributeConverter<HashMap<Finance, LocalDateTime>, String> {
    //============================-Variables-=================================
    private final ObjectMapper objectMapper;

    //===========================-Constructors-===============================
    public FinanceHistoryHashMapConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(HashMap<Finance, LocalDateTime> financeHistoryMap) {
        try {
            return this.objectMapper.writeValueAsString(financeHistoryMap);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting finance history map to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }

    //--------------------Convert-From-Database-Column------------------------
    @Override
    public HashMap<Finance, LocalDateTime> convertToEntityAttribute(String financeHistoryMapJson) {
        try {
            if (financeHistoryMapJson == null) {
                return new HashMap<>();
            }
            return this.objectMapper.readValue(financeHistoryMapJson, new TypeReference<>() {});
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting JSON to finance history map.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
