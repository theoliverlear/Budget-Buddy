package org.budgetbuddy.entity.expense;
//=================================-Imports-==================================
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import org.budgetbuddy.convert.entity.time.TimeIntervalConverter;
import org.budgetbuddy.entity.time.TimeInterval;

@Entity
public class RecurringExpense extends Expense {
    //============================-Variables-=================================
    @Convert(converter = TimeIntervalConverter.class)
    TimeInterval expenseInterval;
    //===========================-Constructors-===============================
    public RecurringExpense() {
        super();
        this.expenseInterval = new TimeInterval();
    }
    public RecurringExpense(String name, double amount) {
        super(name, amount);
        this.expenseInterval = new TimeInterval();
    }
    public RecurringExpense(String name, double amount, TimeInterval expenseInterval) {
        super(name, amount);
        this.expenseInterval = expenseInterval;
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof RecurringExpense recurringExpense) {
            boolean baseObjectIsSame = super.equals(recurringExpense);
            boolean intervalIsSame = this.expenseInterval.equals(recurringExpense.expenseInterval);
            return baseObjectIsSame && intervalIsSame;
        }
        return false;
    }
    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
