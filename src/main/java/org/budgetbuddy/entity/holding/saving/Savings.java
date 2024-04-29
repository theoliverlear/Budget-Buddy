package org.budgetbuddy.entity.holding.saving;

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
        // Add a Saving to the list of Savings.
        this.savings.add(saving);
    }
    //---------------------------Remove-Saving--------------------------------
    public void removeSaving(Saving saving) {
        // Remove a Saving from the list of Savings.
        this.savings.remove(saving);
    }
    //---------------------------Update-Saving--------------------------------
    public void updateSaving(Saving saving) {
        // Find the index of the Saving in the list of Savings.
        int indexOfSaving = this.savings.indexOf(saving);
        // If the Saving is found, update it.
        if (indexOfSaving != -1) {
            this.savings.set(indexOfSaving, saving);
        } else {
            // If the Saving is not found, print an error message.
            System.out.printf("Saving \"%s\" not found.%n", saving.getName());
        }
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj) {
        // Check if the object references are the same. If they are, return
        // true.
        if (this == obj) return true;
        // Check if the object is an instance of Savings. If it is, cast it
        // to a Savings object.
        if (obj instanceof Savings comparedSavings) {
            // Check if the fields of the Savings objects are equal. Return
            // whether the id fields are equal.
            return this.id.equals(comparedSavings.id);
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
