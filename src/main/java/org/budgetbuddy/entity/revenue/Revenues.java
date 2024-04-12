package org.budgetbuddy.entity.revenue;

import jakarta.persistence.*;

import java.util.ArrayList;

//=================================-Imports-==================================
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
        this.revenues.add(revenue);
    }
    //--------------------------Remove-Revenue--------------------------------
    public void removeRevenue(Revenue revenue) {
        this.revenues.remove(revenue);
    }
    //---------------------------Update-Revenue-------------------------------
    public void updateRevenue(Revenue oldRevenue, Revenue newRevenue) {
        int index = this.revenues.indexOf(oldRevenue);
        if (index != -1) {
            this.revenues.set(index, newRevenue);
        } else {
            System.out.printf("Revenue: \"%s\" not found.%n", oldRevenue.getName());
        }
    }
    //--------------------------Get-Total-Amount------------------------------
    public double getTotalAmount() {
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
        if (this == obj) return true;
        if (obj instanceof Revenues comparedRevenues) {
            return this.id.equals(comparedRevenues.id);
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
