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
            // Convert the saving history map to a JSON string.
            return this.objectMapper.writeValueAsString(savingHistoryMap);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting saving history map to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public HashMap<Saving, LocalDateTime> convertToEntityAttribute(String savingHistoryMapJson) {
        try {
            // If the JSON string is null, return an empty map to indicate an
            // empty saving history.
            if (savingHistoryMapJson == null) {
                return new HashMap<>();
            }
            // Convert the JSON string to a saving history map.
            return this.objectMapper.readValue(savingHistoryMapJson, new TypeReference<>() {});
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting JSON to " +
                                             "saving history map.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}