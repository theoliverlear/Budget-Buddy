package org.budgetbuddy.entity.finance;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.budgetbuddy.convert.entity.finance.FinanceHistoryHashMapConverter;
import org.budgetbuddy.model.format.FormattedTime;

import java.time.LocalDateTime;
import java.util.HashMap;

//=================================-Imports-==================================
@Entity
@Getter
@Setter
public class FinanceHistory {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Convert(converter = FinanceHistoryHashMapConverter.class)
    HashMap<Finance, LocalDateTime> financeHistoryMap;
    //===========================-Constructors-===============================
    public FinanceHistory() {
        this.financeHistoryMap = new HashMap<>();
    }
    public FinanceHistory(HashMap<Finance, LocalDateTime> financeHistoryMap) {
        this.financeHistoryMap = financeHistoryMap;
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
