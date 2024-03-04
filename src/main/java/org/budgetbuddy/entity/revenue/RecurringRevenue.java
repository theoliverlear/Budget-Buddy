package org.budgetbuddy.entity.revenue;
//=================================-Imports-==================================
import org.budgetbuddy.entity.time.TimeInterval;

public class RecurringRevenue extends Revenue {
    //============================-Variables-=================================
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

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
