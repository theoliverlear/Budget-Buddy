package org.budgetbuddy.repository;

import org.budgetbuddy.entity.savings.Saving;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingRepository extends JpaRepository<Saving, Long> {

}
