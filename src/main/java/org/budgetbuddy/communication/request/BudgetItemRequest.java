package org.budgetbuddy.communication.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BudgetItemRequest {
    String name;
    double amount;
    String category;

    public BudgetItemRequest() {
        this.name = "";
        this.amount = 0;
        this.category= "";
    }

    public BudgetItemRequest(String name,double amount,String category){
        this.name = name;
        this.amount = amount;
        this.category = category;
    }
}
