package org.budgetbuddy.entity.expense;
//=================================-Imports-==================================
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;

public class Expenses {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ArrayList<Expense> expenses;
    //===========================-Constructors-===============================
    public Expenses() {
        this.expenses = new ArrayList<>();
    }
    public Expenses(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }
    //=============================-Methods-==================================

    //-----------------------------Add-Expense--------------------------------
    public void addExpense(Expense expense) {
        this.expenses.add(expense);
    }
    //--------------------------Remove-Expense--------------------------------
    public void removeExpense(Expense expense) {
        this.expenses.remove(expense);
    }
    //--------------------------Get-Total-Amount------------------------------
    public double getTotalAmount() {
        double totalAmount = 0;
        for (Expense expense : this.expenses) {
            totalAmount += expense.getAmount();
        }
        return totalAmount;
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Expenses comparedExpenses) {
            return this.id.equals(comparedExpenses.id);
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
    public ArrayList<Expense> getExpenses() {
        return this.expenses;
    }
    //=============================-Setters-==================================
    public void setId(Long id) {
        this.id = id;
    }
    public void setExpenses(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }
}
