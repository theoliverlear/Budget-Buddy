package org.budgetbuddy.convert.entity.holding.saving;

//=================================-Imports-==================================
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.holding.saving.Saving;

import java.time.LocalDateTime;
import java.util.HashMap;

@Converter(autoApply = true)
public class SavingHistoryHashMapConverter implements AttributeConverter<HashMap<Saving, LocalDateTime>, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public SavingHistoryHashMapConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(HashMap<Saving, LocalDateTime> savingHistoryMap) {
        try {
            return this.objectMapper.writeValueAsString(savingHistoryMap);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting saving history map to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }

    //--------------------Convert-From-Database-Column------------------------
    @Override
    public HashMap<Saving, LocalDateTime> convertToEntityAttribute(String savingHistoryMapJson) {
        try {
            if (savingHistoryMapJson == null) {
                return new HashMap<>();
            }
            return this.objectMapper.readValue(savingHistoryMapJson, new TypeReference<>() {});
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting JSON to " +
                                             "saving history map.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
