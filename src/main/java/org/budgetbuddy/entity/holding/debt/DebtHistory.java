package org.budgetbuddy.entity.holding.debt;

import jakarta.persistence.*;
import org.budgetbuddy.convert.entity.debt.DebtHistoryHashMapConverter;
import org.budgetbuddy.model.format.FormattedDate;

import java.time.LocalDateTime;
import java.util.HashMap;

//=================================-Imports-==================================
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
        if (this == obj) return true;
        if (obj instanceof DebtHistory debtHistory) {
            return this.id.equals(debtHistory.id);
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
