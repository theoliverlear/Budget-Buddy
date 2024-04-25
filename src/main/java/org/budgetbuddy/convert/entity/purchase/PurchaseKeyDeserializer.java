package org.budgetbuddy.convert.entity.purchase;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import org.budgetbuddy.entity.purchase.Purchase;

public class PurchaseKeyDeserializer extends KeyDeserializer {
    //============================-Variables-=================================
    PurchaseConverter purchaseConverter;
    //===========================-Constructors-===============================
    public PurchaseKeyDeserializer() {
        this.purchaseConverter = new PurchaseConverter();
    }
    //============================-Overrides-=================================

    //--------------------------Deserialize-Key-------------------------------
    @Override
    public Purchase deserializeKey(String key, DeserializationContext context) {
        return this.purchaseConverter.convertToEntityAttribute(key);
    }
}
