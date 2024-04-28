package org.budgetbuddy.convert.entity.holding.saving;
//=================================-Imports-==================================
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.holding.saving.SavingHistory;

@Converter(autoApply = true)
public class SavingHistoryConverter implements AttributeConverter<SavingHistory, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public SavingHistoryConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(SavingHistory savingHistory) {
        try {
            // Convert the SavingHistory to a JSON string.
            return this.objectMapper.writeValueAsString(savingHistory);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting saving history to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public SavingHistory convertToEntityAttribute(String savingHistoryJson) {
        try {
            // Convert the JSON string to a SavingHistory object.
            return this.objectMapper.readValue(savingHistoryJson, SavingHistory.class);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting JSON to saving history.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
