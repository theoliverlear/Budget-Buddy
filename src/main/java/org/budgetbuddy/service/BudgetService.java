package org.budgetbuddy.service;

import org.budgetbuddy.entity.budget.Budget;
import org.budgetbuddy.entity.user.User;
import org.budgetbuddy.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {
    //============================-Variables-=================================
    UserRepository userRepository;
    //=============================-Methods-==================================

    //-------------------------Get-Budget-By-User-----------------------------
    public Budget getBudgetByUser(User user){
        return this.userRepository.getBudgetById(user.getId());
    }
    //-----------------------Update-Budget-By-User----------------------------
    public void updateBudgetByUser(User user, Budget budget){
        user.setCurrentBudget(budget);
        this.userRepository.save(user);
    }
}
