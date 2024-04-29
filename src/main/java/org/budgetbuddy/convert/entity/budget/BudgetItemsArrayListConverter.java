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
            // Convert the BudgetItem ArrayList to a JSON string.
            return this.objectMapper.writeValueAsString(budgetItems);
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting budget items to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public ArrayList<BudgetItem> convertToEntityAttribute(String budgetItemsJson) {
        try {
            // If the JSON string is null, return an empty list to indicate an
            // empty BudgetItem list.
            if (budgetItemsJson == null) {
                return new ArrayList<>();
            }
            // Convert the JSON string to a BudgetItem ArrayList.
            return this.objectMapper.readValue(budgetItemsJson, new TypeReference<>() {});
        } catch (JsonProcessingException ex) {
            // If an error occurs, throw a runtime exception to stop the
            // program from using invalid data.
            final String EXCEPTION_MESSAGE = "Error converting JSON to budget items.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }

}
