package org.budgetbuddy.convert.entity.budget;
//=================================-Imports-==================================
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
        // Use a BudgetConverter to convert the key to a Budget object.
        return this.budgetConverter.convertToEntityAttribute(key);
    }
}
