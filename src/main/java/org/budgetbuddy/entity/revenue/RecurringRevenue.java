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
        // Check if the object references are the same. If they are, return
        // true.
        if (this == obj) return true;
        // Check if the object is an instance of RecurringRevenue. If it is,
        // cast it to a RecurringRevenue object.
        if (obj instanceof RecurringRevenue recurringRevenue) {
            // Check if the fields of the RecurringRevenue objects are equal.
            boolean baseObjectIsSame = super.equals(recurringRevenue);
            boolean intervalIsSame = this.revenueInterval.equals(recurringRevenue.revenueInterval);
            return baseObjectIsSame && intervalIsSame;
        }
        // If we have reached this point, the objects are not instances of the
        // same class and are not equal, so we return false.
        return false;
    }
    //------------------------------Hash-Code---------------------------------
    @Override
    public int hashCode() {
        // Return the hashcode of the super class and the time interval.
        return super.hashCode() + this.revenueInterval.hashCode();
    }
    //------------------------------To-String---------------------------------
    // TODO: Implement the toString method for the RecurringRevenue class.
    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
