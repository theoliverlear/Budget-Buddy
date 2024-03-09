package org.budgetbuddy.controller;
//=================================-Imports-==================================
import org.budgetbuddy.entity.user.User;
import org.budgetbuddy.service.BudgetBuddyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BudgetBuddyController {
    //============================-Variables-=================================
    @Autowired
    BudgetBuddyService budgetBuddyService;
    User currentUser;
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
    public User getCurrentUser() {
        return currentUser;
    }
    //=============================-Setters-==================================
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
