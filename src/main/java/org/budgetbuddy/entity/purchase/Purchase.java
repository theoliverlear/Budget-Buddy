package org.budgetbuddy.entity.purchase;
//=================================-Imports-==================================
import jakarta.persistence.*;
import org.budgetbuddy.entity.category.Category;

@Entity
public class Purchase {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String name;
    double price;
    @Transient // TODO: Create a converter for this, then remove annotation.
    Category category;
    //===========================-Constructors-===============================
    public Purchase() {
        this.name = "";
        this.price = 0;
    }
    public Purchase(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public Purchase(String name, double price, Category category) {
        this.name = name;
        this.price = price;
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
