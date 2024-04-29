package org.budgetbuddy.entity.expense;
//=================================-Imports-==================================
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
// TODO: Create converters for proper entity mapping
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
        // Add the expense to the list of expenses.
        this.expenses.add(expense);
    }
    //--------------------------Remove-Expense--------------------------------
    public void removeExpense(Expense expense) {
        // Remove the expense from the list of expenses.
        this.expenses.remove(expense);
    }
    //--------------------------Get-Total-Amount------------------------------
    public double getTotalAmount() {
        // Loop through the list of expenses to calculate the total amount.
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
        // Check if the object references are the same. If they are, return
        // true.
        if (this == obj) return true;
        // Check if the object is an instance of Expenses. If it is, cast it
        // to an Expenses object.
        if (obj instanceof Expenses comparedExpenses) {
            // Check if the fields of the Expenses objects are equal.
            return this.id.equals(comparedExpenses.id);
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
    // TODO: Implement a toString method to print a representation of the
    //       Expenses object.
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
