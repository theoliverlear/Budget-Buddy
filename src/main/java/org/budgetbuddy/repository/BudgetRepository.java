package org.budgetbuddy.repository;
//=================================-Imports-==================================
import org.budgetbuddy.entity.budget.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    //=============================-Methods-==================================
}
