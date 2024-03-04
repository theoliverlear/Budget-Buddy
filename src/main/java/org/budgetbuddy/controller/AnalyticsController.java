package org.budgetbuddy.controller;
//=================================-Imports-==================================
import org.budgetbuddy.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/analytics")
public class AnalyticsController extends BudgetBuddyController {
    //============================-Variables-=================================
    @Autowired
    AnalyticsService analyticsService;
    //===========================-Constructors-===============================

    //=============================-Methods-==================================

    //-----------------------------Analytics----------------------------------
    @RequestMapping("/view")
    public String analytics() {
        return "analytics";
    }

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
