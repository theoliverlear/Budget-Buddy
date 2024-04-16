package org.budgetbuddy.entity.holding.savings;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;

public class Savings {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    ArrayList<Saving> savings;
    //===========================-Constructors-===============================
    public Savings() {
        this.savings = new ArrayList<>();
    }
    public Savings(ArrayList<Saving> savings) {
        this.savings = savings;
    }
    //=============================-Methods-==================================

    //-----------------------------Add-Saving---------------------------------
    public void addSaving(Saving saving) {
        this.savings.add(saving);
    }
    //---------------------------Remove-Saving--------------------------------
    public void removeSaving(Saving saving) {
        this.savings.remove(saving);
    }
    //---------------------------Update-Saving--------------------------------
    public void updateSaving(Saving saving) {
        int indexOfSaving = this.savings.indexOf(saving);
        if (indexOfSaving != -1) {
            this.savings.set(indexOfSaving, saving);
        } else {
            System.out.printf("Saving \"%s\" not found.%n", saving.getName());
        }
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Savings comparedSavings) {
            return this.id.equals(comparedSavings.id);
        }
        return false;
    }
    //------------------------------Hash-Code---------------------------------
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================
    public Long getId() {
        return this.id;
    }
    public ArrayList<Saving> getSavings() {
        return this.savings;
    }
    //=============================-Setters-==================================
    public void setId(Long id) {
        this.id = id;
    }
    public void setSavings(ArrayList<Saving> savings) {
        this.savings = savings;
    }
}
