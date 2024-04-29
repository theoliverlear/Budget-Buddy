package org.budgetbuddy.convert.entity.holding.debt;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import org.budgetbuddy.entity.holding.debt.Debt;

public class DebtKeyDeserializer extends KeyDeserializer {
    //============================-Variables-=================================
    DebtConverter debtConverter;
    //===========================-Constructors-===============================
    public DebtKeyDeserializer() {
        this.debtConverter = new DebtConverter();
    }
    //============================-Overrides-=================================

    //--------------------------Deserialize-Key-------------------------------
    @Override
    public Debt deserializeKey(String key, DeserializationContext context) {
        // Use a DebtConverter to convert the key to a Debt object.
        return this.debtConverter.convertToEntityAttribute(key);
    }
}
