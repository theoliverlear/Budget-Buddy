package org.budgetbuddy.controller;
//=================================-Imports-==================================
import org.budgetbuddy.service.BudgetBuddyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BudgetBuddyController {
    //============================-Variables-=================================
    @Autowired
    BudgetBuddyService budgetBuddyService;
    //===========================-Constructors-===============================

    //=============================-Methods-==================================

    //-------------------------------Index------------------------------------
    @RequestMapping("/")
    public String index() {
        return "index";
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
