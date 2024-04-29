//=================================-Imports-==================================
package org.budgetbuddy.entity.budget;
import jakarta.persistence.*;
import org.budgetbuddy.convert.entity.budget.BudgetHistoryHashMapConverter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
public class BudgetHistory {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Convert(converter = BudgetHistoryHashMapConverter.class)
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
    public void addBudget(Budget budget, LocalDateTime dateTime) {
        // Add an item to the budgetHistoryMap with a given date.
        this.budgetHistoryMap.put(budget,dateTime);
    }
    //---------------------------Add-Budget-Now-------------------------------
    public void addBudgetNow(Budget budget) {
        // Add an item to the budgetHistoryMap with the current date.
        this.budgetHistoryMap.put(budget, LocalDateTime.now());
    }
    //---------------------------Remove-Budget--------------------------------
    public void removeBudget(Budget budget) {
        // Remove an item from the budgetHistoryMap.
        this.budgetHistoryMap.remove(budget);
    }
    //-------------------------Get-Budget-By-Time-----------------------------
    public Budget getBudgetByTime(LocalDateTime dateTime ) {
        // Loop through the budgetHistoryMap to find the Budget that matches
        // the given date.
        for (Map.Entry<Budget, LocalDateTime> budgetEntry : this.budgetHistoryMap.entrySet() ){
            LocalDateTime entryDateTime = budgetEntry.getValue();
            // If the date matches, return the Budget.
            if (dateTime.equals(entryDateTime)) {
                return budgetEntry.getKey();
            }
        }
        // If no Budget is found, return null.
        return null;
    }
    //-------------------------Get-Date-By-Budget-----------------------------
    public LocalDateTime getDateTimeByBudget(Budget budget) {
        // Return the date associated with a given Budget.
        return this.budgetHistoryMap.get(budget);
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj) {
        // Check if the object references are the same. If they are, return
        // true.
        if (this == obj) return true;
        // Check if the object is an instance of BudgetHistory. If it is,
        // cast it to a BudgetHistory object.
        if (obj instanceof BudgetHistory comparedBudgetHistory) {
            // If id is not null, an equality check can be performed using
            // that field.
            if (this.id != null) {
                // Return whether all fields are equal.
                return this.id.equals(comparedBudgetHistory.id);
            } else {
                // If id is null, return whether all fields are equal except
                // for the id field -- in this case, the budgetHistoryMap
                // items.
                return this.budgetHistoryMap.equals(comparedBudgetHistory.budgetHistoryMap);
            }
        }
        return false;
    }
    //------------------------------Hash-Code---------------------------------
    @Override
    public int hashCode() {
        // Return the hashcode of the id field.
        return this.id.hashCode();
    }
    //------------------------------To-String---------------------------------
    // TODO: Implement the toString method to print a representation of the
    //       BudgetHistory object.
    //=============================-Getters-==================================
    public Long getId(){
        return this.id;
    }
    public HashMap<Budget, LocalDateTime> getBudgetHistoryMap(){
        return this.budgetHistoryMap;
    }
    //=============================-Setters-==================================
    public void setId(Long id) {
        this.id = id;
    }
    public void setBudgetHistoryMap(HashMap<Budget, LocalDateTime> budgetHistoryMap) {
        this.budgetHistoryMap = budgetHistoryMap;
    }
}
