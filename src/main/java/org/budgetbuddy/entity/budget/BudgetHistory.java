//=================================-Imports-==================================
package org.budgetbuddy.entity.budget;
import jakarta.persistence.*;
import org.budgetbuddy.model.format.FormattedDate;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
public class BudgetHistory {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Transient // TODO: Create a converter for this, then remove annotation.
    HashMap<Budget, LocalDateTime> budgetHistoryMap;
    //===========================-Constructors-===============================
    public BudgetHistory() {
        this.budgetHistoryMap = new HashMap<>();
    }
    public BudgetHistory(HashMap<Budget, LocalDateTime> budgetHistoryMap) {
        this.budgetHistoryMap = budgetHistoryMap;
    }
    //=============================-Methods-==================================

    //-----------------------------Add-Budget---------------------------------
    public void addBudget(Budget budget, LocalDateTime dateTime){
        this.budgetHistoryMap.put(budget,dateTime);
    }
    //---------------------------Add-Budget-Now-------------------------------
    public void addBudgetNow(Budget budget){
        this.budgetHistoryMap.put(budget, LocalDateTime.now());
    }
    //---------------------------Remove-Budget--------------------------------
    public void removeBudget(Budget budget){
        this.budgetHistoryMap.remove(budget);
    }
    //-------------------------Get-Budget-By-Time-----------------------------
    public Budget getBudgetByTime(LocalDateTime dateTime ){
        for(Map.Entry<Budget,LocalDateTime> budgetEntry : this.budgetHistoryMap.entrySet() ){
            LocalDateTime entryDateTime = budgetEntry.getValue();
            if (dateTime.equals(entryDateTime)){
                return budgetEntry.getKey();
            }
        }
        return null;
    }
    //-------------------------Get-Date-By-Budget-----------------------------
    public LocalDateTime getDateTimeByBudget(Budget budget){
        return this.budgetHistoryMap.get(budget);
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj instanceof BudgetHistory comparedBudgetHistory){
            return this.id.equals(comparedBudgetHistory.id);
        }
        return false;
    }
    //------------------------------Hash-Code---------------------------------
    @Override
    public int hashCode(){
        return this.id.hashCode();
    }
    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================
    public Long getId(){
        return this.id;
    }
    public HashMap<Budget, LocalDateTime> getBudgetHistoryMap(){
        return this.budgetHistoryMap;
    }
    //=============================-Setters-==================================
}
