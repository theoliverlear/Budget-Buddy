package org.budgetbuddy.entity.expense;
//=================================-Imports-==================================
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
public class ExpenseHistory {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    Long id;
    @Transient // TODO: Create a converter for this, then remove annotation.
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
    public void addExpense(Expense expense, LocalDateTime dateTime){
        this.expenseHistoryMap.put(expense, dateTime);
    }
    //--------------------------Add-Expense-Now-------------------------------
    public void addExpenseNow(Expense expense){
        this.expenseHistoryMap.put(expense, LocalDateTime.now());
    }
    //----------------------------Remove-Expense------------------------------
    public void removeExpense(Expense expense){
        this.expenseHistoryMap.remove(expense);
    }
    //------------------------Get-Date-By-Expense-----------------------------
    public LocalDateTime getDateTimeByExpense(Expense expense){
        return this.expenseHistoryMap.get(expense);
    }
    //------------------------Get-Expense-By-Date-----------------------------
    public Expense getExpenseByDate(LocalDateTime dateTime){
        for (Map.Entry<Expense, LocalDateTime> expenseEntry : this.expenseHistoryMap.entrySet()){
            LocalDateTime entryDateTime = expenseEntry.getValue();
            if (dateTime.equals(entryDateTime)){
                return expenseEntry.getKey();
            }
        }
        return null;
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof ExpenseHistory comparedExpenseHistory) {
            return this.id.equals(comparedExpenseHistory.id);
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
