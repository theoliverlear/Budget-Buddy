package org.budgetbuddy.convert.entity.tax;

//=================================-Imports-==================================
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.tax.TaxHistory;

@Converter(autoApply = true)
public class TaxHistoryConverter implements AttributeConverter<TaxHistory, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public TaxHistoryConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(TaxHistory taxHistory) {
        try {
            return this.objectMapper.writeValueAsString(taxHistory);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting tax history to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public TaxHistory convertToEntityAttribute(String taxHistoryJson) {
        try {
            return this.objectMapper.readValue(taxHistoryJson, TaxHistory.class);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting JSON to tax history.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
