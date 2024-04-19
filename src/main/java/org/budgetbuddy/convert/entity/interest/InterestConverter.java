package org.budgetbuddy.convert.entity.interest;
//=================================-Imports-==================================
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.interest.Interest;

@Converter(autoApply = true)
public class InterestConverter implements AttributeConverter<Interest, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public InterestConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(Interest interest) {
        try {
            return this.objectMapper.writeValueAsString(interest);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting interest to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public Interest convertToEntityAttribute(String interestJson) {
        try {
            return this.objectMapper.readValue(interestJson, Interest.class);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting JSON to interest.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
