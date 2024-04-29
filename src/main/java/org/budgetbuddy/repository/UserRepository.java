package org.budgetbuddy.repository;
//=================================-Imports-==================================
import org.budgetbuddy.entity.budget.Budget;
import org.budgetbuddy.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    //=============================-Methods-==================================
    // Returns a given user by their username.
    User findByUsername(String username);
    // Returns a given user by their ID.
    User getUserById(Long id);
    // Returns the current budget of a given user by their ID. The query is
    // written explicitly to avoid invalid return results.
    @Query("SELECT u.currentBudget FROM User u WHERE u.id = :id")
    Budget getBudgetById(Long id);
    // Returns whether a user exists by their username.
    boolean existsByUsername(String username);
}
