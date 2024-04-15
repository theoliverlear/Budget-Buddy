package org.budgetbuddy.repository;
//=================================-Imports-==================================
import org.budgetbuddy.entity.holding.savings.SavingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingHistoryRepository extends JpaRepository<SavingHistory, Long> {
    //=============================-Methods-==================================
}
