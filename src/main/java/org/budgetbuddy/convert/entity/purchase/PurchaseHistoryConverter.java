package org.budgetbuddy.convert.entity.purchase;

//=================================-Imports-==================================
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.purchase.PurchaseHistory;

@Converter(autoApply = true)
public class PurchaseHistoryConverter implements AttributeConverter<PurchaseHistory, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public PurchaseHistoryConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(PurchaseHistory purchaseHistory) {
        try {
            // Convert the PurchaseHistory to a JSON string.
            return this.objectMapper.writeValueAsString(purchaseHistory);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting PurchaseHistory to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }

    //--------------------Convert-From-Database-Column------------------------
    @Override
    public PurchaseHistory convertToEntityAttribute(String purchaseHistoryJson) {
        try {
            // Convert the JSON string to a PurchaseHistory object.
            return this.objectMapper.readValue(purchaseHistoryJson, PurchaseHistory.class);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting JSON to PurchaseHistory.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
