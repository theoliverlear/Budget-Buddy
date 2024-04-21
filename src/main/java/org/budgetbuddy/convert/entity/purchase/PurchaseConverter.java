package org.budgetbuddy.convert.entity.purchase;
//=================================-Imports-==================================
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.purchase.Purchase;

@Converter(autoApply = true)
public class PurchaseConverter implements AttributeConverter<Purchase, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public PurchaseConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(Purchase purchase) {
        try {
            return this.objectMapper.writeValueAsString(purchase);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting purchase to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public Purchase convertToEntityAttribute(String purchaseJson) {
        try {
            return this.objectMapper.readValue(purchaseJson, Purchase.class);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting JSON to purchase.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
