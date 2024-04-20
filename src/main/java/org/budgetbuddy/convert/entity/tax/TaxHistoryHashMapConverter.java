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
            return this.objectMapper.writeValueAsString(taxHistoryMap);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting tax history map to JSON";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public HashMap<Tax, LocalDateTime> convertToEntityAttribute(String taxHistoryMapJson) {
        try {
            if (taxHistoryMapJson == null) {
                return new HashMap<>();
            }
            return this.objectMapper.readValue(taxHistoryMapJson, new TypeReference<>() {});
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting JSON to tax history map";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
