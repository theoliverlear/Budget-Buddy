package org.budgetbuddy.convert.entity.purchase;
//=================================-Imports-==================================
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.purchase.Purchase;

import java.time.LocalDateTime;
import java.util.HashMap;

@Converter(autoApply = true)
public class PurchaseHistoryHashMapConverter implements AttributeConverter<HashMap<Purchase, LocalDateTime>, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public PurchaseHistoryHashMapConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(HashMap<Purchase, LocalDateTime> purchaseHistoryMap) {
        try {
            // Convert the Purchase history map to a JSON string.
            return this.objectMapper.writeValueAsString(purchaseHistoryMap);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting Purchase history map to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public HashMap<Purchase, LocalDateTime> convertToEntityAttribute(String purchaseHistoryMapJson) {
        try {
            // If the JSON string is null, return an empty map to indicate an
            // empty Purchase history.
            if (purchaseHistoryMapJson == null) {
                return new HashMap<>();
            }
            // Convert the JSON string to a Purchase history map.
            return this.objectMapper.readValue(purchaseHistoryMapJson, new TypeReference<>() {});
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting JSON to Purchase history map.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}