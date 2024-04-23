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

    public void addSaving(Saving saving, LocalDateTime time) {
        this.savingHistoryMap.put(saving, time);
    }
    public void addSavingNow(Saving saving) {
        this.savingHistoryMap.put(saving, LocalDateTime.now());
    }
    public void removeSaving(Saving saving) {
        this.savingHistoryMap.remove(saving);
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof SavingHistory comparedSavingHistory) {
            if (this.id != null) {
                return this.id.equals(comparedSavingHistory.id);
            } else {
                return this.savingHistoryMap.equals(comparedSavingHistory.savingHistoryMap);
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
