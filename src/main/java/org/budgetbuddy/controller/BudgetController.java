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
/**
 * <h6>This class is a controller for managing Budgets.</h6>
 *
 * It handles requests related to Budgets, such as retrieving the current
 * Budget, adding a BudgetItem, editing a BudgetItem, and removing a
 * BudgetItem.
 */
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
    /**
     * <h6>Retrieves the Budget for the current user.</h6>
     *
     * @param session HttpSession object representing the session of the user.
     * @return The view name of the budget template.
     */
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
    /**
     * <h6>Retrieves the current Budget.</h6>
     *
     * @return A ResponseEntity object representing the response to the
     *         request with the current Budget and HTTP status code OK.
     */
    @RequestMapping("/get")
    public ResponseEntity<Budget> get() {
        // Return the current budget.
        return new ResponseEntity<>(this.currentBudget, HttpStatus.OK);
    }
    //--------------------------Add-Budget-Item-------------------------------
    /**
     * <h6>Adds a new BudgetItem in the current Budget.</h6>
     *
     * @param budgetItemRequest The BudgetItemRequest object containing the
     *                          details of the Budget to be added. The
     *                          object must have the following properties:
     *                          <ul>
     *                              <li>name: The name of the BudgetItem
     *                                  (String).</li>
     *                              <li>amount: The amount of the BudgetItem
     *                                  (double).</li>
     *                              <li>category: The Category of the
     *                                  BudgetItem (String).</li>
     *                          </ul>
     * @return A ResponseEntity object representing the response to the
     *         request.
     * <ul>
     *     <li>If the current Budget is not null, the new BudgetItem is added
     *         to it and the Budget is updated in the database. A success
     *         status (HttpStatus.OK) is returned.</li>
     *     <li>If the current Budget is null, a failure status
     *         (HttpStatus.NOT_ACCEPTABLE) is returned.</li>
     * </ul>
     */
    @RequestMapping("/add")
    public ResponseEntity<String> add(@RequestBody BudgetItemRequest budgetItemRequest) {
        // Create a new budget item from the request.
        Category category = new Category(budgetItemRequest.getCategory());
        BudgetItem newBudgetItem = new BudgetItem(budgetItemRequest.getName(),
                budgetItemRequest.getAmount(),
                category);

        // If the current budget is not null, add the new budget item to it.
        if (this.currentBudget != null) {
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
    /**
     * <h6>Edits a BudgetItem.</h6>
     *
     * @param budgetItemRequest The BudgetItemRequest object containing the
     *                          details of the BudgetItem to be edited. The
     *                          object must have the following properties:
     *                          <ul>
     *                              <li>name: The new name of the BudgetItem
     *                                  (String).</li>
     *                              <li>amount: The new amount of the
     *                                  BudgetItem (double).</li>
     *                              <li>category: The new Category of the
     *                                  BudgetItem (String).</li>
     *                          </ul>
     * @return A ResponseEntity object representing the response to the
     *         request.
     *         <ul>
     *             <li> If the current budget is not null, the budget item is
     *                  updated with the new details and the budget is updated
     *                  in the database. A success status (HttpStatus.OK) is
     *                  returned.</li>
     *             <li>If the current budget is null, a failure status
     *                 (HttpStatus.NOT_ACCEPTABLE) is returned.</li>
     *         </ul>
     */
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
    /**
     * <h6>Edits a budget item with a new name.</h6>
     *
     * @param budgetItemRequest The BudgetItemRequest object containing the
     *                          details of the BudgetItem to be edited. The
     *                          object must have the following properties:
     *                          <ul>
     *                              <li>name: The new name of the BudgetItem
     *                                  (String).</li>
     *                              <li>amount: The new amount of the
     *                                  BudgetItem (double).</li>
     *                              <li>category: The new category of the
     *                                  BudgetItem (String).</li>
     *                          </ul>
     * @param newName The new name for the BudgetItem (String).
     * @return A ResponseEntity object representing the response to the
     * request.
     * <ul>
     *      <li>If the current Budget is not null, the budget item is updated
     *          with the new details and the Budget is updated in the
     *          database. A success status (HttpStatus.OK) is returned.</li>
     *      <li>If the current Budget is null, a failure status
     *          (HttpStatus.NOT_ACCEPTABLE) is returned.</li>
     * </ul>
     */
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
    /**
     * <h6>Removes a BudgetItem from the current Budget.</h6>
     *
     * @param budgetItemRequest The BudgetItemRequest object containing the
     *                          details of the BudgetItem to be removed. The
     *                          object must have the following properties:
     *                          <ul>
     *                              <li>name: The name of the BudgetItem
     *                                  (String).</li>
     *                              <li>amount: The amount of the BudgetItem
     *                                  (double).</li>
     *                              <li>category: The Category of the
     *                                  BudgetItem (String).</li>
     *                          </ul>
     * @return A ResponseEntity object representing the response to the request.
     * <ul>
     *      <li>If the BudgetItem exists in the current Budget, the item is
     *          removed and the Budget is updated in the database. A success
     *          status (HttpStatus.OK) is returned.</li>
     *      <li>If the BudgetItem doesn't exist in the current Budget or the
     *          current Budget is null, a failure status
     *          (HttpStatus.NOT_ACCEPTABLE) is returned.</li>
     * </ul>
     */
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
