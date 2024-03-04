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
    //============================-Constants-=================================
    // TODO: Implement values for each category
    public static final Category HOUSING = null;
    public static final Category TRANSPORTATION = null;
    public static final Category FOOD = null;
    public static final Category UTILITIES = null;
    public static final Category HEALTH = null;
    public static final Category INSURANCE = null;
    public static final Category SAVINGS = null;
    public static final Category PERSONAL_SPENDING = null;
    public static final Category ENTERTAINMENT = null;
    public static final Category MISCELLANEOUS = null;
    //===========================-Constructors-===============================

    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================

}
