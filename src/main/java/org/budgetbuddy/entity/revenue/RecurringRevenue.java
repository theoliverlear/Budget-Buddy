package org.budgetbuddy.entity.revenue;
//=================================-Imports-==================================
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import org.budgetbuddy.convert.entity.time.TimeIntervalConverter;
import org.budgetbuddy.entity.time.TimeInterval;

@Entity
public class RecurringRevenue extends Revenue {
    //============================-Variables-=================================
    @Convert(converter = TimeIntervalConverter.class)
    TimeInterval revenueInterval;
    //===========================-Constructors-===============================
    public RecurringRevenue() {
        super();
        this.revenueInterval = new TimeInterval();
    }
    public RecurringRevenue(String name, double amount) {
        super(name, amount);
        this.revenueInterval = new TimeInterval();
    }
    public RecurringRevenue(String name, double amount, TimeInterval revenueInterval) {
        super(name, amount);
        this.revenueInterval = revenueInterval;
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof RecurringRevenue recurringRevenue) {
            boolean baseObjectIsSame = super.equals(recurringRevenue);
            boolean intervalIsSame = this.revenueInterval.equals(recurringRevenue.revenueInterval);
            return baseObjectIsSame && intervalIsSame;
        }
        return false;
    }
    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
