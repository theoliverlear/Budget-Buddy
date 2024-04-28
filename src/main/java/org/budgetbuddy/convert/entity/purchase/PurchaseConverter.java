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
            // Convert the Purchase to a JSON string.
            return this.objectMapper.writeValueAsString(purchase);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting purchase to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public Purchase convertToEntityAttribute(String purchaseJson) {
        try {
            // Convert the JSON string to a Purchase object.
            return this.objectMapper.readValue(purchaseJson, Purchase.class);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting JSON to purchase.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
