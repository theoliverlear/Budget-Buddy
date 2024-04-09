package org.budgetbuddy.entity.category;
//=================================-Imports-==================================
import jakarta.persistence.*;

@Entity
public class Category {
    //============================-Variables-=================================
    @Id
    String title;
    //============================-Constants-=================================
    @Transient
    public static final Category HOUSING = new Category("Housing");
    @Transient
    public static final Category TRANSPORTATION = new Category("Transportation");
    @Transient
    public static final Category FOOD = new Category("Food");
    @Transient
    public static final Category UTILITIES = new Category("Utilities");
    @Transient
    public static final Category HEALTH = new Category("Health");
    @Transient
    public static final Category INSURANCE = new Category("Insurance");
    @Transient
    public static final Category SAVINGS = new Category("Savings");
    @Transient
    public static final Category PERSONAL_SPENDING = new Category("Personal Spending");
    @Transient
    public static final Category ENTERTAINMENT = new Category("Entertainment");
    @Transient
    public static final Category MISCELLANEOUS = new Category("Miscellaneous");
    //===========================-Constructors-===============================
    public Category() {
        this.title = "";
    }
    public Category(String title) {
        this.title = title;
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Category category) {
            return this.title.equals(category.title);
        }
        return false;
    }
    //------------------------------Hash-Code---------------------------------
    @Override
    public int hashCode() {
        return this.title.hashCode();
    }
    //------------------------------To-String---------------------------------
    @Override
    public String toString() {
        return this.title;
    }
    //=============================-Getters-==================================
    public String getTitle() {
        return this.title;
    }
    //=============================-Setters-==================================
    public void setTitle(String title) {
        this.title = title;
    }
}
