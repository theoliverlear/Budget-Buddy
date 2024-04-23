package org.budgetbuddy.entity.tax;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.budgetbuddy.convert.entity.tax.TaxHistoryHashMapConverter;
import org.budgetbuddy.model.format.FormattedDate;

import java.time.LocalDateTime;
import java.util.HashMap;

//=================================-Imports-==================================
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
        this.taxHistoryMap.put(tax, time);
    }
    //---------------------------Add-Tax-Now----------------------------------
    public void addTaxNow(Tax tax) {
        this.taxHistoryMap.put(tax, LocalDateTime.now());
    }
    //-------------------------Remove-Tax-------------------------------------
    public void removeTax(Tax tax) {
        this.taxHistoryMap.remove(tax);
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof TaxHistory comparedTaxHistory) {
            if (this.id != null) {
                return this.id.equals(comparedTaxHistory.id);
            } else {
                return this.taxHistoryMap.equals(comparedTaxHistory.taxHistoryMap);
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

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
