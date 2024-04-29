package org.budgetbuddy.convert.entity.revenue;
//=================================-Imports-==================================
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.revenue.Revenue;

@Converter(autoApply = true)
public class RevenueConverter implements AttributeConverter<Revenue, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public RevenueConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(Revenue revenue) {
        try {
            // Convert the revenue to a JSON string.
            return this.objectMapper.writeValueAsString(revenue);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting revenue to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public Revenue convertToEntityAttribute(String revenueJson) {
        try {
            // Convert the JSON string to a revenue object.
            return this.objectMapper.readValue(revenueJson, Revenue.class);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting JSON to revenue.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
