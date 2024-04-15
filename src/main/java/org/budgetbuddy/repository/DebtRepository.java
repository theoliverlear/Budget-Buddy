package org.budgetbuddy.repository;
//=================================-Imports-==================================
import org.budgetbuddy.entity.holding.debt.Debt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebtRepository extends JpaRepository<Debt, Long> {
    //=============================-Methods-==================================
}
