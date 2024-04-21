package org.budgetbuddy.convert.entity.interest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.interest.Interest;

import java.util.Optional;
@Converter(autoApply = true)
public class OptionalInterestConverter implements AttributeConverter<Optional<Interest>, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public OptionalInterestConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(Optional<Interest> optionalInterest) {
        try {
            return this.objectMapper.writeValueAsString(optionalInterest);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting optional interest to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public Optional<Interest> convertToEntityAttribute(String optionalInterestJson) {
        try {
            if (optionalInterestJson == null) {
                return Optional.empty();
            }
            return this.objectMapper.readValue(optionalInterestJson, new TypeReference<>() {});
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting JSON to optional interest.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
