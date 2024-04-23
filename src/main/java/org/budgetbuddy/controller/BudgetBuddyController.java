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
    @Autowired
    UserService userService;
    User currentUser;
    //===========================-Constructors-===============================

    //=============================-Methods-==================================

    //-------------------------------Index------------------------------------
    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/terms")
    public String terms() {
        return "terms";
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================
    public User getCurrentUser() {
        return this.currentUser;
    }
    //=============================-Setters-==================================
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
