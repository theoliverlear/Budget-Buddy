package org.budgetbuddy.communication.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class BudgetItemRequest {
    String name;
    double amount;
    String category;
    public BudgetItemRequest(String name,double amount,String category){
        this.name = name;
        this.amount = amount;
        this.category = category;
    }
}
