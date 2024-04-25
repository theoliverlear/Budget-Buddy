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
    public void addBudgetItem(BudgetItem budgetItem) { //adding Budget Item method
        this.budgetItems.add(budgetItem);
    }
    public boolean removeBudgetItem(BudgetItem budgetItem) {//removing Budget Item
        return this.budgetItems.remove(budgetItem);
    }
    public boolean updateBudgetItem(BudgetItem updatedBudgetItem) {
        for (BudgetItem budgetItem : this.budgetItems) {
            if (budgetItem.getName().equals(updatedBudgetItem.getName())) {
                budgetItem.setAmount(updatedBudgetItem.getAmount());
                budgetItem.setCategory(updatedBudgetItem.getCategory());
                return true;
            }
        }
        return false;
    }
    public boolean updateBudgetItem(BudgetItem updatedBudgetItem, String newBudgetItemName) {
        for (BudgetItem budgetItem : this.budgetItems) {
            if (budgetItem.getName().equals(updatedBudgetItem.getName())) {
                budgetItem.setName(newBudgetItemName);
                budgetItem.setAmount(updatedBudgetItem.getAmount());
                budgetItem.setCategory(updatedBudgetItem.getCategory());
                return true;
            }
        }
        return false;
    }
    public BudgetItem getBudgetItemByTitle(String budgetItemName){//gets item by title
        BudgetItem budgetItemFound=null;
        for(BudgetItem budgetItem : this.getBudgetItems()){
            if(budgetItem.getName().equals(budgetItemName)){
                return budgetItem;
            }
        }
         return null;
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj instanceof Budget budget) {
            boolean sameBudgetItems = this.budgetItems.equals(budget.getBudgetItems());
            if (this.id != null) {
                boolean sameId = this.id.equals(budget.getId());
                return sameId && sameBudgetItems;
            } else {
                return sameBudgetItems;
            }
        }
        return false;
    }
    //------------------------------Hash-Code---------------------------------
    @Override
    public int hashCode() {
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
