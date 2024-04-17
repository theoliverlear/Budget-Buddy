package org.budgetbuddy.controller;

import org.budgetbuddy.communication.request.BudgetItemRequest;
import org.budgetbuddy.entity.budget.Budget;
import org.budgetbuddy.entity.budget.BudgetItem;
import org.budgetbuddy.entity.category.Category;
import org.budgetbuddy.entity.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/budget")
public class BudgetController {
    //===============================-Variables-=============================
    User currentUser;
    Budget currentBudget;
    //===============================-Methods-=============================
    @RequestMapping("/")
    public String budget(){
        return "budget";
    }
    @RequestMapping("/create")
    public ResponseEntity<String> create(){
        return null;
    }

    @RequestMapping("/add")
    public ResponseEntity<String> add(@ModelAttribute BudgetItemRequest budgetItemRequest){
    Category category = new Category(budgetItemRequest.getCategory());
        BudgetItem newBudgetItem = new BudgetItem(budgetItemRequest.getName(),budgetItemRequest.getAmount(),category);
        if (this.currentBudget != null){
            this.currentBudget.addBudgetItem(newBudgetItem);
            return new ResponseEntity<>("Sucsess", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failure", HttpStatus.NOT_ACCEPTABLE);
    }
    @RequestMapping("/edit/item")
    public ResponseEntity<String> editItem(@ModelAttribute BudgetItemRequest budgetItemRequest){
        Category category = new Category(budgetItemRequest.getCategory());
        BudgetItem updatedBudgetItem = new BudgetItem(budgetItemRequest.getName(),budgetItemRequest.getAmount(),category);
        if (this.currentBudget != null){
            this.currentBudget.updateBudgetItem(updatedBudgetItem);
            return new ResponseEntity<>("Sucsess", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failure", HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping("/edit/item/remove")
    public ResponseEntity<String> removeItem(@ModelAttribute BudgetItemRequest budgetItemRequest){
        Category category = new Category(budgetItemRequest.getCategory());
        BudgetItem removeBudgetItem = new BudgetItem(budgetItemRequest.getName(),budgetItemRequest.getAmount(),category);
        if (this.currentBudget.removeBudgetItem(removeBudgetItem)){
            return new ResponseEntity<>("Sucsess", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failure", HttpStatus.NOT_ACCEPTABLE);

    }
    //create, add to budget, edit



}
