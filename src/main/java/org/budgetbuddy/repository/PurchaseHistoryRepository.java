package org.budgetbuddy.repository;
//=================================-Imports-==================================
import org.budgetbuddy.entity.purchase.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {
    //=============================-Methods-==================================
}
