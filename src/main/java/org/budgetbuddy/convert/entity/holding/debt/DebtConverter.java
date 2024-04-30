package org.budgetbuddy.convert.entity.holding.debt;
//=================================-Imports-==================================
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.holding.debt.Debt;

@Converter(autoApply = true)
public class DebtConverter implements AttributeConverter<Debt, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public DebtConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(Debt debt) {
        try {
            // Convert the Debt to a JSON string.
            return this.objectMapper.writeValueAsString(debt);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting debt to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public Debt convertToEntityAttribute(String debtJson) {
        try {
            // Convert the JSON string to a Debt object.
            return this.objectMapper.readValue(debtJson, Debt.class);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting JSON to debt.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
