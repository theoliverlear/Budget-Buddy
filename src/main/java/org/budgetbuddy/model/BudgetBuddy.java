package org.budgetbuddy.model;

import org.budgetbuddy.model.calculator.FinanceCalculator;
import org.budgetbuddy.model.calculator.InterestCalculator;
import org.budgetbuddy.model.graph.Graph;

//=================================-Imports-==================================
public class BudgetBuddy {
    //============================-Variables-=================================
    Graph currentGraph;
    FinanceCalculator financeCalculator;
    InterestCalculator interestCalculator;
    //===========================-Constructors-===============================
    public BudgetBuddy() {
        currentGraph = null;
        this.financeCalculator = new FinanceCalculator();
        this.interestCalculator = new InterestCalculator();
    }
    public BudgetBuddy(Graph currentGraph, FinanceCalculator financeCalculator, InterestCalculator interestCalculator) {
        this.currentGraph = currentGraph;
        this.financeCalculator = financeCalculator;
        this.interestCalculator = interestCalculator;
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
