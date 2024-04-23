package org.budgetbuddy.convert.entity.revenue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.revenue.Revenue;

import java.time.LocalDateTime;
import java.util.HashMap;

@Converter(autoApply = true)
public class RevenueHistoryHashMapConverter implements AttributeConverter<HashMap<Revenue, LocalDateTime>, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public RevenueHistoryHashMapConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(HashMap<Revenue, LocalDateTime> revenueHistory) {
        try {
            return this.objectMapper.writeValueAsString(revenueHistory);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting revenue history to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public HashMap<Revenue, LocalDateTime> convertToEntityAttribute(String revenueHistoryJson) {
        try {
            if (revenueHistoryJson == null) {
                return new HashMap<>();
            }
            return this.objectMapper.readValue(revenueHistoryJson, new TypeReference<>() {});
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting JSON to revenue history map.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
