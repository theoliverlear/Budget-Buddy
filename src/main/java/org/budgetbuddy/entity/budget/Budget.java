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

    //--------------------------Add-Budget-Items------------------------------
    public void addBudgetItem(BudgetItem budgetItem) {
        this.budgetItems.add(budgetItem);
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================
    public Long getId() {
        return this.id;
    }
    public ArrayList<BudgetItem> getBudgetItems() {
        return this.budgetItems;
    }
    //=============================-Setters-==================================
    public void setId(Long id) {
        this.id = id;
    }
    public void setBudgetItems(ArrayList<BudgetItem> budgetItems) {
        this.budgetItems = budgetItems;
    }
}
