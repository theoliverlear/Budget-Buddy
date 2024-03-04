package org.budgetbuddy.entity.expense;
//=================================-Imports-==================================
import org.budgetbuddy.entity.time.TimeInterval;

public class RecurringExpense extends Expense {
    //============================-Variables-=================================
    TimeInterval expenseInterval;
    //===========================-Constructors-===============================
    public RecurringExpense(String name, double amount, TimeInterval expenseInterval) {
        super(name, amount);
        this.expenseInterval = expenseInterval;
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
