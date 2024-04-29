package org.budgetbuddy.communication.request;
//=================================-Imports-==================================
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class BudgetItemRequest {
    //============================-Variables-=================================
    String name;
    double amount;
    String category;
    //===========================-Constructors-===============================
    public BudgetItemRequest(String name,double amount,String category){
        this.name = name;
        this.amount = amount;
        this.category = category;
    }
}
