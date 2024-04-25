package org.budgetbuddy.entity.holding.saving;
//=================================-Imports-==================================
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import org.budgetbuddy.convert.entity.holding.saving.SavingKeyDeserializer;
import org.budgetbuddy.convert.entity.interest.InterestConverter;
import org.budgetbuddy.convert.entity.interest.OptionalInterestConverter;
import org.budgetbuddy.entity.interest.Interest;

import java.util.Optional;

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
        if (this == obj) return true;
        if (obj instanceof Saving comparedSaving) {
            boolean nameIsSame = this.name.equals(comparedSaving.name);
            boolean amountIsSame = this.amount == comparedSaving.amount;
            if (this.id != null) {
                return this.id.equals(comparedSaving.id);
            } else if (this.interest != null) {
                boolean interestIsSame = this.interest.equals(comparedSaving.interest);
                return nameIsSame && amountIsSame && interestIsSame;
            } else {
                return nameIsSame && amountIsSame;
            }
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
        if (this.interest == null) {
            return "%s: $%.2f".formatted(this.name, this.amount);
        } else {
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
