package org.budgetbuddy.convert.entity.finance;
//=================================-Imports-==================================
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.finance.Finance;

@Converter(autoApply = true)
public class FinanceConverter implements AttributeConverter<Finance, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public FinanceConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(Finance finance) {
        try {
            // Convert the Finance to a JSON string.
            return this.objectMapper.writeValueAsString(finance);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting finance to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public Finance convertToEntityAttribute(String financeJson) {
        try {
            // Convert the JSON string to a Finance object.
            return this.objectMapper.readValue(financeJson, Finance.class);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting JSON to finance.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
