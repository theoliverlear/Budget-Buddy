package org.budgetbuddy.convert.entity.holding.debt;
//=================================-Imports-==================================
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
            // Convert the debt history map to a JSON string.
            return this.objectMapper.writeValueAsString(debtHistoryMap);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting debt history map to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public HashMap<Debt, LocalDateTime> convertToEntityAttribute(String debtHistoryMapJson) {
        try {
            // If the JSON string is null, return an empty map to indicate an
            // empty debt history.
            if (debtHistoryMapJson == null) {
                return new HashMap<>();
            }
            // Convert the JSON string to a debt history map.
            return this.objectMapper.readValue(debtHistoryMapJson, new TypeReference<>() {});
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting JSON to debt history map.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
