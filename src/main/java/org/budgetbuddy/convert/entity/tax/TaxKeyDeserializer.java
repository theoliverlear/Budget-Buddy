package org.budgetbuddy.convert.entity.tax;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import org.budgetbuddy.entity.tax.Tax;

public class TaxKeyDeserializer extends KeyDeserializer {
    //============================-Variables-=================================
    TaxConverter taxConverter;
    //===========================-Constructors-===============================
    public TaxKeyDeserializer() {
        this.taxConverter = new TaxConverter();
    }
    //============================-Overrides-=================================

    //--------------------------Deserialize-Key-------------------------------
    @Override
    public Tax deserializeKey(String key, DeserializationContext context) {
        return this.taxConverter.convertToEntityAttribute(key);
    }
}
