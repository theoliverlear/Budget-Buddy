package org.budgetbuddy.entity.holding.debt;
//=================================-Imports-==================================
import jakarta.persistence.*;
import org.budgetbuddy.convert.entity.holding.debt.DebtHistoryHashMapConverter;

import java.time.LocalDateTime;
import java.util.HashMap;

@Entity
public class DebtHistory {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Convert(converter = DebtHistoryHashMapConverter.class)
    HashMap<Debt, LocalDateTime> debtHistoryMap;
    //===========================-Constructors-===============================
    public DebtHistory() {
        this.debtHistoryMap = new HashMap<>();
    }
    public DebtHistory(HashMap<Debt, LocalDateTime> debtHistoryMap) {
        this.debtHistoryMap = debtHistoryMap;
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj) {
        // Check if the object references are the same. If they are, return
        // true.
        if (this == obj) return true;
        // Check if the object is an instance of DebtHistory. If it is, cast
        // it to a DebtHistory object.
        if (obj instanceof DebtHistory debtHistory) {
            // Check if the fields of the DebtHistory objects are equal.
            if (this.id != null) {
                // If the DebtHistory id is not null, an equality check can be
                // performed using that field.
                return this.id.equals(debtHistory.id);
            } else {
                // If the DebtHistory id is null, return whether all fields
                // are equal except for the id field.
                return this.debtHistoryMap.equals(debtHistory.debtHistoryMap);
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

    //=============================-Getters-==================================
    public Long getId() {
        return this.id;
    }
    public HashMap<Debt, LocalDateTime> getDebtHistoryMap() {
        return this.debtHistoryMap;
    }
    //=============================-Setters-==================================
    public void setId(Long id) {
        this.id = id;
    }
    public void setDebtHistoryMap(HashMap<Debt, LocalDateTime> debtHistoryMap) {
        this.debtHistoryMap = debtHistoryMap;
    }
}
