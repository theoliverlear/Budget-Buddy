package org.budgetbuddy.entity.budget;
//=================================-Imports-==================================
import jakarta.persistence.*;
import org.budgetbuddy.convert.entity.category.CategoryConverter;
import org.budgetbuddy.entity.category.Category;

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
        // Check if the object references are the same. If they are, return
        // true.
        if (this == obj) return true;
        // Check if the object is an instance of BudgetItem. If it is, cast it
        // to a BudgetItem object.
        if (obj instanceof BudgetItem budgetItem) {
            // Check if the fields of the BudgetItem objects are equal.
            boolean sameName = this.name.equals(budgetItem.name);
            boolean sameAmount = this.amount == budgetItem.amount;
            boolean sameCategory = this.category.equals(budgetItem.category);
            // If the BudgetItem id is not null, an equality check can be
            // performed using that field.
            if (this.id != null) {
                // Return whether all fields are equal.
                boolean sameId = this.id.equals(budgetItem.id);
                return sameId && sameName && sameAmount && sameCategory;
            } else {
                // If the BudgetItem id is null, return whether all fields are
                // equal except for the id field.
                return sameName && sameAmount && sameCategory;
            }
        }
        // If we have reached this point, the objects are not instances of the
        // same class and are not equal, so we return false.
        return false;
    }
    //------------------------------Hash-Code---------------------------------
    @Override
    public int hashCode() {
        // If the BudgetItem id is not null, return the hashcode of the id
        // field.
        if (this.id != null) {
            return this.id.hashCode();
        } else {
            // If the BudgetItem id is null, return the hashcode of the name,
            // amount, and category fields combined.
            int combinedHashCode = this.name.hashCode();
            combinedHashCode += Double.hashCode(this.amount);
            combinedHashCode += this.category.hashCode();
            return combinedHashCode;
        }
    }
    //------------------------------To-String---------------------------------
    @Override
    public String toString() {
        return this.name + " - " + this.amount + " - " + this.category;
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
