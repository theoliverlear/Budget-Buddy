package org.budgetbuddy.repository;

import org.budgetbuddy.entity.expense.ExpenseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseHistoryRepository extends JpaRepository<ExpenseHistory, Long> {
}
