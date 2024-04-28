package org.budgetbuddy.controller;
//=================================-Imports-==================================
import jakarta.servlet.http.HttpSession;
import org.budgetbuddy.communication.request.BudgetItemRequest;
import org.budgetbuddy.entity.budget.Budget;
import org.budgetbuddy.entity.budget.BudgetItem;
import org.budgetbuddy.entity.category.Category;
import org.budgetbuddy.entity.user.User;
import org.budgetbuddy.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/budget")
public class BudgetController {
    //============================-Variables-=================================
    User currentUser;
    Budget currentBudget = new Budget();
    @Autowired
    BudgetService budgetService;
    //=============================-Methods-==================================

    //--------------------------------User------------------------------------
    @RequestMapping("/")
    public String budget(HttpSession session) {
        // Get user from session.
        User currentUser = (User) session.getAttribute("user");
        // If the user is not logged in, redirect to the login page.
        if (currentUser == null) {
            return "redirect:/user/";
        }
        // If we have reached this point, the user is logged in. We set them
        // to the current user and get their budget.
        this.currentUser = currentUser;
        this.currentBudget = this.budgetService.getBudgetByUser(currentUser);
        return "budget";
    }
    //-----------------------------Get-Budget---------------------------------
    @RequestMapping("/get")
    public ResponseEntity<Budget> get() {
        // Return the current budget.
        return new ResponseEntity<>(this.currentBudget, HttpStatus.OK);
    }
    //--------------------------Add-Budget-Item-------------------------------
    @RequestMapping("/add")
    public ResponseEntity<String> add(@RequestBody BudgetItemRequest budgetItemRequest) {
        // Create a new budget item from the request.
        Category category = new Category(budgetItemRequest.getCategory());
        BudgetItem newBudgetItem = new BudgetItem(budgetItemRequest.getName(),
                                                  budgetItemRequest.getAmount(),
                                                  category);
        // If the current budget is not null, add the new budget item to it.
        if (this.currentBudget != null){
            this.currentBudget.addBudgetItem(newBudgetItem);
            // Update the budget in the database.
            this.budgetService.updateBudgetByUser(this.currentUser, this.currentBudget);
            // Return a success status.
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        // If we have reached this point, the budget is null. We return a
        // failure status.
        return new ResponseEntity<>("Failure", HttpStatus.NOT_ACCEPTABLE);
    }
    //--------------------------Edit-Budget-Item------------------------------
    @RequestMapping("/edit")
    public ResponseEntity<String> editItem(@RequestBody BudgetItemRequest budgetItemRequest) {
        // Create a new budget item from the request.
        Category category = new Category(budgetItemRequest.getCategory());
        BudgetItem updatedBudgetItem = new BudgetItem(budgetItemRequest.getName(),
                                                      budgetItemRequest.getAmount(),
                                                      category);
        // If the current budget is not null, update the budget item.
        if (this.currentBudget != null) {
            // If the budget item was updated successfully, update the budget
            // in the database and return a success status.
            if (this.currentBudget.updateBudgetItem(updatedBudgetItem)) {
                this.budgetService.updateBudgetByUser(this.currentUser, this.currentBudget);
                return new ResponseEntity<>("Success", HttpStatus.OK);
            } else {
                // If the budget item was not updated successfully, return a
                // failure status.
                return new ResponseEntity<>("Failure", HttpStatus.NOT_ACCEPTABLE);
            }
        }
        // If we have reached this point, the budget is null. We return a
        // failure status.
        return new ResponseEntity<>("Failure", HttpStatus.NOT_ACCEPTABLE);
    }
    //----------------------Edit-Item-With-New-Name---------------------------
    @RequestMapping("/edit/{newName}")
    public ResponseEntity<String> editItemWithNewName(@RequestBody BudgetItemRequest budgetItemRequest,
                                                      @PathVariable String newName) {
        // Create a new budget item from the request.
        Category category = new Category(budgetItemRequest.getCategory());
        BudgetItem updatedBudgetItem = new BudgetItem(budgetItemRequest.getName(),
                                                      budgetItemRequest.getAmount(),
                                                      category);
        // If the current budget is not null, update the budget item.
        if (this.currentBudget != null) {
            // If the budget item was updated successfully, update the budget
            // in the database and return a success status.
            if (this.currentBudget.updateBudgetItem(updatedBudgetItem, newName)) {
                this.budgetService.updateBudgetByUser(this.currentUser, this.currentBudget);
                return new ResponseEntity<>("Success", HttpStatus.OK);
            } else {
                // If the budget item was not updated successfully, return a
                // failure status.
                return new ResponseEntity<>("Failure", HttpStatus.NOT_ACCEPTABLE);
            }
        }
        // If we have reached this point, the budget is null. We return a
        // failure status.
        return new ResponseEntity<>("Failure", HttpStatus.NOT_ACCEPTABLE);
    }
    //--------------------------Remove-Budget-Item----------------------------
    @RequestMapping("/remove")
    public ResponseEntity<String> removeItem(@RequestBody BudgetItemRequest budgetItemRequest) {
        // Create a new budget item from the request.
        Category category = new Category(budgetItemRequest.getCategory());
        BudgetItem removeBudgetItem = new BudgetItem(budgetItemRequest.getName(),
                                                     budgetItemRequest.getAmount(),
                                                     category);
        // If the budget item was removed successfully, update the budget in
        // the database and return a success status.
        if (this.currentBudget.removeBudgetItem(removeBudgetItem)) {
            this.budgetService.updateBudgetByUser(this.currentUser, this.currentBudget);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        // If we have reached this point, the budget item was not removed
        // successfully. We return a failure status.
        return new ResponseEntity<>("Failure", HttpStatus.NOT_ACCEPTABLE);
    }
}
