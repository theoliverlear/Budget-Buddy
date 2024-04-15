//=================================-Imports-==================================
package org.budgetbuddy.entity.revenue;
import jakarta.persistence.*;


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
    @Transient // TODO: Create a converter for this, then remove annotation.
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
    public void addRevenue(Revenue revenue, LocalDateTime dateTime){
        this.revenueHistoryMap.put(revenue,dateTime);
    }
    //----------------------------Add-Revenue-Now-----------------------------
    public void addRevenueNow(Revenue revenue){
        this.revenueHistoryMap.put(revenue,LocalDateTime.now());
    }
    //----------------------------Remove-Revenue-------------------------------
    public void removeRevenue(Revenue revenue){
        this.revenueHistoryMap.remove(revenue);
    }
    //---------------------------Get-Revenue-By-Time--------------------------
    public Revenue getRevenueByTime(LocalDateTime dateTime){
        for(Map.Entry<Revenue,LocalDateTime> revenueEntry : this.revenueHistoryMap.entrySet() ){
            LocalDateTime entryDateTime = revenueEntry.getValue();
            if (dateTime.equals(entryDateTime)){
                return revenueEntry.getKey();
            }
        }
        return null;
    }
    //-------------------------Get-Date-By-Revenue----------------------------
    public LocalDateTime getDateTimeByRevenue(Revenue revenue){
        return this.revenueHistoryMap.get(revenue);
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj instanceof RevenueHistory comparedRevenueHistory){
            return this.id.equals(comparedRevenueHistory.id);
        }
        return false;
    }
    //------------------------------Hash-Code---------------------------------
    @Override
    public int hashCode(){
        return this.id.hashCode();
    }
    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================
    public Long getId(){
        return this.id;
    }
    public HashMap<Revenue, LocalDateTime> getRevenueHistoryMap(){
        return this.revenueHistoryMap;
    }
    //=============================-Setters-==================================
}
