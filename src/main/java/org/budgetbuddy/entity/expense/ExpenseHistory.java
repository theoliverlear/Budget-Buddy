package org.budgetbuddy.entity.expense;
//=================================-Imports-==================================
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import org.budgetbuddy.model.format.FormattedDate;
import org.budgetbuddy.model.format.FormattedTime;

import java.util.HashMap;

@Entity
public class ExpenseHistory {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    Long id;
    @Transient // TODO: Create a converter for this, then remove annotation.
    HashMap<Expense, FormattedDate> expenseHistoryMap;
    //===========================-Constructors-===============================
    public ExpenseHistory() {
        this.expenseHistoryMap = new HashMap<>();
    }
    public ExpenseHistory(HashMap<Expense, FormattedDate> expenseHistoryMap) {
        this.expenseHistoryMap = expenseHistoryMap;
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
