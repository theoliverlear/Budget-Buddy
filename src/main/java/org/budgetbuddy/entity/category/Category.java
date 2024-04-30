package org.budgetbuddy.entity.category;
//=================================-Imports-==================================
import jakarta.persistence.*;

@Entity
public class Category {
    //============================-Variables-=================================
    @Id
    String title;
    //============================-Constants-=================================

    //------------------------------Holdings----------------------------------
    @Transient
    public static final Category CHECKING = new Category("Checking");
    @Transient
    public static final Category SAVINGS = new Category("Savings");
    @Transient
    public static final Category INVESTMENTS = new Category("Investments");
    @Transient
    public static final Category EMERGENCY_FUND = new Category("Emergency Fund");
    @Transient
    public static final Category RETIREMENT = new Category("Retirement");
    @Transient
    public static final Category DEBT = new Category("Debt");
    //------------------------------Expenses----------------------------------
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
    public static final Category PERSONAL_SPENDING = new Category("Personal Spending");
    @Transient
    public static final Category ENTERTAINMENT = new Category("Entertainment");
    @Transient
    public static final Category TAXES = new Category("Taxes");
    //------------------------------Revenues----------------------------------
    @Transient
    public static final Category SALARY = new Category("Salary");
    @Transient
    public static final Category GIFTS = new Category("Gifts");
    @Transient
    public static final Category TAX_REFUND = new Category("Tax Refund");
    //---------------------Both-Expenses-And-Revenue--------------------------
    @Transient
    public static final Category INTEREST = new Category("Interest");
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
        // Check if the object references are the same. If they are, return
        // true.
        if (this == obj) return true;
        // Check if the object is an instance of Category. If it is, cast it
        // to a Category object.
        if (obj instanceof Category category) {
            // Check if the fields of the Category objects are equal.
            return this.title.equals(category.title);
        }
        // If we have reached this point, the objects are not instances of the
        // same class and are not equal, so we return false.
        return false;
    }
    //------------------------------Hash-Code---------------------------------
    @Override
    public int hashCode() {
        // Return the hashcode of the title field.
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
