package org.budgetbuddy.entity.budget;
//=================================-Imports-==================================
import jakarta.persistence.*;
import org.budgetbuddy.convert.entity.BudgetItemsArrayListConverter;

import java.util.ArrayList;

@Entity
public class Budget {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Convert(converter = BudgetItemsArrayListConverter.class)
    ArrayList<BudgetItem> budgetItems;
    //===========================-Constructors-===============================
    public Budget() {
        this.budgetItems = new ArrayList<>();
    }
    public Budget(ArrayList<BudgetItem> budgetItems) {
        this.budgetItems = budgetItems;
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
