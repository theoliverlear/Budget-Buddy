package org.budgetbuddy.convert.entity.revenue;
//=================================-Imports-==================================
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.revenue.RecurringRevenue;

import java.util.ArrayList;

@Converter(autoApply = true)
public class RecurringRevenueArrayListConverter implements AttributeConverter<ArrayList<RecurringRevenue>, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public RecurringRevenueArrayListConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Overrides-=================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(ArrayList<RecurringRevenue> recurringRevenues) {
        try {
            // Convert the recurring revenues to a JSON string.
            return this.objectMapper.writeValueAsString(recurringRevenues);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting recurring revenues to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public ArrayList<RecurringRevenue> convertToEntityAttribute(String recurringRevenuesJson) {
        try {
            // If the JSON string is null, return an empty list to indicate an
            // empty recurring revenues list.
            if (recurringRevenuesJson == null) {
                return new ArrayList<>();
            }
            // Convert the JSON string to a recurring revenues ArrayList.
            return this.objectMapper.readValue(recurringRevenuesJson, new TypeReference<>() {});
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting JSON to" +
                                             " recurring revenues ArrayList.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
