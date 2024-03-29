package org.budgetbuddy.controller;
//=================================-Imports-==================================
import org.budgetbuddy.entity.budget.Budget;
import org.budgetbuddy.model.graph.PieGraph;
import org.budgetbuddy.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/graph")
public class GraphController extends BudgetBuddyController {
    //============================-Variables-=================================
    @Autowired
    GraphService graphService;
    //===========================-Constructors-===============================

    //=============================-Methods-==================================

    //-------------------------------Graph------------------------------------
    @RequestMapping("/view")
    public String graph() {
        return "graph";
    }
    //-----------------------------Pie-Graph----------------------------------
    @RequestMapping("/view/pie/{dataType}")
    public ResponseEntity<PieGraph> pieGraph(@PathVariable String dataType) {
        if (this.getCurrentUser() == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        // TODO: Generalize this method to work with all kinds of user's data.
        // Let's say the user wants to see their budget breakdown in a pie
        // graph.
        Long userId = this.getCurrentUser().getId();
        Budget userBudget = this.userService.getUserRepository().getBudgetById(userId);
        PieGraph pieGraph = this.graphService.buildPieGraph(userBudget);
        return new ResponseEntity<>(pieGraph, HttpStatus.OK);
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
