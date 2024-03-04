package org.budgetbuddy.repository;
//=================================-Imports-==================================
import org.budgetbuddy.entity.expense.ExpenseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseHistoryRepository extends JpaRepository<ExpenseHistory, Long> {
    //=============================-Methods-==================================
}
