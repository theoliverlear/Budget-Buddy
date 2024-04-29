package org.budgetbuddy.entity.expense;
//=================================-Imports-==================================
import jakarta.persistence.*;
import org.budgetbuddy.convert.entity.expense.ExpenseHistoryHashMapConverter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
public class ExpenseHistory {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Convert(converter = ExpenseHistoryHashMapConverter.class)
    HashMap<Expense, LocalDateTime> expenseHistoryMap;
    //===========================-Constructors-===============================
    public ExpenseHistory() {
        this.expenseHistoryMap = new HashMap<>();
    }
    public ExpenseHistory(HashMap<Expense, LocalDateTime> expenseHistoryMap) {
        this.expenseHistoryMap = expenseHistoryMap;
    }
    //=============================-Methods-==================================

    //----------------------------Add-Expense---------------------------------
    public void addExpense(Expense expense, LocalDateTime dateTime) {
        // Add an item to the expenseHistoryMap with a given date.
        this.expenseHistoryMap.put(expense, dateTime);
    }
    //--------------------------Add-Expense-Now-------------------------------
    public void addExpenseNow(Expense expense) {
        // Add an item to the expenseHistoryMap with the current date.
        this.expenseHistoryMap.put(expense, LocalDateTime.now());
    }
    //----------------------------Remove-Expense------------------------------
    public void removeExpense(Expense expense) {
        // Remove an item from the expenseHistoryMap.
        this.expenseHistoryMap.remove(expense);
    }
    //------------------------Get-Date-By-Expense-----------------------------
    public LocalDateTime getDateTimeByExpense(Expense expense) {
        // Return the date associated with a given Expense.
        return this.expenseHistoryMap.get(expense);
    }
    //------------------------Get-Expense-By-Date-----------------------------
    public Expense getExpenseByDate(LocalDateTime dateTime) {
        // Loop through the expenseHistoryMap to find the Expense that matches
        // the given date.
        for (Map.Entry<Expense, LocalDateTime> expenseEntry : this.expenseHistoryMap.entrySet()) {
            LocalDateTime entryDateTime = expenseEntry.getValue();
            // If the date matches, return the Expense.
            if (dateTime.equals(entryDateTime)) {
                return expenseEntry.getKey();
            }
        }
        // If no Expense is found, we return null.
        return null;
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj) {
        // Check if the object references are the same. If they are, return
        // true.
        if (this == obj) return true;
        // Check if the object is an instance of ExpenseHistory. If it is,
        // cast it to an ExpenseHistory object.
        if (obj instanceof ExpenseHistory comparedExpenseHistory) {
            // Return whether the id fields are equal.
            return this.id.equals(comparedExpenseHistory.id);
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
    // TODO: Implement the toString method to print a representation of the
    //       ExpenseHistory object.
    //=============================-Getters-==================================
    public Long getId() {
        return this.id;
    }
    public HashMap<Expense, LocalDateTime> getExpenseHistoryMap() {
        return this.expenseHistoryMap;
    }
    //=============================-Setters-==================================
    public void setId(Long id) {
        this.id = id;
    }
    public void setExpenseHistoryMap(HashMap<Expense, LocalDateTime> expenseHistoryMap) {
        this.expenseHistoryMap = expenseHistoryMap;
    }
}
