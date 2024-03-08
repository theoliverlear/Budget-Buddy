package org.budgetbuddy.repository;
//=================================-Imports-==================================
import org.budgetbuddy.entity.finance.FinanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceHistoryRepository extends JpaRepository<FinanceHistory, Long> {
    //=============================-Methods-==================================
}
