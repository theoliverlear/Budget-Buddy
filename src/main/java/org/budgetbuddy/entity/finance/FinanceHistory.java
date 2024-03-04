package org.budgetbuddy.entity.finance;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import org.budgetbuddy.model.format.FormattedTime;

import java.util.HashMap;

//=================================-Imports-==================================
@Entity
public class FinanceHistory {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    Long id;
    @Transient // TODO: Create a converter for this, then remove annotation.
    HashMap<FormattedTime, Finance> financeHistoryMap;
    //===========================-Constructors-===============================
    public FinanceHistory() {
        this.financeHistoryMap = new HashMap<>();
    }
    public FinanceHistory(HashMap<FormattedTime, Finance> financeHistoryMap) {
        this.financeHistoryMap = financeHistoryMap;
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
