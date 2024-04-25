package org.budgetbuddy.convert.entity.budget;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import org.budgetbuddy.entity.budget.Budget;

public class BudgetKeyDeserializer extends KeyDeserializer {
    //============================-Variables-=================================
    BudgetConverter budgetConverter;
    //===========================-Constructors-===============================
    public BudgetKeyDeserializer() {
        this.budgetConverter = new BudgetConverter();
    }
    //============================-Overrides-=================================

    //--------------------------Deserialize-Key-------------------------------
    @Override
    public Budget deserializeKey(String key, DeserializationContext context) {
        return this.budgetConverter.convertToEntityAttribute(key);
    }
}
