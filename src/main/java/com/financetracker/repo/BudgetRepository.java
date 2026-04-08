package com.financetracker.repo;

import com.financetracker.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    // Find all budgets for a user
    List<Budget> findByUserId(Long userId);

    // Find budgets for a user by category
    List<Budget> findByUserIdAndCategory(Long userId, String category);
}