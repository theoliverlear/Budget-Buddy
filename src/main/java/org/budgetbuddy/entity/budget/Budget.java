package org.budgetbuddy.entity.budget;
//=================================-Imports-==================================
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import org.budgetbuddy.convert.entity.budget.BudgetItemsArrayListConverter;
import org.budgetbuddy.convert.entity.budget.BudgetKeyDeserializer;

import java.util.ArrayList;

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
    public Budget() {
        this.budgetItems = new ArrayList<>();
    }
    public Budget(ArrayList<BudgetItem> budgetItems) {
        this.budgetItems = budgetItems;
    }
    //=============================-Methods-==================================

    //--------------------------Add-Budget-Items------------------------------
    public void addBudgetItem(BudgetItem budgetItem) {
        // Add the budget item to the list of BudgetItems.
        this.budgetItems.add(budgetItem);
    }
    //-------------------------Remove-Budget-Item-----------------------------
    public boolean removeBudgetItem(BudgetItem budgetItem) {
        // Remove the budget item from the list of BudgetItems. Return whether
        // the budget item was removed successfully.
        return this.budgetItems.remove(budgetItem);
    }
    //-------------------------Update-Budget-Item-----------------------------
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
