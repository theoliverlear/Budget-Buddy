package org.budgetbuddy.entity.holding.debt;

import jakarta.persistence.*;
import org.budgetbuddy.model.format.FormattedDate;

import java.util.HashMap;

//=================================-Imports-==================================
@Entity
public class DebtHistory {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Transient // TODO: Create a converter for this, then remove annotation.
    HashMap<Debt, FormattedDate> debtHistoryMap;
    //===========================-Constructors-===============================
    public DebtHistory() {
        this.debtHistoryMap = new HashMap<>();
    }
    public DebtHistory(HashMap<Debt, FormattedDate> debtHistoryMap) {
        this.debtHistoryMap = debtHistoryMap;
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
