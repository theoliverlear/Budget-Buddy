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

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
