//=================================-Imports-==================================
package org.budgetbuddy.entity.revenue;
import jakarta.persistence.*;
import org.budgetbuddy.convert.entity.revenue.RevenueHistoryHashMapConverter;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
public class RevenueHistory {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Convert(converter = RevenueHistoryHashMapConverter.class)
    HashMap<Revenue, LocalDateTime> revenueHistoryMap;
    //===========================-Constructors-===============================
    public RevenueHistory() {
        this.revenueHistoryMap = new HashMap<>();
    }
    public RevenueHistory(HashMap<Revenue, LocalDateTime> revenueHistoryMap) {
        this.revenueHistoryMap = revenueHistoryMap;
    }
    //=============================-Methods-==================================

    //----------------------------Add-Revenue---------------------------------
    public void addRevenue(Revenue revenue, LocalDateTime dateTime) {
        // Add an item to the revenueHistoryMap with a given date.
        this.revenueHistoryMap.put(revenue,dateTime);
    }
    //----------------------------Add-Revenue-Now-----------------------------
    public void addRevenueNow(Revenue revenue) {
        // Add an item to the revenueHistoryMap with the current date.
        this.revenueHistoryMap.put(revenue,LocalDateTime.now());
    }
    //----------------------------Remove-Revenue-------------------------------
    public void removeRevenue(Revenue revenue) {
        // Remove an item from the revenueHistoryMap.
        this.revenueHistoryMap.remove(revenue);
    }
    //---------------------------Get-Revenue-By-Time--------------------------
    public Revenue getRevenueByTime(LocalDateTime dateTime) {
        // Loop through the revenueHistoryMap to find the Revenue that matches
        // the given date.
        for (Map.Entry<Revenue,LocalDateTime> revenueEntry : this.revenueHistoryMap.entrySet()) {
            LocalDateTime entryDateTime = revenueEntry.getValue();
            // If the date matches, return the Revenue.
            if (dateTime.equals(entryDateTime)) {
                return revenueEntry.getKey();
            }
        }
        // If no Revenue is found, return null.
        return null;
    }
    //-------------------------Get-Date-By-Revenue----------------------------
    public LocalDateTime getDateTimeByRevenue(Revenue revenue) {
        // Return the date associated with a given Revenue.
        return this.revenueHistoryMap.get(revenue);
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj) {
        // Check if the object references are the same. If they are, return
        // true.
        if (this == obj) return true;
        // Check if the object is an instance of RevenueHistory. If it is,
        // cast it to a RevenueHistory object.
        if (obj instanceof RevenueHistory comparedRevenueHistory) {
            // Check if the fields of the RevenueHistory objects are equal.
            return this.id.equals(comparedRevenueHistory.id);
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
    // TODO: Implement the toString method for the RevenueHistory class.
    //=============================-Getters-==================================
    public Long getId() {
        return this.id;
    }
    public HashMap<Revenue, LocalDateTime> getRevenueHistoryMap() {
        return this.revenueHistoryMap;
    }
    //=============================-Setters-==================================
    public void setId(Long id) {
        this.id = id;
    }
    public void setRevenueHistoryMap(HashMap<Revenue, LocalDateTime> revenueHistoryMap) {
        this.revenueHistoryMap = revenueHistoryMap;
    }
}
