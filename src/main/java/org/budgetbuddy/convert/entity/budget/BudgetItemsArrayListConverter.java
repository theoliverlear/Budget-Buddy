package org.budgetbuddy.convert.entity.budget;
//=================================-Imports-==================================
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.budget.BudgetItem;

import java.util.ArrayList;

@Converter(autoApply = true)
public class BudgetItemsArrayListConverter implements AttributeConverter<ArrayList<BudgetItem>, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public BudgetItemsArrayListConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Methods-===================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(ArrayList<BudgetItem> budgetItems) {
        try {
            return this.objectMapper.writeValueAsString(budgetItems);
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting budget items to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public ArrayList<BudgetItem> convertToEntityAttribute(String budgetItemsJson) {
        try {
            if (budgetItemsJson == null) {
                return new ArrayList<>();
            }
            return this.objectMapper.readValue(budgetItemsJson, new TypeReference<>() {});
        } catch (JsonProcessingException ex) {
            final String EXCEPTION_MESSAGE = "Error converting JSON to budget items.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }

}
