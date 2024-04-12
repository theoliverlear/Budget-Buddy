//=================================-Imports-==================================
package org.budgetbuddy.entity.expense;

import jakarta.persistence.*;
import org.budgetbuddy.convert.entity.CategoryConverter;
import org.budgetbuddy.entity.category.Category;

@Entity
public class Expense {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    double amount;
    @Convert(converter = CategoryConverter.class)
    Category category;
    //===========================-Constructors-===============================
    public Expense() {
        this.name = "Unknown";
        this.amount = 0;
        this.category = Category.MISCELLANEOUS;
    }
    public Expense(String name, double amount) {
        this.name = name;
        this.amount = amount;
        this.category = Category.MISCELLANEOUS;
    }
    public Expense(String name, double amount, Category category) {
        this.name = name;
        this.amount = amount;
        this.category = category;
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Expense expense) {
            return this.id.equals(expense.id);
        }
        return false;
    }
    //------------------------------Hash-Code---------------------------------
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
    //------------------------------To-String---------------------------------
    @Override
    public String toString() {
        return "%s: $%.2f - %s".formatted(this.name, this.amount, this.category);
    }
    //=============================-Getters-==================================
    public Long getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public double getAmount() {
        return this.amount;
    }
    public Category getCategory() {
        return this.category;
    }
    //=============================-Setters-==================================
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
}
