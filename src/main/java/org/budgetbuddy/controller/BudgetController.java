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
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/user/";
        }
        this.currentUser = currentUser;
        this.currentBudget = this.budgetService.getBudgetByUser(currentUser);
        return "budget";
    }
    //-----------------------------Get-Budget---------------------------------
    @RequestMapping("/get")
    public ResponseEntity<Budget> get() {
        return new ResponseEntity<>(this.currentBudget, HttpStatus.OK);
    }
    //--------------------------Add-Budget-Item-------------------------------
    @RequestMapping("/add")
    public ResponseEntity<String> add(@RequestBody BudgetItemRequest budgetItemRequest){
    Category category = new Category(budgetItemRequest.getCategory());
        BudgetItem newBudgetItem = new BudgetItem(budgetItemRequest.getName(),budgetItemRequest.getAmount(),category);
        if (this.currentBudget != null){
            this.currentBudget.addBudgetItem(newBudgetItem);
            this.budgetService.updateBudgetByUser(this.currentUser, this.currentBudget);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failure", HttpStatus.NOT_ACCEPTABLE);
    }
    //--------------------------Edit-Budget-Item------------------------------
    @RequestMapping("/edit")
    public ResponseEntity<String> editItem(@RequestBody BudgetItemRequest budgetItemRequest){
        Category category = new Category(budgetItemRequest.getCategory());
        BudgetItem updatedBudgetItem = new BudgetItem(budgetItemRequest.getName(),budgetItemRequest.getAmount(),category);
        if (this.currentBudget != null){
            if (this.currentBudget.updateBudgetItem(updatedBudgetItem)) {
                this.budgetService.updateBudgetByUser(this.currentUser, this.currentBudget);
                return new ResponseEntity<>("Success", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failure", HttpStatus.NOT_ACCEPTABLE);
            }
        }
        return new ResponseEntity<>("Failure", HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping("/edit/{newName}")
    public ResponseEntity<String> editItemWithNewName(@RequestBody BudgetItemRequest budgetItemRequest, @PathVariable String newName) {
        Category category = new Category(budgetItemRequest.getCategory());
        BudgetItem updatedBudgetItem = new BudgetItem(budgetItemRequest.getName(),budgetItemRequest.getAmount(),category);
        if (this.currentBudget != null){
            if (this.currentBudget.updateBudgetItem(updatedBudgetItem, newName)) {
                this.budgetService.updateBudgetByUser(this.currentUser, this.currentBudget);
                return new ResponseEntity<>("Success", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failure", HttpStatus.NOT_ACCEPTABLE);
            }
        }
        return new ResponseEntity<>("Failure", HttpStatus.NOT_ACCEPTABLE);
    }
    //--------------------------Remove-Budget-Item----------------------------
    @RequestMapping("/remove")
    public ResponseEntity<String> removeItem(@RequestBody BudgetItemRequest budgetItemRequest){
        Category category = new Category(budgetItemRequest.getCategory());
        BudgetItem removeBudgetItem = new BudgetItem(budgetItemRequest.getName(),budgetItemRequest.getAmount(),category);
        System.out.println(removeBudgetItem);
        if (this.currentBudget.removeBudgetItem(removeBudgetItem)) {
            this.budgetService.updateBudgetByUser(this.currentUser, this.currentBudget);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failure", HttpStatus.NOT_ACCEPTABLE);

    }
}
