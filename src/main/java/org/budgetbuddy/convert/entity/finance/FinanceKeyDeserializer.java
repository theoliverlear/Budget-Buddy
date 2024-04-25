package org.budgetbuddy.convert.entity.finance;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import org.budgetbuddy.entity.finance.Finance;

public class FinanceKeyDeserializer extends KeyDeserializer {
    //============================-Variables-=================================
    FinanceConverter financeConverter;
    //===========================-Constructors-===============================
    public FinanceKeyDeserializer() {
        this.financeConverter = new FinanceConverter();
    }
    //============================-Overrides-=================================

    //--------------------------Deserialize-Key-------------------------------
    @Override
    public Finance deserializeKey(String key, DeserializationContext context) {
        return this.financeConverter.convertToEntityAttribute(key);
    }
}
