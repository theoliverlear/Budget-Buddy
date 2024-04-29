package org.budgetbuddy.entity.holding.saving;
//=================================-Imports-==================================
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import org.budgetbuddy.convert.entity.holding.saving.SavingKeyDeserializer;
import org.budgetbuddy.convert.entity.interest.InterestConverter;
import org.budgetbuddy.entity.interest.Interest;

@Entity
@JsonDeserialize(keyUsing = SavingKeyDeserializer.class)
public class Saving {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    double amount;
    @Convert(converter = InterestConverter.class)
    Interest interest;
    //===========================-Constructors-===============================
    public Saving() {
        this.name = "Unnamed Saving";
        this.amount = 0;
        this.interest = null;
    }
    public Saving(String name, double amount) {
        this.name = name;
        this.amount = amount;
        this.interest = null;
    }
    public Saving(String name, double amount, Interest interest) {
        this.name = name;
        this.amount = amount;
        this.interest = interest;
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj) {
        // Check if the object references are the same. If they are, return
        // true.
        if (this == obj) return true;
        // Check if the object is an instance of Saving. If it is, cast it
        // to a Saving object.
        if (obj instanceof Saving comparedSaving) {
            // Check if the fields of the Saving objects are equal.
            boolean nameIsSame = this.name.equals(comparedSaving.name);
            boolean amountIsSame = this.amount == comparedSaving.amount;
            // Check if the Saving id is not null. If it is, an equality check
            // can be performed using that field.
            if (this.id != null) {
                // Return whether the Saving id is equal to the comparedSaving
                // id.
                return this.id.equals(comparedSaving.id);
            } else if (this.interest != null) {
                // If the Saving id is null and the interest is not null,
                // return whether all fields are equal except for the id
                // field.
                boolean interestIsSame = this.interest.equals(comparedSaving.interest);
                return nameIsSame && amountIsSame && interestIsSame;
            } else {
                // If the Saving id and interest are null, return whether all
                // fields are equal except for the id and interest fields.
                return nameIsSame && amountIsSame;
            }
        }
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
        // If the interest is null, return the name and amount of the Saving.
        if (this.interest == null) {
            return "%s: $%.2f".formatted(this.name, this.amount);
        } else {
            // If the interest is not null, return the name, amount, and
            // interest of the Saving.
            return "%s: $%.2f - %s".formatted(this.name, this.amount, this.interest);
        }
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
    public Interest getInterest() {
        return this.interest;
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
    public void setInterest(Interest interest) {
        this.interest = interest;
    }
}
