package org.budgetbuddy.convert.entity.revenue;
//=================================-Imports-==================================
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.revenue.RecurringRevenue;

@Converter(autoApply = true)
public class RecurringRevenueConverter implements AttributeConverter<RecurringRevenue, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public RecurringRevenueConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(RecurringRevenue recurringRevenue) {
        try {
            // Convert the recurring revenue to a JSON string.
            return this.objectMapper.writeValueAsString(recurringRevenue);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting recurring revenue to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public RecurringRevenue convertToEntityAttribute(String recurringRevenueJson) {
        try {
            // Convert the JSON string to a recurring revenue object.
            return this.objectMapper.readValue(recurringRevenueJson, RecurringRevenue.class);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting JSON to recurring revenue.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
