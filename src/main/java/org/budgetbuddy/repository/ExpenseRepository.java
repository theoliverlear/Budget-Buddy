package org.budgetbuddy.repository;
//=================================-Imports-==================================
import org.budgetbuddy.entity.expense.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    //=============================-Methods-==================================
}
