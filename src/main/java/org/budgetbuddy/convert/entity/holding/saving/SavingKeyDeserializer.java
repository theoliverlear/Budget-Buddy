package org.budgetbuddy.convert.entity.holding.saving;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import org.budgetbuddy.entity.holding.saving.Saving;

public class SavingKeyDeserializer extends KeyDeserializer {
    //============================-Variables-=================================
    SavingConverter savingConverter;
    //===========================-Constructors-===============================
    public SavingKeyDeserializer() {
        this.savingConverter = new SavingConverter();
    }
    //============================-Overrides-=================================

    //--------------------------Deserialize-Key-------------------------------
    @Override
    public Saving deserializeKey(String key, DeserializationContext context) {
        return this.savingConverter.convertToEntityAttribute(key);
    }
}
