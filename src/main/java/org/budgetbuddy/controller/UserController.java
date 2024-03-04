package org.budgetbuddy.controller;
//=================================-Imports-==================================
import org.budgetbuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController extends BudgetBuddyController {
    //============================-Variables-=================================
    @Autowired
    UserService userService;
    //===========================-Constructors-===============================

    //=============================-Methods-==================================

    //-------------------------------Login------------------------------------
    @RequestMapping("/login")
    public String login() {
        return "index";
    }
    //------------------------------Sign-Up-----------------------------------
    @RequestMapping("/signup")
    public String signup() {
        return "index";
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
