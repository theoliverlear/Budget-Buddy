package org.budgetbuddy.convert.entity.holding.saving;

//=================================-Imports-==================================
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.holding.saving.Saving;

@Converter(autoApply = true)
public class SavingConverter implements AttributeConverter<Saving, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public SavingConverter () {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(Saving saving) {
        try {
            // Convert the Saving to a JSON string.
            return this.objectMapper.writeValueAsString(saving);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting saving to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }

    //--------------------Convert-From-Database-Column------------------------
    @Override
    public Saving convertToEntityAttribute(String savingJson) {
        try {
            // Convert the JSON string to a Saving object.
            return this.objectMapper.readValue(savingJson, Saving.class);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting JSON to saving.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
