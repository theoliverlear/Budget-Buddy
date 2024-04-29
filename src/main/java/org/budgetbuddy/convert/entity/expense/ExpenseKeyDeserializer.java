package org.budgetbuddy.convert.entity.expense;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import org.budgetbuddy.entity.expense.Expense;

public class ExpenseKeyDeserializer extends KeyDeserializer {
    //============================-Variables-=================================
    ExpenseConverter expenseConverter;
    //===========================-Constructors-===============================
    public ExpenseKeyDeserializer() {
        this.expenseConverter = new ExpenseConverter();
    }
    //============================-Overrides-=================================

    //--------------------------Deserialize-Key-------------------------------
    @Override
    public Expense deserializeKey(String key, DeserializationContext context) {
        // Use an ExpenseConverter to convert the key to an Expense object.
        return this.expenseConverter.convertToEntityAttribute(key);
    }
}
