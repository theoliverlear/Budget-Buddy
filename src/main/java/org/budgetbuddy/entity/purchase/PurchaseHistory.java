package org.budgetbuddy.entity.purchase;
//=================================-Imports-==================================
import jakarta.persistence.*;
import org.budgetbuddy.model.format.FormattedDate;

import java.util.HashMap;

@Entity
public class PurchaseHistory {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Transient // TODO: Create a converter for this, then remove annotation.
    HashMap<Purchase, FormattedDate> purchasesHistoryMap;
    //===========================-Constructors-===============================
    public PurchaseHistory() {
        this.purchasesHistoryMap = new HashMap<>();
    }
    public PurchaseHistory(HashMap<Purchase, FormattedDate> purchasesHistoryMap) {
        this.purchasesHistoryMap = purchasesHistoryMap;
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
