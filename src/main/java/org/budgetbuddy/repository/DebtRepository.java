package org.budgetbuddy.repository;

import org.budgetbuddy.entity.debt.Debt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebtRepository extends JpaRepository<Debt, Long> {
}
