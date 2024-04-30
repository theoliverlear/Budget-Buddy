package org.budgetbuddy.entity.purchase;
//=================================-Imports-==================================
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.budgetbuddy.convert.entity.category.CategoryConverter;
import org.budgetbuddy.convert.entity.purchase.PurchaseKeyDeserializer;
import org.budgetbuddy.entity.category.Category;

@Entity
@Getter
@Setter
@JsonDeserialize(keyUsing = PurchaseKeyDeserializer.class)
public class Purchase {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String name;
    double price;
    @Convert(converter = CategoryConverter.class)
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
    @Override
    public boolean equals(Object obj) {
        // Check if the object references are the same. If they are, return
        // true.
        if (this == obj) return true;
        // Check if the object is an instance of Purchase. If it is, cast it
        // to a Purchase object.
        if (obj instanceof Purchase purchase) {
            // Check if the fields of the Purchase objects are equal.
            boolean idIsSame = this.id.equals(purchase.id);
            boolean nameIsSame = this.name.equals(purchase.name);
            boolean priceIsSame = this.price == purchase.price;
            boolean categoryIsSame = this.category.equals(purchase.category);
            return idIsSame && nameIsSame && priceIsSame && categoryIsSame;
        }
        // If we have reached this point, the objects are not instances of the
        // same class and are not equal, so we return false.
        return false;
    }
    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------
    @Override
    public String toString() {
        // Return a formatted string with the Purchase name, price, and
        // category.
        return "%s: $%.2f - %s".formatted(this.name, this.price, this.category);
    }
    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
