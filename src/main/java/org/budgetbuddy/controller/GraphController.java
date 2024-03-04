package org.budgetbuddy.controller;
//=================================-Imports-==================================
import org.budgetbuddy.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    @RequestMapping("/")
    public String graph() {
        return "graph";
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
