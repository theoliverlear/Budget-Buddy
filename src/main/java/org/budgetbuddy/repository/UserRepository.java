package org.budgetbuddy.repository;
//=================================-Imports-==================================
import org.budgetbuddy.entity.budget.Budget;
import org.budgetbuddy.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //=============================-Methods-==================================
    User findByUsername(String username);
    User getUserById(Long id);
    Budget getBudgetById(Long id);
    boolean existsByUsername(String username);
}
