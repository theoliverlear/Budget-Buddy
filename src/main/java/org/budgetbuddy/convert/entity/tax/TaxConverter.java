package org.budgetbuddy.convert.entity.tax;

//=================================-Imports-==================================
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.tax.Tax;

@Converter(autoApply = true)
public class TaxConverter implements AttributeConverter<Tax, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public TaxConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(Tax tax) {
        try {
            // Convert the Tax to a JSON string.
            return this.objectMapper.writeValueAsString(tax);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting Tax entity to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public Tax convertToEntityAttribute(String taxJson) {
        try {
            // Convert the JSON string to a Tax object.
            return this.objectMapper.readValue(taxJson, Tax.class);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting JSON to Tax entity.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}