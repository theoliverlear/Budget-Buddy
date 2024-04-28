package org.budgetbuddy.convert.entity.tax;
//=================================-Imports-==================================
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
        // Use a TaxConverter to convert the key to a Tax object.
        return this.taxConverter.convertToEntityAttribute(key);
    }
}
