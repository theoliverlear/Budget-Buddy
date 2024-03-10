package org.budgetbuddy.entity.budget;

import jakarta.persistence.*;
import org.budgetbuddy.entity.category.Category;

//=================================-Imports-==================================
@Entity
public class BudgetItem {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    double amount;
    @Transient // TODO: Create a converter for this, then remove annotation.
    Category category;
    //===========================-Constructors-===============================
    public BudgetItem() {
        this.name = "";
        this.amount = 0;
    }
    public BudgetItem(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }
    public BudgetItem(String name, double amount, Category category) {
        this.name = name;
        this.amount = amount;
        this.category = category;
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

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
