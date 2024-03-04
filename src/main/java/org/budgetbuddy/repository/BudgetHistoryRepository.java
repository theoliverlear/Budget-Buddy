package org.budgetbuddy.repository;
//=================================-Imports-==================================
import org.budgetbuddy.entity.budget.BudgetHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetHistoryRepository extends JpaRepository<BudgetHistory, Long> {
    //=============================-Methods-==================================

}
