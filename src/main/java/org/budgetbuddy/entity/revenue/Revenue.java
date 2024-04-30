package org.budgetbuddy.entity.revenue;
//=================================-Imports-==================================
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.budgetbuddy.convert.entity.revenue.RevenueKeyDeserializer;

@Entity
@JsonDeserialize(keyUsing = RevenueKeyDeserializer.class)
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
    @Override
    public boolean equals(Object obj) {
        // Check if the object references are the same. If they are, return
        // true.
        if (this == obj) return true;
        // Check if the object is an instance of Revenue. If it is, cast it
        // to a Revenue object.
        if (obj instanceof Revenue comparedRevenue) {
            // Check if the fields of the Revenue objects are equal.
            if (this.id != null) {
                // If the Revenue id is not null, an equality check can be
                // performed using that field.
                return this.id.equals(comparedRevenue.id);
            } else {
                // If the Revenue id is null, return whether all fields are
                // equal except for the id field.
                boolean nameIsSame = this.name.equals(comparedRevenue.name);
                boolean amountIsSame = this.amount == comparedRevenue.amount;
                return nameIsSame && amountIsSame;
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
        // Return a string representation of the Revenue name and amount.
        return "%s: $%.2f".formatted(this.name, this.amount);
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
}
