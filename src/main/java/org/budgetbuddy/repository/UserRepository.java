package org.budgetbuddy.repository;

import org.budgetbuddy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User getUserById(Long id);
}
