package org.budgetbuddy.repository;
//=================================-Imports-==================================
import org.budgetbuddy.entity.debt.DebtHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebtHistoryRepository extends JpaRepository<DebtHistory, Long> {
    //=============================-Methods-==================================
}
