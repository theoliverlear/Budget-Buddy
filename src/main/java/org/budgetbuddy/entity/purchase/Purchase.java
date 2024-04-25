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
        if (this == obj) return true;
        if (obj instanceof Purchase purchase) {
            boolean idIsSame = this.id.equals(purchase.id);
            boolean nameIsSame = this.name.equals(purchase.name);
            boolean priceIsSame = this.price == purchase.price;
            boolean categoryIsSame = this.category.equals(purchase.category);
            return idIsSame && nameIsSame && priceIsSame && categoryIsSame;
        }
        return false;
    }
    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------
    @Override
    public String toString() {
        return "%s: $%.2f - %s".formatted(this.name, this.price, this.category);
    }
    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
