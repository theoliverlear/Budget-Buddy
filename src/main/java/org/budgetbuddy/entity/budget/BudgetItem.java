package org.budgetbuddy.entity.budget;

import jakarta.persistence.*;
import org.budgetbuddy.convert.entity.category.CategoryConverter;
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
    @Convert(converter = CategoryConverter.class)
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
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof BudgetItem budgetItem) {
            boolean sameId = this.id.equals(budgetItem.id);
            boolean sameName = this.name.equals(budgetItem.name);
            boolean sameAmount = this.amount == budgetItem.amount;
            boolean sameCategory = this.category.equals(budgetItem.category);
            return sameId && sameName && sameAmount && sameCategory;
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
