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

    //-------------------------Build-Pie-Graph--------------------------------
    public PieGraph buildPieGraph(Budget userBudget) {
        // Create a HashMap to store the budget data.
        HashMap<String, Double> budgetData = new HashMap<>();
        // Get the budget items from the user's budget.
        ArrayList<BudgetItem> budgetItems = userBudget.getBudgetItems();
        // Iterate through the budget items and add them to the budget data
        // HashMap.
        for (BudgetItem budgetItem : budgetItems) {
            budgetData.put(budgetItem.getName(), budgetItem.getAmount());
        }
        // Return a new PieGraph object with the budget data.
        return new PieGraph(budgetData);
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
