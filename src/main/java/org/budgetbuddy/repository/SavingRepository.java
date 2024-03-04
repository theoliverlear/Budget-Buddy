package org.budgetbuddy.repository;
//=================================-Imports-==================================
import org.budgetbuddy.entity.savings.Saving;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingRepository extends JpaRepository<Saving, Long> {
    //=============================-Methods-==================================
}
