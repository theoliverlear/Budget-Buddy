package org.budgetbuddy.entity.category;
//=================================-Imports-==================================
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Category {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    //============================-Constants-=================================
    // TODO: Implement values for each category
    public static final Category HOUSING = new Category("Housing");
    public static final Category TRANSPORTATION = new Category("Transportation");
    public static final Category FOOD = new Category("Food");
    public static final Category UTILITIES = new Category("Utilities");
    public static final Category HEALTH = new Category("Health");
    public static final Category INSURANCE = new Category("Insurance");
    public static final Category SAVINGS = new Category("Savings");
    public static final Category PERSONAL_SPENDING = new Category("Personal Spending");
    public static final Category ENTERTAINMENT = new Category("Entertainment");
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

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================
    public Long getId() {
        return this.id;
    }
    public String getTitle() {
        return this.title;
    }
    //=============================-Setters-==================================
    public void setId(Long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
