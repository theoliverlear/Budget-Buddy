package org.budgetbuddy.entity.holding.saving;
//============================-Variables-=================================
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.budgetbuddy.convert.entity.holding.saving.SavingHistoryHashMapConverter;
import org.budgetbuddy.model.format.FormattedDate;

import java.time.LocalDateTime;
import java.util.HashMap;

@Entity
@Getter
@Setter
public class SavingHistory {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Convert(converter = SavingHistoryHashMapConverter.class)
    HashMap<Saving, LocalDateTime> savingHistoryMap;
    //===========================-Constructors-===============================
    public SavingHistory() {
        this.savingHistoryMap = new HashMap<>();
    }
    public SavingHistory(HashMap<Saving, LocalDateTime> savingHistoryMap) {
        this.savingHistoryMap = savingHistoryMap;
    }
    //=============================-Methods-==================================

    //-----------------------------Add-Saving---------------------------------
    public void addSaving(Saving saving, LocalDateTime time) {
        // Add an item to the savingHistoryMap with a given date.
        this.savingHistoryMap.put(saving, time);
    }
    //---------------------------Add-Saving-Now-------------------------------
    public void addSavingNow(Saving saving) {
        // Add an item to the savingHistoryMap with the current date.
        this.savingHistoryMap.put(saving, LocalDateTime.now());
    }
    //---------------------------Remove-Saving--------------------------------
    public void removeSaving(Saving saving) {
        // Remove an item from the savingHistoryMap.
        this.savingHistoryMap.remove(saving);
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj) {
        // Check if the object references are the same. If they are, return
        // true.
        if (this == obj) return true;
        // Check if the object is an instance of SavingHistory. If it is,
        // cast it to a SavingHistory object.
        if (obj instanceof SavingHistory comparedSavingHistory) {
            // Check if the fields of the SavingHistory objects are equal.
            if (this.id != null) {
                // If the SavingHistory id is not null, an equality check can
                // be performed using that field.
                return this.id.equals(comparedSavingHistory.id);
            } else {
                // If the SavingHistory id is null, return whether all fields
                // are equal except for the id field.
                return this.savingHistoryMap.equals(comparedSavingHistory.savingHistoryMap);
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
    // TODO: Implement the toString method for the SavingHistory class.
    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
