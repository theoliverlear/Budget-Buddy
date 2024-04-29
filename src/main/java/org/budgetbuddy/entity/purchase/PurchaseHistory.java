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

    //----------------------------Add-Purchase--------------------------------
    public void addPurchase(Purchase purchase, LocalDateTime time) {
        // Add an item to the purchasesHistoryMap with a given date.
        this.purchasesHistoryMap.put(purchase, time);
    }
    //--------------------------Add-Purchase-Now------------------------------
    public void addPurchaseNow(Purchase purchase) {
        // Add an item to the purchasesHistoryMap with the current date.
        this.purchasesHistoryMap.put(purchase, LocalDateTime.now());
    }
    //--------------------------Remove-Purchase-------------------------------
    public void removePurchase(Purchase purchase) {
        // Remove an item from the purchasesHistoryMap.
        this.purchasesHistoryMap.remove(purchase);
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj) {
        // Check if the object references are the same. If they are, return
        // true.
        if (this == obj) return true;
        // Check if the object is an instance of PurchaseHistory. If it is,
        // cast it to a PurchaseHistory object.
        if (obj instanceof PurchaseHistory purchaseHistory) {
            // Check if the fields of the PurchaseHistory objects are equal.
            if (this.id != null) {
                // If the PurchaseHistory id is not null, an equality check
                // can be performed using that field.
                return this.id.equals(purchaseHistory.id);
            } else {
                // If the PurchaseHistory id is null, return whether all
                // fields are equal except for the id field.
                return this.purchasesHistoryMap.equals(purchaseHistory.purchasesHistoryMap);
            }
        }
        // If we have reached this point, the objects are not instances of the
        // same class and are not equal, so we return false.
        return false;
    }
    //------------------------------Hash-Code---------------------------------
    @Override
    public int hashCode() {
        // Return the hashcode of the id field.
        return this.id.hashCode();
    }
    //------------------------------To-String---------------------------------
    // TODO: Implement the toString method for the PurchaseHistory class.
    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
