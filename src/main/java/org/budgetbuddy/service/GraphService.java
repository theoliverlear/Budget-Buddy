package org.budgetbuddy.service;
//=================================-Imports-==================================
import org.budgetbuddy.entity.budget.Budget;
import org.budgetbuddy.entity.budget.BudgetItem;
import org.budgetbuddy.model.graph.PieGraph;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class GraphService {
    //============================-Variables-=================================

    //===========================-Constructors-===============================

    //=============================-Methods-==================================
    public PieGraph buildPieGraph(Budget userBudget) {
        HashMap<String, Double> budgetData = new HashMap<>();
        ArrayList<BudgetItem> budgetItems = userBudget.getBudgetItems();
        for (BudgetItem budgetItem : budgetItems) {
            budgetData.put(budgetItem.getName(), budgetItem.getAmount());
        }
        return new PieGraph(budgetData);
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
