package org.budgetbuddy.controller;
//=================================-Imports-==================================
import org.budgetbuddy.entity.user.User;
import org.budgetbuddy.service.BudgetBuddyService;
import org.budgetbuddy.service.UserService;
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
    public String home() {
        // Return the home page.
        return "home";
    }

    @RequestMapping("/terms")
    public String terms() {
        // Return the terms of service page.
        return "terms";
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================

}
