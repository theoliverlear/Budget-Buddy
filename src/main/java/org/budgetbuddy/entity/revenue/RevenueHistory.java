package org.budgetbuddy.entity.revenue;
//=================================-Imports-==================================
import jakarta.persistence.*;
import org.budgetbuddy.model.format.FormattedDate;

import java.util.HashMap;

@Entity
public class RevenueHistory {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Transient // TODO: Create a converter for this, then remove annotation.
    HashMap<Revenue, FormattedDate> revenueHistoryMap;
    //===========================-Constructors-===============================
    public RevenueHistory() {
        this.revenueHistoryMap = new HashMap<>();
    }
    public RevenueHistory(HashMap<Revenue, FormattedDate> revenueHistoryMap) {
        this.revenueHistoryMap = revenueHistoryMap;
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
