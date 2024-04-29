package org.budgetbuddy.entity.expense;
//=================================-Imports-==================================
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import org.budgetbuddy.convert.entity.category.CategoryConverter;
import org.budgetbuddy.convert.entity.expense.ExpenseKeyDeserializer;
import org.budgetbuddy.entity.category.Category;

@Entity
@JsonDeserialize(keyUsing = ExpenseKeyDeserializer.class)
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
        // Check if the object references are the same. If they are, return
        // true.
        if (this == obj) return true;
        // Check if the object is an instance of Expense. If it is, cast it
        // to an Expense object.
        if (obj instanceof Expense expense) {
            // Check if the fields of the Expense objects are equal.
            if (this.id != null) {
                // If the Expense id is not null, an equality check can be
                // performed using that field.
                return this.id.equals(expense.id);
            } else {
                // If the Expense id is null, return whether all fields are
                // equal except for the id field.
                boolean nameIsSame = this.name.equals(expense.name);
                boolean amountIsSame = this.amount == expense.amount;
                boolean categoryIsSame = this.category.equals(expense.category);
                return nameIsSame && amountIsSame && categoryIsSame;
            }
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
