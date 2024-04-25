package org.budgetbuddy.convert.entity.interest;
//=================================-Imports-==================================
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.interest.CompoundInterest;
import org.budgetbuddy.entity.interest.Interest;
import org.budgetbuddy.entity.interest.SimpleInterest;

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
            JsonNode jsonNode = this.objectMapper.readTree(interestJson);
            if (jsonNode.has("interest_type")) {
                String type = jsonNode.get("interest_type").asText();
                if (type.equals("simple")) {
                    return this.objectMapper.readValue(interestJson, SimpleInterest.class);
                } else if (type.equals("compound")) {
                    return this.objectMapper.readValue(interestJson, CompoundInterest.class);
                }
            }
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting JSON to interest.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
        throw new RuntimeException("Interest type not specified in JSON.");
    }
}
