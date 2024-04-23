package org.budgetbuddy.entity.finance;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.budgetbuddy.convert.entity.finance.FinanceHistoryHashMapConverter;
import org.budgetbuddy.model.format.FormattedTime;

import java.time.LocalDateTime;
import java.util.HashMap;

//=================================-Imports-==================================
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
        if (this == obj) return true;
        if (obj instanceof FinanceHistory financeHistory) {
            if (this.id != null) {
                return this.id.equals(financeHistory.id);
            } else {
                return this.financeHistoryMap.equals(financeHistory.financeHistoryMap);
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
