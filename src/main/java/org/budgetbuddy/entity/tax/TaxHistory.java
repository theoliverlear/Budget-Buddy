package org.budgetbuddy.entity.tax;
//=================================-Imports-==================================
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.budgetbuddy.convert.entity.tax.TaxHistoryHashMapConverter;

import java.time.LocalDateTime;
import java.util.HashMap;

@Entity
@Getter
@Setter
public class TaxHistory {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Convert(converter = TaxHistoryHashMapConverter.class)
    HashMap<Tax, LocalDateTime> taxHistoryMap;
    //===========================-Constructors-===============================
    public TaxHistory() {
        this.taxHistoryMap = new HashMap<>();
    }
    public TaxHistory(HashMap<Tax, LocalDateTime> taxHistoryMap) {
        this.taxHistoryMap = taxHistoryMap;
    }
    //=============================-Methods-==================================

    //------------------------------Add-Tax-----------------------------------
    public void addTax(Tax tax, LocalDateTime time) {
        // Add an item to the taxHistoryMap with a given date.
        this.taxHistoryMap.put(tax, time);
    }
    //---------------------------Add-Tax-Now----------------------------------
    public void addTaxNow(Tax tax) {
        // Add an item to the taxHistoryMap with the current date.
        this.taxHistoryMap.put(tax, LocalDateTime.now());
    }
    //-------------------------Remove-Tax-------------------------------------
    public void removeTax(Tax tax) {
        // Remove an item from the taxHistoryMap.
        this.taxHistoryMap.remove(tax);
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj) {
        // Check if the object references are the same. If they are, return
        // true.
        if (this == obj) return true;
        // Check if the object is an instance of TaxHistory. If it is, cast it
        // to a TaxHistory object.
        if (obj instanceof TaxHistory comparedTaxHistory) {
            // Check if the fields of the TaxHistory objects are equal.
            if (this.id != null) {
                // If the TaxHistory id is not null, an equality check
                // can be performed using that field.
                return this.id.equals(comparedTaxHistory.id);
            } else {
                // If the TaxHistory id is null, return whether all fields are
                // equal except for the id field.
                return this.taxHistoryMap.equals(comparedTaxHistory.taxHistoryMap);
            }
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
    // TODO: Implement the toString method for the TaxHistory class.
    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
