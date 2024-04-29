package org.budgetbuddy.convert.entity.tax;

//=================================-Imports-==================================
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.tax.Tax;

import java.time.LocalDateTime;
import java.util.HashMap;

@Converter(autoApply = true)
public class TaxHistoryHashMapConverter implements AttributeConverter<HashMap<Tax, LocalDateTime>, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public TaxHistoryHashMapConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(HashMap<Tax, LocalDateTime> taxHistoryMap) {
        try {
            // Convert the tax history map to a JSON string.
            return this.objectMapper.writeValueAsString(taxHistoryMap);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting tax history map to JSON";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public HashMap<Tax, LocalDateTime> convertToEntityAttribute(String taxHistoryMapJson) {
        try {
            // If the JSON string is null, return an empty map to indicate an
            // empty tax history.
            if (taxHistoryMapJson == null) {
                return new HashMap<>();
            }
            // Convert the JSON string to a tax history map.
            return this.objectMapper.readValue(taxHistoryMapJson, new TypeReference<>() {});
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting JSON to tax history map";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
