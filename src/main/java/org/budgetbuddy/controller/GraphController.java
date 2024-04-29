package org.budgetbuddy.controller;
//=================================-Imports-==================================
import jakarta.servlet.http.HttpSession;
import org.budgetbuddy.entity.budget.Budget;
import org.budgetbuddy.entity.user.User;
import org.budgetbuddy.model.graph.PieGraph;
import org.budgetbuddy.service.GraphService;
import org.budgetbuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/graph")
public class GraphController {
    //============================-Variables-=================================
    @Autowired
    GraphService graphService;
    @Autowired
    UserService userService;
    //===========================-Constructors-===============================

    //=============================-Methods-==================================

    //-------------------------------Graph------------------------------------
    @RequestMapping("/view")
    public String graph() {
        return "graph";
    }
    //-----------------------------Pie-Graph----------------------------------
    @RequestMapping("/view/pie/{dataType}")
    public ResponseEntity<String> pieGraph(@PathVariable String dataType, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        // If the user is not logged in, return an unauthorized status.
        if (currentUser == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        // TODO: Generalize this method to work with all kinds of user's data.
        // Let's say the user wants to see their budget breakdown in a pie
        // graph.
        Long userId = currentUser.getId();
        // Get the user's budget based on their ID.
        Budget userBudget = this.userService.getUserRepository().getBudgetById(userId);
        // Build a pie graph based on the user's budget.
        PieGraph pieGraph = this.graphService.buildPieGraph(userBudget);
        // Return the pie graph as a string.
        return new ResponseEntity<>(pieGraph.toString(), HttpStatus.OK);
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
