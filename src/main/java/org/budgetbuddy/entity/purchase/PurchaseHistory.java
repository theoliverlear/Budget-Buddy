package org.budgetbuddy.entity.purchase;
//=================================-Imports-==================================
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.budgetbuddy.convert.entity.purchase.PurchaseHistoryHashMapConverter;
import org.budgetbuddy.model.format.FormattedDate;

import java.time.LocalDateTime;
import java.util.HashMap;

@Entity
@Getter
@Setter
public class PurchaseHistory {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Convert(converter = PurchaseHistoryHashMapConverter.class)
    HashMap<Purchase, LocalDateTime> purchasesHistoryMap;
    //===========================-Constructors-===============================
    public PurchaseHistory() {
        this.purchasesHistoryMap = new HashMap<>();
    }
    public PurchaseHistory(HashMap<Purchase, LocalDateTime> purchasesHistoryMap) {
        this.purchasesHistoryMap = purchasesHistoryMap;
    }
    //=============================-Methods-==================================

    public void addPurchase(Purchase purchase, LocalDateTime time) {
        this.purchasesHistoryMap.put(purchase, time);
    }
    public void addPurchaseNow(Purchase purchase) {
        this.purchasesHistoryMap.put(purchase, LocalDateTime.now());
    }
    public void removePurchase(Purchase purchase) {
        this.purchasesHistoryMap.remove(purchase);
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof PurchaseHistory purchaseHistory) {
            if (this.id != null) {
                return this.id.equals(purchaseHistory.id);
            } else {
                return this.purchasesHistoryMap.equals(purchaseHistory.purchasesHistoryMap);
            }
        }
        return false;
    }
    //------------------------------Hash-Code---------------------------------
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
