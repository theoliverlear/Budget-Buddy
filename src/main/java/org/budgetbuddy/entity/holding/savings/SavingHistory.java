package org.budgetbuddy.entity.holding.savings;
//============================-Variables-=================================
import jakarta.persistence.*;
import org.budgetbuddy.model.format.FormattedDate;

import java.util.HashMap;

@Entity
public class SavingHistory {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Transient // TODO: Create a converter for this, then remove annotation.
    HashMap<Saving, FormattedDate> savingHistoryMap;
    //===========================-Constructors-===============================
    public SavingHistory() {
        this.savingHistoryMap = new HashMap<>();
    }
    public SavingHistory(HashMap<Saving, FormattedDate> savingHistoryMap) {
        this.savingHistoryMap = savingHistoryMap;
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
