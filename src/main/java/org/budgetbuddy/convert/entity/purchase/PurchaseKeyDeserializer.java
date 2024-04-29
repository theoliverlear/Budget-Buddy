package org.budgetbuddy.convert.entity.purchase;
//=================================-Imports-==================================
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
        // Use a PurchaseConverter to convert the key to a Purchase object.
        return this.purchaseConverter.convertToEntityAttribute(key);
    }
}
