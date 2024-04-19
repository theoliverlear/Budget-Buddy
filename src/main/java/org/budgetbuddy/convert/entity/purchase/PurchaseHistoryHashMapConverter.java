package org.budgetbuddy.convert.entity.purchase;

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
    //============================-Variables-===================================
    ObjectMapper objectMapper;

    //===========================-Constructors-=================================
    public PurchaseHistoryHashMapConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-===================================

    //---------------------Convert-To-Database-Column---------------------------
    @Override
    public String convertToDatabaseColumn(HashMap<Purchase, LocalDateTime> purchaseHistoryMap) {
        try {
            return this.objectMapper.writeValueAsString(purchaseHistoryMap);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting Purchase history map to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }

    //--------------------Convert-From-Database-Column--------------------------
    @Override
    public HashMap<Purchase, LocalDateTime> convertToEntityAttribute(String purchaseHistoryMapJson) {
        try {
            if (purchaseHistoryMapJson == null) {
                return new HashMap<>();
            }
            return this.objectMapper.readValue(purchaseHistoryMapJson, new TypeReference<>() {});
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting JSON to Purchase history map.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}