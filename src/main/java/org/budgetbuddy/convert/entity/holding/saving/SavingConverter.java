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
            return this.objectMapper.writeValueAsString(saving);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting saving to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }

    //--------------------Convert-From-Database-Column------------------------
    @Override
    public Saving convertToEntityAttribute(String savingJson) {
        try {
            return this.objectMapper.readValue(savingJson, Saving.class);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting JSON to saving.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}