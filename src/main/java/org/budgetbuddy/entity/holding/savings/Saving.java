package org.budgetbuddy.entity.holding.savings;
//=================================-Imports-==================================
import jakarta.persistence.*;
import org.budgetbuddy.entity.interest.Interest;

import java.util.Optional;

@Entity
public class Saving {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    double amount;
    @Transient // TODO: Create a converter for this, then remove annotation.
    Optional<Interest> interest;
    //===========================-Constructors-===============================
    public Saving() {
        this.name = "Unnamed Saving";
        this.amount = 0;
        this.interest = Optional.empty();
    }
    public Saving(String name, double amount) {
        this.name = name;
        this.amount = amount;
        this.interest = Optional.empty();
    }
    public Saving(String name, double amount, Interest interest) {
        this.name = name;
        this.amount = amount;
        this.interest = Optional.of(interest);
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Saving comparedSaving) {
            return this.id.equals(comparedSaving.id);
        }
        return false;
    }
    //------------------------------Hash-Code---------------------------------
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
    //------------------------------To-String---------------------------------
    @Override
    public String toString() {
        if (this.interest.isEmpty()) {
            return "%s: $%.2f".formatted(this.name, this.amount);
        } else {
            return "%s: $%.2f - %s".formatted(this.name, this.amount, this.interest.get());
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
    public Optional<Interest> getInterest() {
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
        this.interest = Optional.of(interest);
    }
}
