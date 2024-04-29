package org.budgetbuddy.service;
//=================================-Imports-==================================
import org.budgetbuddy.entity.budget.Budget;
import org.budgetbuddy.entity.user.User;
import org.budgetbuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {
    //============================-Variables-=================================
    UserRepository userRepository;
    //===========================-Constructors-===============================
    @Autowired
    public BudgetService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //=============================-Methods-==================================

    //-------------------------Get-Budget-By-User-----------------------------
    public Budget getBudgetByUser(User user) {
        // Call on the UserRepository to get the budget by the user's id.
        return this.userRepository.getBudgetById(user.getId());
    }
    //-----------------------Update-Budget-By-User----------------------------
    public void updateBudgetByUser(User user, Budget budget) {
        // Update the user's current budget and save the changes.
        user.setCurrentBudget(budget);
        this.userRepository.save(user);
    }
}
