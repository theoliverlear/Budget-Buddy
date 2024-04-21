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
    public void addTax(Tax tax, LocalDateTime time) {
        this.taxHistoryMap.put(tax, time);
    }
    public void addTaxNow(Tax tax) {
        this.taxHistoryMap.put(tax, LocalDateTime.now());
    }
    public void removeTax(Tax tax) {
        this.taxHistoryMap.remove(tax);
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
