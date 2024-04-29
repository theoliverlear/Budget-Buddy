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
        // Check if the object references are the same. If they are, return
        // true.
        if (this == obj) return true;
        // Check if the object is an instance of RecurringExpense. If it is,
        // cast it to a RecurringExpense object.
        if (obj instanceof RecurringExpense recurringExpense) {
            // Check if the fields of the RecurringExpense objects are equal.
            // We use the fields of the super class to check for equality for
            // the base object and include the additional field for the
            // time interval.
            boolean baseObjectIsSame = super.equals(recurringExpense);
            boolean intervalIsSame = this.expenseInterval.equals(recurringExpense.expenseInterval);
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
        return super.hashCode() + this.expenseInterval.hashCode();
    }
    //------------------------------To-String---------------------------------
    // TODO: Implement the toString method for the RecurringExpense class.
    //=============================-Getters-==================================
    public TimeInterval getExpenseInterval() {
        return this.expenseInterval;
    }
    //=============================-Setters-==================================
    public void setExpenseInterval(TimeInterval expenseInterval) {
        this.expenseInterval = expenseInterval;
    }
}