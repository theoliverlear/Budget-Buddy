package org.budgetbuddy.entity.finance;
//=================================-Imports-==================================
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.budgetbuddy.convert.entity.finance.FinanceHistoryHashMapConverter;

import java.time.LocalDateTime;
import java.util.HashMap;

@Entity
@Getter
@Setter
public class FinanceHistory {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Convert(converter = FinanceHistoryHashMapConverter.class)
    HashMap<Finance, LocalDateTime> financeHistoryMap;
    //===========================-Constructors-===============================
    public FinanceHistory() {
        this.financeHistoryMap = new HashMap<>();
    }
    public FinanceHistory(HashMap<Finance, LocalDateTime> financeHistoryMap) {
        this.financeHistoryMap = financeHistoryMap;
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj) {
        // Check if the object references are the same. If they are, return
        // true.
        if (this == obj) return true;
        // Check if the object is an instance of FinanceHistory. If it is,
        // cast it to a FinanceHistory object.
        if (obj instanceof FinanceHistory financeHistory) {
            // Check if the fields of the FinanceHistory objects are equal.
            if (this.id != null) {
                // If the FinanceHistory id is not null, an equality check can
                // be performed using that field.
                return this.id.equals(financeHistory.id);
            } else {
                // If the FinanceHistory id is null, return whether all fields
                // are equal except for the id field.
                return this.financeHistoryMap.equals(financeHistory.financeHistoryMap);
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
    // TODO: Implement the toString method for the FinanceHistory class.
    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
