package org.budgetbuddy.convert.entity.revenue;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import org.budgetbuddy.entity.revenue.Revenue;

public class RevenueKeyDeserializer extends KeyDeserializer {
    //============================-Variables-=================================
    RevenueConverter revenueConverter;
    //===========================-Constructors-===============================
    public RevenueKeyDeserializer() {
        this.revenueConverter = new RevenueConverter();
    }
    //============================-Overrides-=================================

    //--------------------------Deserialize-Key-------------------------------
    @Override
    public Revenue deserializeKey(String key, DeserializationContext context) {
        return this.revenueConverter.convertToEntityAttribute(key);
    }
}
