//=================================-Imports-==================================
package org.budgetbuddy.entity.budget;
import jakarta.persistence.*;
import org.budgetbuddy.model.format.FormattedDate;
import java.util.HashMap;

@Entity
public class BudgetHistory {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Transient // TODO: Create a converter for this, then remove annotation.
    HashMap<Budget, FormattedDate> budgetHistoryMap;
    //===========================-Constructors-===============================
    public BudgetHistory() {
        this.budgetHistoryMap = new HashMap<>();
    }
    public BudgetHistory(HashMap<Budget, FormattedDate> budgetHistoryMap) {
        this.budgetHistoryMap = budgetHistoryMap;
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
