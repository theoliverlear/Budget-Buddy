package org.budgetbuddy.entity.budget;
//=================================-Imports-==================================
import jakarta.persistence.*;
import org.budgetbuddy.convert.entity.BudgetItemsArrayListConverter;

import java.util.ArrayList;

@Entity
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
    public void removeBudgetItem(BudgetItem budgetItem) {//removing Budget Item
        this.budgetItems.remove(budgetItem);
    }
    public void updateBudgetItem(BudgetItem budgetItem){ //update Budget Item
        int budgetItemIndex = this.getBudgetItems().indexOf(budgetItem);
        if(budgetItemIndex != -1){//checks if budget item is found
            this.getBudgetItems().remove(budgetItemIndex);
            this.getBudgetItems().add(budgetItemIndex, budgetItem);
        }
        else{
            System.out.println(budgetItem.getName()+" is not found");
        }
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
        if (obj instanceof Budget budget){
            boolean sameId = this.id.equals(budget.getId());
            boolean sameBudgetItems = this.budgetItems.equals(budget.getBudgetItems());
            return sameId && sameBudgetItems;
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
