package org.budgetbuddy.convert.entity.revenue;

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
            return this.objectMapper.writeValueAsString(revenue);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting revenue to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public Revenue convertToEntityAttribute(String revenueJson) {
        try {
            return this.objectMapper.readValue(revenueJson, Revenue.class);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting JSON to revenue.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
