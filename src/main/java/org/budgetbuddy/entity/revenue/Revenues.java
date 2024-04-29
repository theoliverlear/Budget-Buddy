package org.budgetbuddy.entity.revenue;
//=================================-Imports-==================================
import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Revenues {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private ArrayList<Revenue> revenues;
    //===========================-Constructors-===============================
    public Revenues() {
        this.revenues = new ArrayList<>();
    }
    public Revenues(ArrayList<Revenue> revenues) {
        this.revenues = revenues;
    }
    //=============================-Methods-==================================

    //-----------------------------Add-Revenue--------------------------------
    public void addRevenue(Revenue revenue) {
        // Add a Revenue to the list of Revenues.
        this.revenues.add(revenue);
    }
    //--------------------------Remove-Revenue--------------------------------
    public void removeRevenue(Revenue revenue) {
        // Remove a Revenue from the list of Revenues.
        this.revenues.remove(revenue);
    }
    //---------------------------Update-Revenue-------------------------------
    public void updateRevenue(Revenue oldRevenue, Revenue newRevenue) {
        // Find the index of the old Revenue in the list of Revenues.
        int index = this.revenues.indexOf(oldRevenue);
        // If the Revenue is found, update it.
        if (index != -1) {
            this.revenues.set(index, newRevenue);
        } else {
            // If the Revenue is not found, print an error message.
            System.out.printf("Revenue: \"%s\" not found.%n", oldRevenue.getName());
        }
    }
    //--------------------------Get-Total-Amount------------------------------
    public double getTotalAmount() {
        // Loop through the list of Revenues and sum the amounts.
        double totalAmount = 0;
        for (Revenue revenue : this.revenues) {
            totalAmount += revenue.getAmount();
        }
        return totalAmount;
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj) {
        // Check if the object references are the same. If they are, return
        // true.
        if (this == obj) return true;
        // Check if the object is an instance of Revenues. If it is, cast it
        // to a Revenues object.
        if (obj instanceof Revenues comparedRevenues) {
            // Check if the fields of the Revenues objects are equal. Return
            // whether the id fields are equal.
            return this.id.equals(comparedRevenues.id);
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
    // TODO: Implement the toString method for the Revenues class.
    //=============================-Getters-==================================
    public Long getId() {
        return this.id;
    }
    public ArrayList<Revenue> getRevenues() {
        return this.revenues;
    }
    //=============================-Setters-==================================
    public void setId(Long id) {
        this.id = id;
    }
    public void setRevenues(ArrayList<Revenue> revenues) {
        this.revenues = revenues;
    }
}
