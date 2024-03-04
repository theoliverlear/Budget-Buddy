package org.budgetbuddy.entity.revenue;
//=================================-Imports-==================================
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Revenue {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    double amount;
    //===========================-Constructors-===============================
    public Revenue() {
        this.name = "Unnamed Revenue";
        this.amount = 0;
    }
    public Revenue(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
