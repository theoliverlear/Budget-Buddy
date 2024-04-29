package org.budgetbuddy.entity.budget;
//=================================-Imports-==================================
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import org.budgetbuddy.convert.entity.budget.BudgetItemsArrayListConverter;
import org.budgetbuddy.convert.entity.budget.BudgetKeyDeserializer;

import java.util.ArrayList;

/**
 * <h6>This class represents a User's Budget.</h6>
 *
 * A Budget is a collection of BudgetItems that represent the User's planned
 * spending.
 * @see BudgetItem
 * @see Entity
 */
@Entity
@JsonDeserialize(keyUsing = BudgetKeyDeserializer.class)
public class Budget {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Convert(converter = BudgetItemsArrayListConverter.class)
    ArrayList<BudgetItem> budgetItems;
    //===========================-Constructors-===============================
    /**
     * <h6>Default constructor for a Budget object.</h6>
     */
    public Budget() {
        this.budgetItems = new ArrayList<>();
    }
    /**
     * <h6>Constructor for a Budget object.</h6>
     *
     * @param budgetItems The list of BudgetItems to add to the Budget.
     */
    public Budget(ArrayList<BudgetItem> budgetItems) {
        this.budgetItems = budgetItems;
    }
    //=============================-Methods-==================================

    //--------------------------Add-Budget-Items------------------------------
    /**
     * <h6>Add a BudgetItem to the Budget.</h6>
     *
     * @param budgetItem The BudgetItem to add to the Budget.
     */
    public void addBudgetItem(BudgetItem budgetItem) {
        // Add the budget item to the list of BudgetItems.
        this.budgetItems.add(budgetItem);
    }
    //-------------------------Remove-Budget-Item-----------------------------
    /**
     * <h6>Remove a BudgetItem from the Budget.</h6>
     *
     * @param budgetItem The BudgetItem to remove from the Budget.
     * @return Whether the BudgetItem was removed successfully.
     */
    public boolean removeBudgetItem(BudgetItem budgetItem) {
        // Remove the budget item from the list of BudgetItems. Return whether
        // the budget item was removed successfully.
        return this.budgetItems.remove(budgetItem);
    }
    //-------------------------Update-Budget-Item-----------------------------
    /**
     * <h6>Update a BudgetItem in the Budget.</h6>
     *
     * @param updatedBudgetItem The updated BudgetItem to replace the existing
     *                          BudgetItem.
     * @return Whether the BudgetItem was updated successfully.
     */
    public boolean updateBudgetItem(BudgetItem updatedBudgetItem) {
        // Loop through the list of BudgetItems to find the one that matches
        // the name of the item to update.
        for (BudgetItem budgetItem : this.budgetItems) {
            if (budgetItem.getName().equals(updatedBudgetItem.getName())) {
                // If the item is found, update the item fields and return
                // true to indicate a successful update.
                budgetItem.setAmount(updatedBudgetItem.getAmount());
                budgetItem.setCategory(updatedBudgetItem.getCategory());
                return true;
            }
        }
        // If the item is not found, return false to indicate an unsuccessful
        // update.
        return false;
    }
    //-------------------------Update-Budget-Item-----------------------------
    /**
     * <h6>Update a BudgetItem in the Budget.</h6>
     *
     * @param updatedBudgetItem The updated BudgetItem to replace the existing
     *                          BudgetItem.
     * @param newBudgetItemName The new name for the BudgetItem.
     * @return Whether the BudgetItem was updated successfully.
     */
    public boolean updateBudgetItem(BudgetItem updatedBudgetItem, String newBudgetItemName) {
        // Loop through the list of BudgetItems to find the one that matches
        // the name of the item to update.
        for (BudgetItem budgetItem : this.budgetItems) {
            if (budgetItem.getName().equals(updatedBudgetItem.getName())) {
                // If the item is found, update the item fields and return
                // true to indicate a successful update.
                budgetItem.setName(newBudgetItemName);
                budgetItem.setAmount(updatedBudgetItem.getAmount());
                budgetItem.setCategory(updatedBudgetItem.getCategory());
                return true;
            }
        }
        // If the item is not found, return false to indicate an unsuccessful
        // update.
        return false;
    }
    //----------------------Get-Budget-Item-By-Title--------------------------
    /**
     * <h6>Get a BudgetItem from the Budget by its name.</h6>
     *
     * @param budgetItemName The name of the BudgetItem to get.
     * @return The BudgetItem with the given name.
     */
    public BudgetItem getBudgetItemByTitle(String budgetItemName) {
        // Loop through the list of BudgetItems to find the one that matches
        // the name of the item to get.
        BudgetItem budgetItemFound = null;
        for (BudgetItem budgetItem : this.getBudgetItems()) {
            // If the item is found, return the item.
            if (budgetItem.getName().equals(budgetItemName)) {
                return budgetItem;
            }
        }
        // If the item is not found, return null.
         return null;
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    /**
     * <h6>Check if two Budget objects are equal.</h6>
     *
     * @param obj The object to compare to the Budget object.
     * @return boolean Whether the two objects are equal.
     */
    @Override
    public boolean equals(Object obj) {
        // Check if the object references are the same. If they are, return
        // true.
        if (this == obj) return true;
        // Check if the object is an instance of the Budget class. If it is,
        // cast it to a Budget object.
        if (obj instanceof Budget budget) {
            // Check if the budget items are the same. If they are, return
            // true.
            boolean sameBudgetItems = this.budgetItems.equals(budget.getBudgetItems());
            // If id is not null, an equality check can be performed using
            // that field.
            if (this.id != null) {
                // Return whether all fields are equal.
                boolean sameId = this.id.equals(budget.getId());
                return sameId && sameBudgetItems;
            } else {
                // If id is null, return whether all fields are equal except
                // for the id field.
                return sameBudgetItems;
            }
        }
        // If we have reached this point, the objects not instances of the
        // same class and are not equal, so we return false.
        return false;
    }
    //------------------------------Hash-Code---------------------------------

    /**
     * <h6>Get the hashcode of the Budget object.</h6>
     *
     * Creates a hashcode based on the id field of the Budget object.
     * @return int The hashcode of the Budget object.
     */
    @Override
    public int hashCode() {
        // Return the hashcode of the id field.
        return this.id.hashCode();
    }
    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================
    public Long getId() {
        return this.id;
    }
    public ArrayList<BudgetItem> getBudgetItems() {
        return this.budgetItems;
    }
    //=============================-Setters-==================================
    public void setId(Long id) {
        this.id = id;
    }
    public void setBudgetItems(ArrayList<BudgetItem> budgetItems) {
        this.budgetItems = budgetItems;
    }
}
