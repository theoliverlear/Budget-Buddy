package org.budgetbuddy.repository;
//=================================-Imports-==================================
import org.budgetbuddy.entity.finance.Finance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceRepository extends JpaRepository<Finance, Long> {
    //=============================-Methods-==================================
}
