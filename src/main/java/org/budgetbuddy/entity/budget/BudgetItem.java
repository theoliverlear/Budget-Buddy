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

    //=============================-Setters-==================================
}
