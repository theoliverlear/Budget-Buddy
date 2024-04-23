package org.budgetbuddy.repository;
//=================================-Imports-==================================
import org.budgetbuddy.entity.budget.Budget;
import org.budgetbuddy.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    //=============================-Methods-==================================
    User findByUsername(String username);
    User getUserById(Long id);
    @Query("SELECT u.currentBudget FROM User u WHERE u.id = :id")
    Budget getBudgetById(Long id);
    boolean existsByUsername(String username);
}
