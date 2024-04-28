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
            // Convert the Interest to a JSON string.
            return this.objectMapper.writeValueAsString(interest);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting interest to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public Interest convertToEntityAttribute(String interestJson) {
        try {
            // Convert the JSON string to an Interest object. The JSON string
            // must contain a field called "interest_type" that specifies the
            // type of interest.
            JsonNode jsonNode = this.objectMapper.readTree(interestJson);
            if (jsonNode.has("interest_type")) {
                String type = jsonNode.get("interest_type").asText();
                // If the interest type is "simple", convert the JSON string
                // to a SimpleInterest object.
                if (type.equals("simple")) {
                    return this.objectMapper.readValue(interestJson, SimpleInterest.class);
                } else if (type.equals("compound")) {
                    // If the interest type is "compound", convert the JSON
                    // string to a CompoundInterest object.
                    return this.objectMapper.readValue(interestJson, CompoundInterest.class);
                }
            }
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting JSON to interest.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
        // If we have reached this point, the interest type was not specified
        // in the JSON string. A runtime exception is thrown to stop the
        // program from using invalid data.
        throw new RuntimeException("Interest type not specified in JSON.");
    }
}